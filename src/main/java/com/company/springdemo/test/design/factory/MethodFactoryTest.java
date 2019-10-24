package com.company.springdemo.test.design.factory;

/**
 * @description: 该类简单说明介绍工厂方法模式，有四部分组成，
 * 分别是工厂的抽象与实现、产品的抽象与实现
 * @author: whs
 * @date: 2019/09/09 19:06
 */
public class MethodFactoryTest {

    public static void main(String[] args) {
        VehicleFactory factory = new BroomFactory();
        System.out.println(factory.getClass());
        Moveable m = factory.create();
        m.run();

        // 利用反射获取工厂或者抽象产品
        VehicleFactory factory1 = getInstance("com.company.springdemo.test.design.factory.BroomFactory");
        Moveable m2 = factory1.create();
        m2.run();
    }

    public static VehicleFactory getInstance(String ClassName) {
        VehicleFactory f = null;
        try {
            f = (VehicleFactory) Class.forName(ClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}
//抽象产品角色
interface Moveable {
    void run();
}
//具体产品角色1
class Plane implements Moveable {
    @Override
    public void run() {
        System.out.println("plane....");
    }
}
//具体产品角色2
class Broom implements Moveable {
    @Override
    public void run() {
        System.out.println("broom.....");
    }
}
//抽象工厂
abstract class VehicleFactory {
    abstract Moveable create();
}
//具体工厂1
class PlaneFactory extends VehicleFactory{
    public Moveable create() {
        return new Plane();
    }
}
//具体工厂2
class BroomFactory extends VehicleFactory{
    public Moveable create() {
        return new Broom();
    }
}
