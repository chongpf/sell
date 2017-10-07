/*
 * Created by Michael
 * 17-10-6 上午9:01
 */

package sell.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sell.demo.dataobject.ProductInfo;
import sell.demo.dto.CartDTO;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //ADD
    void increaseStock(List<CartDTO> cartDTOList);

    //Delete
    void decreaseStock(List<CartDTO> cartDTOList);
}
