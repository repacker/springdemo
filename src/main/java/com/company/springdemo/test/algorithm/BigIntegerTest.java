package com.company.springdemo.test.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Auther: whs
 * @Date: 2019/2/18 11:01
 * @Description: BigIntegerTestTest
 */
public class BigIntegerTest {

    public static void main(String[] args) {
        long star = System.currentTimeMillis();
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                10000000,
                0.01);

        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }
}
