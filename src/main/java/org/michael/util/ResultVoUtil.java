/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 上午10:36
 */

package org.michael.util;

import org.michael.vo.ResultVo;
import org.michael.dto.OrderDTO;

import java.util.List;
import java.util.Map;

public class ResultVoUtil {

    public static ResultVo success(List list) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData(list);
        return resultVo;
    }

    public static ResultVo success(Map<String, String> map) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData(map);
        return resultVo;
    }

    public static ResultVo success(OrderDTO orderDTO) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData(orderDTO);
        return resultVo;
    }
//    public static ResultVo success(List<OrderDTO> orderDTOList) {
//        ResultVo resultVo = new ResultVo();
//        resultVo.setCode(0);
//        resultVo.setMsg("success");
//        resultVo.setData(orderDTOList);
//        return resultVo;
//    }

    public static ResultVo success() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData(null);
        return resultVo;
    }

    public static ResultVo error(Integer code, String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(message);
        return resultVo;
    }
}
