package com.company.springdemo.test;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello main Test!!!");
        Person person = new Person();
    }

    public static class Person{
        public static void main(String[] args) {
            System.out.println("Hello Person!!!");
        }
    }
}
