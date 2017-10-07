/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 下午3:53
 */

package org.michael.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.michael.dto.OrderDTO;

public interface OrderService {
    public OrderDTO create(OrderDTO orderDTO);

    public OrderDTO findOne(String orderId);

    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    public OrderDTO cancel(OrderDTO orderDTO);

    public OrderDTO finish(OrderDTO orderDTO);

    public OrderDTO paid(OrderDTO orderDTO);
}
