/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-7 下午2:50
 */

package org.michael.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.michael.enums.ResultEnum;
import org.michael.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.michael.dto.OrderDTO;
import org.michael.exception.SellException;
import org.michael.service.BuyerService;

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
