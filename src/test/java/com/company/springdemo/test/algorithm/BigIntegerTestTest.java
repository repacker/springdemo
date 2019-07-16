package com.company.springdemo.test.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;

public class BigIntegerTestTest {

    /**
     * @Description: 该方法使用Google Guava 库中实现的布隆过滤算法 ，可以快速查找到大数据 数组中找到该重复整数的数字
     * @Param:
     * @return: QW：现在有一个非常庞大的数据，假设全是 int 类型。现在我给你一个数，你需要告诉我它是否存在其中(尽量高效)。
     * 需求其实很清晰，只是要判断一个数据是否存在即可。
     * 但这里有一个比较重要的前提：非常庞大的数据。
     */
    @Test
    public void guavaTest() {
        long star = System.currentTimeMillis();
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                10000000,
                0.01);

        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }

        Assert.assertTrue(filter.mightContain(1));
        Assert.assertTrue(filter.mightContain(2));
        Assert.assertTrue(filter.mightContain(3));
        Assert.assertFalse(filter.mightContain(10000000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }

}