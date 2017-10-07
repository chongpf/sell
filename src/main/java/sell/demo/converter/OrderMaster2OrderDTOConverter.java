/*
 * Created by Michael
 * 17-10-6 下午5:54
 */

package sell.demo.converter;

import org.springframework.beans.BeanUtils;
import sell.demo.dataobject.OrderMaster;
import sell.demo.dto.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList =
                orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
        return orderDTOList;
    }
}
