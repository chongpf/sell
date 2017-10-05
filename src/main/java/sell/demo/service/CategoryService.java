package sell.demo.service;

import sell.demo.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    public ProductCategory findOne(Integer categoryId);

    public List<ProductCategory> findAll();

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    public ProductCategory save(ProductCategory productCategory);

}
