/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

package org.michael.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.michael.dataobject.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveOneTest(){
        ProductCategory productCategory = new ProductCategory("男生最爱", 11);
        ProductCategory productCategory1 = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(productCategory1);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> resultList = productCategoryRepository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,list.size());

    }
}