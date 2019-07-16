package com.company.springdemo.test.jdkstream.forkjoin;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 该类 归并排序的简单实现
 * @author: whs
 * @date: 2019/07/13 11:19
 */
public class Merge1 {
    private static int MAX = 10000;

    private static int inits[] = new int[MAX];

    // 这是为了生成一个数量为MAX的随机整数集合，准备计算数据
    // 和算法本身并没有什么关系
    static {
        Random r = new Random();
        for (int index = 1; index <= MAX; index++) {
            inits[index - 1] = r.nextInt(10000000);
        }
    }

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        int results[] = forkits(inits);
        long endTime = System.currentTimeMillis();
        // 如果参与排序的数据非常庞大，记得把这种打印方式去掉
        System.out.println("耗时=" + (endTime - beginTime) + " | " + Arrays.toString(results));
    }

    // 拆分成较小的元素或者进行足够小的元素集合的排序
    private static int[] forkits(int source[]) {
        int sourceLen = source.length;
        if (sourceLen > 2) {
            int midIndex = sourceLen / 2;
            int result1[] = forkits(Arrays.copyOf(source, midIndex));
            int result2[] = forkits(Arrays.copyOfRange(source, midIndex, sourceLen));
            // 将两个有序的数组，合并成一个有序的数组
            int mer[] = joinInts(result1, result2);
            return mer;
        }
        // 否则说明集合中只有一个或者两个元素，可以进行这两个元素的比较排序了
        else {
            // 如果条件成立，说明数组中只有一个元素，或者是数组中的元素都已经排列好位置了
            if (sourceLen == 1
                    || source[0] <= source[1]) {
                return source;
            } else {
                int targetp[] = new int[sourceLen];
                targetp[0] = source[1];
                targetp[1] = source[0];
                return targetp;
            }
        }
    }

    /**
     * 这个方法用于合并两个有序集合
     *
     * @param array1
     * @param array2
     */
    public static int[] joinInts(int array1[], int array2[]) {
        int destInts[] = new int[array1.length + array2.length];
        int array1Len = array1.length;
        int array2Len = array2.length;
        int destLen = destInts.length;

        // 只需要以新的集合destInts的长度为标准，遍历一次即可
        for (int index = 0, array1Index = 0, array2Index = 0; index < destLen; index++) {
            int value1 = array1Index >= array1Len ? Integer.MAX_VALUE : array1[array1Index];
            int value2 = array2Index >= array2Len ? Integer.MAX_VALUE : array2[array2Index];
            // 如果条件成立，说明应该取数组array1中的值
            if (value1 < value2) {
                array1Index++;
                destInts[index] = value1;
            }
            // 否则取数组array2中的值
            else {
                array2Index++;
                destInts[index] = value2;
            }
        }

        return destInts;
    }

}
