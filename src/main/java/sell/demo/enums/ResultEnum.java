/*
 * Created by Michael
 * 17-10-6 下午3:46
 */

package sell.demo.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不足"),
    ORDER_MASTER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不对"),
    ORDER_UPDATE_ERROR(15,"取消订单，更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品详情"),
    ORDER_FINISH_STATUS_ERROR(17,"状态不正确无法完结订单"),
    ORDER_FINISH_UPDATE_ERROR(18,"完结订单，更新失败"),
    ORDER_PAY_STATUS_ERROR(19,"状态不正确无法支付订单"),
    ORDER_PAY_UPDATE_ERROR(20,"支付订单，更新失败"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
