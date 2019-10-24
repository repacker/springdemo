//package com.company.springdemo.test.design.factory;
//
///**
// * @description: 该类 测试 运行抽象工厂模式
// * @author: whs
// * @date: 2019/09/09 19:20
// */
//public class AbsFactoryTest {
//    public static void main(String[] args) {
//        AbstractFactory f = new DefaultFactory();
//        Vehicle v = f.createVehicle();
//        v.run();
//        Weapon w = f.createWeapon();
//        w.shoot();
//        Food a = f.createFood();
//        a.printName();
//    }
//}
////抽象工厂类
//public abstract class AbstractFactory {
//    public abstract Vehicle createVehicle();
//    public abstract Weapon createWeapon();
//    public abstract Food createFood();
//}
////具体工厂类，其中Food,Vehicle，Weapon是抽象类，
//public class DefaultFactory extends AbstractFactory{
//    @Override
//    public Food createFood() {
//        return new Apple();
//    }
//    @Override
//    public Vehicle createVehicle() {
//        return new Car();
//    }
//    @Override
//    public Weapon createWeapon() {
//        return new AK47();
//    }
//}
