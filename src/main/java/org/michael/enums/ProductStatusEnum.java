/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 上午9:07
 */

package org.michael.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {

    UP(0,"正常"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
