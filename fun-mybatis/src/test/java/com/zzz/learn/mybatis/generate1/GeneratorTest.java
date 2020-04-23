package com.zzz.learn.mybatis.generate1;

import com.alibaba.fastjson.JSON;
import com.zzz.learn.mybatis.ConfigTest;
import com.zzz.learn.mybatis.generate1.mapper.ItemsMapper;
import com.zzz.learn.mybatis.generate1.po.Items;
import com.zzz.learn.mybatis.generate1.po.ItemsExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mybatis-generator-spring-test.xml"})
public class GeneratorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigTest.class);

    @Autowired
    private ItemsMapper itemsMapper;


    //根据主键删除
    @Test
    public void testDeleteByPrimaryKey() {

    }

    @Test
    public void testInsert() {
        Items items = new Items();
        items.setName("手机");
        items.setPrice(999f);
        items.setCreateTime(new Date());
        int row = itemsMapper.insert(items);
    }

    //自定义条件查询
    @Test
    public void testSelectByExample() {
        ItemsExample itemsExample = new ItemsExample();
        //通过criteria构造查询条件
        ItemsExample.Criteria criteria = itemsExample.createCriteria();
        criteria.andNameEqualTo("笔记本");
        //可能返回多条记录
        List<Items> list = itemsMapper.selectByExample(itemsExample);

       LOGGER.info(JSON.toJSONString(list));

    }

    //根据主键查询
    @Test
    public void testSelectByPrimaryKey() {
        Items items = itemsMapper.selectByPrimaryKey(1);
        LOGGER.info(JSON.toJSONString(items));
    }

    //更新数据
    @Test
    public void testUpdateByPrimaryKey() {

        //对所有字段进行更新，需要先查询出来再更新
        Items items = itemsMapper.selectByPrimaryKey(1);

        items.setName("手机");

        itemsMapper.updateByPrimaryKey(items);
        //如果传入字段不空为才更新，在批量更新中使用此方法，不需要先查询再更新
        //itemsMapper.updateByPrimaryKeySelective(record);

    }


}
