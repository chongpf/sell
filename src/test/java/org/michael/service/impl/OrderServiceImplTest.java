/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 下午5:00
 */

package org.michael.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.michael.enums.PayStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.michael.dataobject.OrderDetail;
import org.michael.dto.OrderDTO;
import org.michael.enums.OrderStatusEnum;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    private String BUYER_OPENID = "110110";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("种子");
        orderDTO.setBuyerAddress("shanghai");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("18512341234");

        //cart
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(10);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123457");
        orderDetail2.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);
        System.out.println(orderDTO);
        orderService.create(orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1507282711565558319");
        Assert.assertEquals("1507282711565558319", orderDTO.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0, 20);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getContent().size());
    }

    @Test
    public void cancel() throws Exception {
//        OrderDTO orderDTO = orderService.findOne("1507281477356431291");
//        OrderDTO result = orderService.cancel(orderDTO);
//        Assert.assertEquals(OrderStatusEnum.CANCLE.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1507281477356431291");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());

        String s = "1";

        s="2";
        s="3";


    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1507281477356431291");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.FINISHED.getCode(), result.getPayStatus());
    }
}