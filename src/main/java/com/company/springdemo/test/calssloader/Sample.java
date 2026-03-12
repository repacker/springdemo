package com.company.springdemo.test.calssloader;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: whs
 * @time: 2022/3/14 7:05 PM
 */
public class Sample {

    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }

    static void testClassIdentity() {
        String classDataRootPath = "/Users/wanghongshuai/Downloads/workplace/springdemo/target/classes/";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.company.springdemo.test.calssloader.Sample";
        try {
            Class<?> class1 = fscl1.findClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.findClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Sample.class.getClassLoader());
        testClassIdentity();
    }
}
