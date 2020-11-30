package com.company.springdemo.test.jdkstream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 该类
 * @author: whs
 * @date: 2019/07/12 19:49
 */
public class CollectionsStreamTest {

    /**
     * 筛选与切片
     * filter-接收Lambda，从流中排除某些元素
     * limit-截断流，使元素不超过给定数量
     * slip(n)-跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit互补
     * distinct-筛选，通过流所生成元素的hashCode(),equals()去除重复元素
     */
    public static List<Employee> employees = Arrays.asList(
            new Employee("张三", 19, 2000),
            new Employee("张四", 33, 3000),
            new Employee("张五", 38, 4000),
            new Employee("张六", 41, 2500),
            new Employee("张七", 42, 5500),
            new Employee("张七", 43, 5500),
            new Employee("张七", 42, 5500)
    );

    @Test
    public void test1() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);
    }

    @Test
    public void test3() {
        employees.stream().filter((x) -> x.getAge() == 19).forEach(System.out::println);
        employees.stream().filter((x) -> x.getAge() == 19).forEach(employee -> {
            System.out.println(employee.getAge());
        });
        //Employee{name='张三', age=19, salary=2000} 意思就是遍历集合取出每一个对象，如果对象中的Age等于19，那么他就会通过filter
    }

    @Test
    public void test4() {
        employees.stream().filter((x) -> x.getAge() < 42).limit(2).forEach(System.out::println);
        //Employee{name='张三', age=19, salary=2000} 意思就是取出age<42的前两个对象 limit跟数据库的limit基本一样
        //Employee{name='张四', age=33, salary=3000}  注意这里的limit，如果找到指定数量的数据后，循环迭代就自己结束了，不会一直遍历
    }

    @Test
    public void test5() {
        employees.stream().filter((x) -> x.getAge() < 42).skip(2).forEach(System.out::println);
        //LambdaP.Employee{name='张五', age=38, salary=4000}
        //LambdaP.Employee{name='张六', age=41, salary=2500}
        //意思就是跳过前两个，输出满足条件的其他对象数据
    }

    @Test
    public void test6() {
        //到这一步才新添加的重复的“张七”，以上的操作时没有重复元素的
        System.out.println(employees.size());
        employees.stream().distinct().forEach(System.out::println);
        //去重操作！注意！！！！！必须重写去重目标对象中的hashCode与equals方法！
    }

    @Test
    public void test7() {
        //所有变大写
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        list.stream()
                .map((x) -> x.toUpperCase())//需要一个Function接口
                .forEach(System.out::println);
        System.out.println("**************************************");
        //取出所有的Name
        employees.stream().map(Employee::getName).distinct().forEach(System.out::println);
        /**
         * map他会将流中的每一个元素都应用到map后的表达式上！
         */
    }

    public static Stream<Character> getChar(List<String> list) {
        List<Character> lists = new ArrayList<>();
        //遍历传入的list，现在的lambda中的e为"aaa","bbb","ccc","ddd","eee"
        list.stream().forEach((e) -> {
            char[] ch = e.toCharArray();
            for (char c : ch) {
                lists.add(c);
            }
        });
        return lists.stream();
    }

    //    需求：把每个字母都转换成char
    @Test
    public void test8() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Stream<Character> aChar = getChar(list);
        aChar.forEach(System.out::println);
    }

    public static Stream<Character> getChar2(String string) {
        List<Character> list = new ArrayList<>();
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            list.add(aChar);
        }
        return list.stream();
    }

    //需求：把每个字母都转换成char
    @Test
    public void test9() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        //这里的返回是一个流套流，因为map本身会返回一个流，并且调用的方法也返回来一个流
        Stream<Stream<Character>> streamStream = list.stream().map(CollectionsStreamTest::getChar2);
        //外层的流
        streamStream.forEach((e) -> {
            //传入的e 为内层流
            e.forEach((r) -> {
                System.out.println(r);
            });
        });
    }

    //自然排序
    @Test
    public void test10() {
        List<String> list = Arrays.asList("a", "c", "r", "d", "b", "z", "1");
        list.stream().sorted().forEach(System.out::print);//1abcdrz
    }

    //利用上面集合排序，按名字排序，名字相同按工资排序
    @Test
    public void test11() {
        employees.stream().sorted((x, y) -> {
            if (x.getName().equals(y.getName())) {
                return Integer.compare(x.getSalary(), y.getSalary());
            } else {
                return x.getName().compareTo(y.getName());
            }
        }).forEach(System.out::println);
//        LambdaP.Employee{name='张七', age=42, salary=5500}
//        LambdaP.Employee{name='张三', age=19, salary=2000}
//        LambdaP.Employee{name='张五', age=38, salary=4000}
//        LambdaP.Employee{name='张六', age=41, salary=1500}
//        LambdaP.Employee{name='张六', age=41, salary=2500}
//        LambdaP.Employee{name='张四', age=33, salary=3000}
    }

    /**
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否匹配至少一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中的任意元素
     * count-返回流中总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */
    //allMatch测试
    @Test
    public void test12() {
        boolean b = employees.stream().allMatch((e) -> e.getName().getClass() == String.class);
        System.out.println(b);//true  检查名字是不是String类型的，那肯定是啊
        boolean b1 = employees.stream().allMatch((e) -> e.getSalary() == 1500);
        System.out.println(b1);//false 检查工资是否都等于1500，那肯定不是啊
    }

    //anyMatch测试
    @Test
    public void test13() {
        boolean b = employees.stream().anyMatch((e) -> e.getAge() == 41);
        System.out.println(b);
    }

    //noneMatch测试
    @Test
    public void test14() {
        boolean b = employees.stream().noneMatch((e) -> e.getSalary() == 1500);
        System.out.println(b);//false  检查是否没有匹配所有元素，如果判断条件存在于流中那么他就返回false，如果不存在那么就返回true
    }

    //findFirst测试
    @Test
    public void test15() {
        //Optional 是java 8 中新增的，为了防止空指针异常，别的帖子会解释这个类
        Optional<Employee> e = employees.stream().findFirst();
        System.out.println(e.get());
    }

    //findAny测试，findAny找到线程中的任意一个
    @Test
    public void test16() {
        //这里Stream串行流 与 parallelStream并行流 的区别
        //Stream时一条线程去找，并行流即几条线程同时去找，谁找到算谁的
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);
    }

    //count测试
    @Test
    public void test17() {
        long count = employees.stream().count();
        System.out.println(count);//6
        System.out.println(employees.size());
    }

    //max测试
    @Test
    public void test18() {
        //max与min都必须有比较条件才可以
        Optional<Employee> max = employees.stream().max((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());
        //LambdaP.Employee{name='张七', age=42, salary=5500}
    }

    //min测试
    @Test
    public void test19() {
        Optional<Employee> min = employees.stream().min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(min.get());//LambdaP.Employee{name='张三', age=19, salary=2000}
    }

    // 求和
    @Test
    public void test20() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //求和
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);//55
        /**
         * reduce第一个参数是一个起始值，第二个参数是一个二元运算，
         *      这个方法有重载，像上面这个方法的话，
         *      他每次把值都放到y，第一次x起始值为0，所以就是0+y，0+y的结果再次放到x，然后把下一个元素放到y，在进行运算，以此类推
         */
    }

    @Test
    public void test21() {
        //获取工资总和、
        //获取employees的流，然后取出每个对象的工资，然后求和
        Integer reduce = employees.stream().map(Employee::getSalary).reduce(0, (x, y) -> x + y);
        System.out.println(reduce);//18500
        //***********************************************************************
        //sum 定义如下       public static int sum(int a, int b) {
        //                      return a + b;
        //                 }
        Optional<Integer> reduce1 = employees.stream().map(Employee::getSalary).reduce(Integer::sum);
        System.out.println(reduce1.get());
        //18500这里为啥是返回Optional？因为上一个重载的reduce有起始值，再怎么滴都是有值的，那么这个是没有起始值保证的
    }

    // 收集到指定集合中！
    @Test
    public void test22() {
        //需求 ，将名字提取出来，放到List中咋办呢
        List<Employee> collect = employees.stream().collect(Collectors.toList());
        collect.stream().forEach(System.out::println);//即可输出
        System.out.println("***************");
        //如果平时我们需要的收集到的集合没有提供相应的办法咋办
        HashSet<Employee> collect1 = employees.stream().collect(Collectors.toCollection(HashSet::new));
        for (Employee employee : collect1) {
            System.out.println(employee);
        }
    }

    //可以计算各项数据
    @Test
    public void test23() {
        //求总数
        Long collect = employees.stream().collect(Collectors.counting());
        System.out.println(collect);//6
        //求最小薪资
        Optional<Employee> collect1 = employees.stream().collect(Collectors.minBy((x, y) -> Integer.compare(x.getSalary(), y.getSalary())));
        System.out.println(collect1.get());//LambdaP.Employee{name='张六', age=41, salary=1500}

        System.out.println("*************************");
        //取出工资求工资最小
        Optional<Integer> collect2 = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Integer::compare));
        System.out.println(collect2.get());//1500
        //求最大薪资
        Optional<Employee> collect3 = employees.stream().collect(Collectors.maxBy((x, y) -> x.getAge() - y.getAge()));
        System.out.println(collect3);//Optional[LambdaP.Employee{name='张七', age=42, salary=5500}]以为没有get，所以外层抱着一个Optional对象
        //求平均薪资
        Double collect4 = employees.stream().collect(Collectors.averagingInt(Employee::getSalary));
        System.out.println(collect4);//3083.3333333333335  这里平均数有三个不同的方法分别是转int，转double，转long，并且后面时自己循环，不需要自己map了
        //求和  同样是三个不同方法 转int，转double，转long
        Double collect6 = employees.stream().collect(Collectors.summingDouble(Employee::getAge));
        System.out.println(collect6);//214.0
        // 同样是三个不同方法 转int，转double，转long，但是这里是按照 指定列 把上面的所有参数都求出来的
        DoubleSummaryStatistics collect5 = employees.stream().collect(Collectors.summarizingDouble(Employee::getAge));
        System.out.println(collect5);//DoubleSummaryStatistics{count=6, sum=214.000000, min=19.000000, average=35.666667, max=42.000000}
        System.out.println(collect5.getCount());//6
        System.out.println(collect5.getMax());//42。0
    }

    //分组！！
    @Test
    public void test24() {
        //在这我才更改了上面集合的工资数据，
        Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getSalary));
        System.out.println(collect);//下面的数据就是分组后的,Map的key为分组条件，value为List的分组后的对象
        //{
        //  2000=[LambdaP.Employee{name='张三', age=19, salary=2000}, LambdaP.Employee{name='张六', age=41, salary=2000}],
        //  4000=[LambdaP.Employee{name='张五', age=38, salary=4000}],
        //  3000=[LambdaP.Employee{name='张四', age=33, salary=3000}, LambdaP.Employee{name='张六', age=41, salary=3000}],
        //  5500=[LambdaP.Employee{name='张七', age=42, salary=5500}]
        // }

        ConcurrentMap<Integer, List<Employee>> collect1 = employees.stream().collect(Collectors.groupingByConcurrent(Employee::getSalary));
        System.out.println(collect1);//还没搞清。。。。
        //{
        // 4000=[LambdaP.Employee{name='张五', age=38, salary=4000}],
        // 2000=[LambdaP.Employee{name='张三', age=19, salary=2000}, LambdaP.Employee{name='张六', age=41, salary=2000}],
        // 3000=[LambdaP.Employee{name='张四', age=33, salary=3000}, LambdaP.Employee{name='张六', age=41, salary=3000}],
        // 5500=[LambdaP.Employee{name='张七', age=42, salary=5500}]
        //}
    }

    //多级分组！！
    @Test
    public void test25() {
        //按工资分，工资一样按年龄分
        Map<Integer, Map<String, List<Employee>>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getSalary, Collectors.groupingBy((e) -> {
            if (e.getAge() < 20) {
                return "青年";
            } else if (e.getAge() < 40) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        System.out.println(collect);
        //{
        // 2000={青年=[LambdaP.Employee{name='张三', age=19, salary=2000}]},
        // 4000={中年=[LambdaP.Employee{name='张五', age=38, salary=4000}]},
        // 3000={老年=[LambdaP.Employee{name='张六', age=41, salary=3000}, LambdaP.Employee{name='张7', age=41, salary=3000}], 中年=[LambdaP.Employee{name='张四', age=33, salary=3000}]},
        // 5500={老年=[LambdaP.Employee{name='张七', age=42, salary=5500}]}
        //}
    }

    //分区 分为true与false
    @Test
    public void test26() {
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy((e) -> e.getAge() > 35));
        System.out.println(collect);
        //{
        // false=[LambdaP.Employee{name='张四', age=33, salary=3000}, LambdaP.Employee{name='张三', age=19, salary=2000}],
        // true=[LambdaP.Employee{name='张五', age=38, salary=4000}, LambdaP.Employee{name='张六', age=41, salary=3000},
        //          LambdaP.Employee{name='张7', age=41, salary=3000}, LambdaP.Employee{name='张七', age=42, salary=5500}]
        //}
    }

    //连接！
    @Test
    public void test27() {
        //参数：第一个是用什么隔开这些需要连接的字符串，第二个是字符串开头，第三个是字符串结尾
        String collect = employees.stream().map(Employee::getName).collect(Collectors.joining(",", "--", "--"));
        System.out.println("collect = " + collect);//collect = --张四,张五,张六,张三,张7,张七--
    }
}