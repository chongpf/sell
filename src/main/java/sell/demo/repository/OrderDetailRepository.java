/*
 * Created by Michael
 * 17-10-6 下午3:12
 */

package sell.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sell.demo.dataobject.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);

}
