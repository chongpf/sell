/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

package org.michael.repository;

import org.michael.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> catagoryTypeList);

}
