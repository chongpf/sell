/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-7 下午2:48
 */

package org.michael.service;

import org.michael.dto.OrderDTO;

public interface BuyerService {

    public OrderDTO findOrderOne(String openid, String orderId);

    public OrderDTO cancelOrder(String openid, String orderId);
}
