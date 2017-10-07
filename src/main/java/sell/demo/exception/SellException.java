/*
 * Created by Michael
 * 17-10-6 下午3:49
 */

package sell.demo.exception;

import sell.demo.enums.ResultEnum;

public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
