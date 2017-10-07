/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 上午9:03
 */

package org.michael.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.michael.dataobject.ProductInfo;
import org.michael.dto.CartDTO;
import org.michael.enums.ProductStatusEnum;
import org.michael.enums.ResultEnum;
import org.michael.exception.SellException;
import org.michael.repository.ProductInfoRepository;
import org.michael.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
       for(CartDTO cartDTO : cartDTOList){
           ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
           if (cartDTO == null) {
               throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
           }

           Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();

           productInfo.setProductStock(result);
           repository.save(productInfo);
       }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (cartDTO == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
}
