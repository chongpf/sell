/*
 * Created by Michael
 * 17-10-6 下午4:38
 */

package sell.demo.dto;

import lombok.Data;

@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
