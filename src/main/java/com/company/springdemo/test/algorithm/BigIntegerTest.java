package com.company.springdemo.test.algorithm;

import java.util.HashSet;

/**
 * @Auther: whs
 * @Date: 2019/2/18 11:01
 * @Description: BigIntegerTestTest
 */
public class BigIntegerTest {

    public static void main(String[] args) {
        long star = System.currentTimeMillis();
//        BloomFilter<Integer> filter = BloomFilter.create(
//                Funnels.integerFunnel(),
//                10000000,
//                0.01);
//
//        for (int i = 0; i < 10000000; i++) {
//            filter.put(i);
//        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
        System.out.println(test3(3));

        HashSet set = new HashSet();
        System.out.println(set.add(null));
        System.out.println(set.add(null));
        set.add(null);
    }

    private static int test3(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return test3(n - 1) + test3(n - 2);
        }
    }
}
