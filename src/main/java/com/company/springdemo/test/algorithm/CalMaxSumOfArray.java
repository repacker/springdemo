package com.company.springdemo.test.algorithm;

/**
 * @Auther: whs
 * @Date: 2019/2/25 18:50
 * @Description: 输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)。例如:输入的数组为{1,-2,3,10,-4,7,2,-5}，
 * 和最大的子数组为{3,10,-4,7,2}，因此输出为该子数组的和18
 */
public class CalMaxSumOfArray {

    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        int result = CalMaxSumOfArray.calMaxSumOfArray(data);
        System.out.println("result:" + result);
    }

    public static int calMaxSumOfArray(int[] a) {
        if (null == a) {
            return 0;
        }
        if (a.length == 1) {
            return a[0];
        }
        int sum = a[0];
        int temp = a[0];
        for (int i = 1; i < a.length; i++) {
            if (temp < 0) {
                temp = 0;
            }
            temp = temp + a[i];
            if (sum < temp) {
                sum = temp;
            }
        }
        return sum;
    }
}
