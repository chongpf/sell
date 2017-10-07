/*
 * Created by Michael
 * 17-10-6 上午10:36
 */

package sell.demo.util;

import sell.demo.vo.ProductVO;
import sell.demo.vo.ResultVo;

import java.util.List;

public class ResultVoUtil {

    public static ResultVo success(List<ProductVO> productVOList) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData(productVOList);
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code,String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(message);
        return resultVo;
    }
}
