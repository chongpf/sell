/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 上午9:38
 */

package org.michael.vo;

import lombok.Data;

@Data
public class ResultVo<T> {

    //error code
    private Integer code;

    //message
    private String msg;

    //content
    private T data;
}
