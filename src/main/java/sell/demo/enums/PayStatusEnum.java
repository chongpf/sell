/*
 * Created by Michael
 * 17-10-6 下午3:03
 */

package sell.demo.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    FINISHED(1,"支付完成")
    ;
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
