/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-7 上午8:21
 */

package org.michael.controller;

import lombok.extern.slf4j.Slf4j;
import org.michael.enums.ResultEnum;
import org.michael.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.michael.converter.OrderForm2OrderDTO;
import org.michael.dto.OrderDTO;
import org.michael.exception.SellException;
import org.michael.form.OrderForm;
import org.michael.service.BuyerService;
import org.michael.util.ResultVoUtil;
import org.michael.vo.ResultVo;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVoUtil.success(map);
    }


    // 订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openId)) {
            log.error("[查询表单列表]openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openId, request);
        List<OrderDTO> orderDTOList = orderDTOPage.getContent();

        return ResultVoUtil.success(orderDTOList);
    }

    // 订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(
            @RequestParam(value = "openid", defaultValue = "0") String openid,
            @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

    // 取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(
            @RequestParam(value = "openid", defaultValue = "0") String openid,
            @RequestParam("orderId") String orderId) {

        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }
}
