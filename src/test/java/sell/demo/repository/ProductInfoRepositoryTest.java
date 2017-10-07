/*
 * Created by Michael
 * 17-10-6 上午8:45
 */

package sell.demo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sell.demo.dataobject.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        ProductInfo info = repository.save(productInfo);
        Assert.assertEquals("123456", info.getProductId());
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfo = repository.findByProductStatus(new Integer(0));

        Assert.assertNotEquals(0,productInfo.size());
    }

}