/*
 * Created by Michael
 * 17-10-6 下午2:50
 */

package sell.demo.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0,"新下单"),
    FINISHED(1,"完结"),
    CANCLE(2,"取消")
    ;
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
