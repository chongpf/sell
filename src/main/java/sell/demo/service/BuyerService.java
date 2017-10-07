/*
 * Created by Michael
 * 17-10-7 下午2:48
 */

package sell.demo.service;

import sell.demo.dto.OrderDTO;

public interface BuyerService {

    public OrderDTO findOrderOne(String openid, String orderId);

    public OrderDTO cancelOrder(String openid, String orderId);
}
