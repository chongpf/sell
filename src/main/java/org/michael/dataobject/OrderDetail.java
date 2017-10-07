/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 上午7:59
 */

package org.michael.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@DynamicUpdate
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;

    public OrderDetail() {
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "detailId='" + detailId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productIcon='" + productIcon + '\'' +
                '}';
    }
}
