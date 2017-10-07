/*
 * Created by Michael
 * 17-10-6 下午4:00
 */

package sell.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sell.demo.converter.OrderMaster2OrderDTOConverter;
import sell.demo.dataobject.OrderDetail;
import sell.demo.dataobject.OrderMaster;
import sell.demo.dataobject.ProductInfo;
import sell.demo.dto.CartDTO;
import sell.demo.dto.OrderDTO;
import sell.demo.enums.OrderStatusEnum;
import sell.demo.enums.PayStatusEnum;
import sell.demo.enums.ResultEnum;
import sell.demo.exception.SellException;
import sell.demo.repository.OrderDetailRepository;
import sell.demo.repository.OrderMasterRepository;
import sell.demo.service.OrderService;
import sell.demo.service.ProductService;
import sell.demo.util.KeyUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmout = new BigDecimal(0);
        /// 0.select price form product
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //1.calculate sum price
            BigDecimal thisPrice = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()));
            orderAmout = orderAmout.add(thisPrice);

            //2.订单detail入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        // 3.write orderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderId(orderId);
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList =
                orderDTO.getOrderDetailList().stream().map(
                        e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_MASTER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(
                orderDTOList, pageable, orderMasterPage.getTotalElements()
        );
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        //1.判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[取消订单]订单状态不正确,orderId={},orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[取消订单]更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        //3.返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单]订单无商品详情,orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //4.如果未支付，需要退款
        if (orderMaster.getPayStatus().equals(PayStatusEnum.WAIT)) {
            //orderMaster.setPayStatus();
        }

        OrderDTO result = OrderMaster2OrderDTOConverter.convert(orderMaster);
        result.setOrderDetailList(orderDTO.getOrderDetailList());
        return result;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完成订单]状态不正确无法完结订单,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_FINISH_STATUS_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[完结订单]更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_FINISH_UPDATE_ERROR);
        }

        OrderDTO result = OrderMaster2OrderDTOConverter.convert(orderMaster);
        result.setOrderDetailList(orderDTO.getOrderDetailList());

        return result;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[支付订单]订单状态不对,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        if (!orderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[支付订单]状态不正确无法支付订单,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderMaster.setPayStatus(PayStatusEnum.FINISHED.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[支付订单]更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_PAY_UPDATE_ERROR);
        }

        OrderDTO result = OrderMaster2OrderDTOConverter.convert(orderMaster);
        result.setOrderDetailList(orderDTO.getOrderDetailList());

        return result;
    }
}
