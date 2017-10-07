/*
 * Created by Michael
 * 17-10-6 下午3:08
 */

package sell.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sell.demo.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
}
