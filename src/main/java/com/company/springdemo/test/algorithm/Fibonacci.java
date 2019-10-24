package com.company.springdemo.test.algorithm;

/**
 * @description: 该类 斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39
 * 1、尝试递归、非递归的性能的优化、异同
 * @author: whs
 * @date: 2019/09/10 20:03
 */
public class Fibonacci {

    public int count = 0;

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println("非递归计算：" + fibonacci.fibonacci(10));
        System.out.println("非递归计算调用函数次数：" + fibonacci.count);

        Fibonacci fibonacci2 = new Fibonacci();
        System.out.println("递归计算：" + fibonacci2.fibonacciRecursion(10));
        System.out.println("递归计算调用函数次数：" + fibonacci2.count);

    }

    // 递归算法
    public int fibonacciRecursion(int n) {
        count++;
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }

    // 非递归算法
    public int fibonacci(int n) {
        count++;
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int result = 0;
        int preResult = 1; // n - 2项
        int currentResult = 1; // n - 1项
        for (int i = 3; i <= n; i++) {
            result = preResult + currentResult; // n = f(n-1) + f(n-2)
            preResult = currentResult;          // f(n-2) = f(n-1)
            currentResult = result;            // f(n-1) = n
        }
        return result;
    }
}
