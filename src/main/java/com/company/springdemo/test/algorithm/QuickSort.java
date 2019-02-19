package com.company.springdemo.test.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * @Auther: whs
 * @Date: 2019/2/18 19:54
 * @Description: 快速排序
 * 1、先找准基准元素49，将数组分为两部分，low、high为该次遍历下标。
 * 2、当low < high恒成立时，基准元素与末尾high元素比较，如果大于或等于基准元素high=high-1，否则将该末尾比较元素替换基准元素所在位置。
 * 3、接下来当low < high恒成立时，重新开始从首端low开始遍历比较，如果小于或等于基准元素low=low+1，否则将该首端比较元素替换基准元素所在位置。
 * 4、当low < high恒成立时，重新执行步骤2、3
 * 5、分成两部分后，分别递归执行步骤1、2、3、4公共逻辑，完成有序遍历
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64};
        quick(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    public static int getMiddle(int[] list, int low, int high) {
        System.out.println("开始：low:" + low + ",high:" + high + "===" + JSON.toJSON(list));
        //数组的第一个作为中轴
        int temp = list[low];
        while (low < high) {
            while (low < high && list[high] >= temp) {
                System.out.println("步骤右端---low:" + low + ",high:" + high + "===" + JSON.toJSON(list));
                high--;
            }
            //比中轴小的记录移到低端
            list[low] = list[high];
            while (low < high && list[low] <= temp) {
                System.out.println("步骤左端---low:" + low + ",high:" + high + "===" + JSON.toJSON(list));
                low++;
            }
            //比中轴大的记录移到高端
            list[high] = list[low];
        }
        //中轴记录到尾
        list[low] = temp;
        //返回中轴的位置
        return low;
    }

    public static void quickSort(int[] list, int low, int high) {
        if (low < high) {
            //将list数组进行一分为二排序
            int middle = getMiddle(list, low, high);
            //对低字表进行递归排
            quickSort(list, low, middle - 1);
            //对高字表进行递归排
            quickSort(list, middle + 1, high);
        }
    }

    public static void quick(int[] a) {
        //查看数组是否为空
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }
}
