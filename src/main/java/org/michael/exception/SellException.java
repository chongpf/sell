/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 下午3:49
 */

package org.michael.exception;

import org.michael.enums.ResultEnum;

public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
