/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-7 上午11:26
 */

package org.michael.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerOrderControllerTest {

    @Autowired
    private BuyerOrderController buyerOrderController;

    @Test
    public void create() throws Exception {
     /*
     name: "damao"
     phone: "18612341234"
     address: "shanghai-changning"
     openid: "110111"
     items: [{productId: "123456",productQuantity: "10"},{productId: "123457",productQuantity: "10"}]
     */

     /*
name:张三
phone:18612341234
address:上海松江
openid:110112
items:[{productId: "123456",productQuantity: "10"},{productId: "123457",productQuantity: "10"}]
      */
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
     System.out.println("--------------" + format.format(System.currentTimeMillis()));
    }

}