package sell.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sell.demo.dataobject.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{

}
