/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

package org.michael.service;

import org.michael.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    public ProductCategory findOne(Integer categoryId);

    public List<ProductCategory> findAll();

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    public ProductCategory save(ProductCategory productCategory);

}
