/*
 * Created by Michael
 * 17-10-7 上午8:44
 */

package sell.demo.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import sell.demo.dataobject.OrderDetail;
import sell.demo.dto.OrderDTO;
import sell.demo.enums.ResultEnum;
import sell.demo.exception.SellException;
import sell.demo.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList =
                    gson.fromJson(orderForm.getItems(),
                            new TypeToken<List<OrderDetail>>() {
                            }.getType());
        } catch (Exception e) {
            log.error("[covertOrderFrom2OrderDTO] error,rderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
