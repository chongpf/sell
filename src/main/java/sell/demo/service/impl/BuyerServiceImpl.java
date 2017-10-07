/*
 * Created by Michael
 * 17-10-7 下午2:50
 */

package sell.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sell.demo.dto.OrderDTO;
import sell.demo.enums.ResultEnum;
import sell.demo.exception.SellException;
import sell.demo.service.BuyerService;
import sell.demo.service.OrderService;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        OrderDTO orderDTO = checkOrderDTO(openid, orderId);
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {

        OrderDTO orderDTO = checkOrderDTO(openid, orderId);
        orderService.cancel(orderDTO);
        return orderDTO;
    }

    private OrderDTO checkOrderDTO(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("[订单处理]openid内容不合法");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return orderDTO;
    }
}
