/*
 * Created by Michael
 * 17-10-6 下午3:56
 */

package sell.demo.dto;

import lombok.Data;
import sell.demo.dataobject.OrderDetail;
import sell.demo.dataobject.OrderMaster;
import sell.demo.enums.OrderStatusEnum;
import sell.demo.enums.PayStatusEnum;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private List<OrderDetail> orderDetailList;

    public OrderDTO() {
    }
}
