/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 上午9:01
 */

package org.michael.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.michael.dataobject.ProductInfo;
import org.michael.dto.CartDTO;

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
