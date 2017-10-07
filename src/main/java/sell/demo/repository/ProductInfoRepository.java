/*
 * Created by Michael
 * 17-10-6 上午8:09
 */

package sell.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sell.demo.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
