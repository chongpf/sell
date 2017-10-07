/*
 * Created by Michael
 * 17-10-6 上午9:38
 */

package sell.demo.vo;

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
