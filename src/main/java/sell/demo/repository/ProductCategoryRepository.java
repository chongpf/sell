package sell.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sell.demo.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> catagoryTypeList);

}
