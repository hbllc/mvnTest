package com.llc.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lilichuan
 */
public class StreamTest {
    static List<Student> students = Stream.of(
            Student.builder().name("小张").age(16).clazz("高一1班").course("历史").score(88).build(),
            Student.builder().name("小李").age(16).clazz("高一3班").course("数学").score(12).build(),
            Student.builder().name("小王").age(17).clazz("高二1班").course("地理").score(44).build(),
            Student.builder().name("小红").age(18).clazz("高二1班").course("物理").score(67).build(),
            Student.builder().name("李华").age(15).clazz("高二2班").course("数学").score(99).build(),
            Student.builder().name("小潘").age(19).clazz("高三4班").course("英语").score(100).build(),
            Student.builder().name("小聂").age(20).clazz("高三4班").course("物理").score(32).build()
    ).collect(Collectors.toList());

    public static void main(String[] args) {
        // 检视元素
        List<String> peek = students.stream()
                .filter(s -> s.getScore() > 60)
                .peek(System.out::println)
                // peek还可以用来修改元素内容
                .peek(s -> s.setScore(60))
                .peek(System.out::println)
                .map(Student::getName)
                .peek(System.out::println)
                .collect(Collectors.toList());
        printList(peek);


        // 流转集合
        List<Student> studentList = students.stream().collect(Collectors.toList());
// 使用该方法，可以把流转map或任意集合对象
        HashMap<String, Student> map = students.stream().collect(HashMap::new, (hashMap, student) -> hashMap.put(student.getName(), student), HashMap::putAll);
        System.out.println(studentList);
        System.out.println(map);
        System.out.println("===========================");

        // 获取学生姓名的新列表
        List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());
        List<Integer> ages = students.stream().mapToInt(Student::getAge).boxed().collect(Collectors.toList());
        System.out.println(names);
        System.out.println(ages);
        System.out.println("===========================");

        // 合并所有姓名
        String reduce = students.stream().reduce(new StringBuffer(), (result, student) -> result.append(student.getName()), (r1, r2) -> r1.append(r2.toString())).toString();
// 简单写法，通过map和reduce操作的显式组合，能更简单的表示
        Optional<String> reduce1 = students.stream().map(Student::getName).reduce(String::concat);
// 当然如果只是简单的字符串拼接，完全可以直接使用Collectors.joining的连接函数来实现
        String reduce2 = students.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(reduce2);
        System.out.println(reduce1);
        System.out.println(reduce);


        // 这里标识值为0，累加器将会从如下等式开始累加计算：0 + student.getScore()
        Integer reduce4 = students.stream().reduce(0, (integer, student) -> integer + student.getScore(), Integer::sum);
// 这里标识值为10，累加器将会从如下等式开始累加计算：10 + student.getScore()
        Integer reduce3 = students.stream().reduce(10, (integer, student) -> integer + student.getScore(), Integer::sum);
        System.out.println(reduce4);
        System.out.println(reduce3);


        // 只获取高一的数学成绩
        List<Student> math = students.stream()
                .filter(student -> student.getClazz().contains("高一") && "数学".equals(student.getCourse()))
                .collect(Collectors.toList());
        System.out.println(math);
        System.out.println("===========================");


        // 取第二页，每页五条数据
        List<Student> page = students.stream().skip(5).limit(5).collect(Collectors.toList());
        System.out.println(page);
        System.out.println("===========================");


        // 按成绩排序，成绩相同时根据年龄排倒序
        List<Student> sorted = students.stream()
                .sorted(Comparator.comparing(Student::getScore, Comparator.reverseOrder())
                        .thenComparing(Student::getAge, Comparator.reverseOrder()))
                .collect(Collectors.toList());
// 普通写法
        List<Student> sorted2 = students.stream()
                .sorted((c1, c2) -> {
                    if (c1.getScore() > c2.getScore()) {
                        return -1;
                    } else if (c1.getScore().equals(c2.getScore())) {
                        if (c1.getAge() > c2.getAge()) {
                            return -1;
                        } else if (c1.getAge() < c2.getAge()) {
                            return 1;
                        }
                        return 0;
                    }
                    return 1;
                })
                .collect(Collectors.toList());
        System.out.println(sorted);
        System.out.println(sorted2);

        // 分数的最大值 最小值
        Student max = students.stream().max(Comparator.comparing(Student::getScore)).orElse(null);
        Student min = students.stream().min(Comparator.comparing(Student::getScore)).orElse(null);
// 也可以通过如下写法获取最大值，这种写法只能获取到值，没法关联用户，跟上面的写法各有用途
        int max2 = students.stream().mapToInt(Student::getScore).max().orElse(0);
        int min2 = students.stream().mapToInt(Student::getScore).min().orElse(0);
        System.out.println(max);
        System.out.println(min);


        // 计数求及格的学生人数
        long count = students.stream().filter(student -> student.getScore() > 60).count();
// 求分数总和
        Integer sum = students.stream().map(Student::getScore).reduce(Integer::sum).orElse(-1);
        Integer sum2 = students.stream().mapToInt(Student::getScore).sum();
// 分数的平均值
        double average = students.stream().mapToDouble(Student::getScore).average().orElse(0D);
        System.out.println(count + "-" + sum + "-" + average);


        // 将不同课程的学生进行分类
        HashMap<String, List<Student>> groupByCourse = (HashMap<String, List<Student>>) students.stream()
                .collect(Collectors.groupingBy(Student::getCourse));
// 上面的方法中，最终返回默认是HashMap，键值对中的值默认是ArrayList，可以通过下面的方法自定义返回结果、值的类型
        HashMap<String, List<Student>> groupByCourse1 = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, HashMap::new, Collectors.toList()));
// 增加映射功能，将值设置为名字
        HashMap<String, List<String>> groupMapping = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, HashMap::new, Collectors.mapping(Student::getName, Collectors.toList())));
// 增加合并函数，计算每科总分
        HashMap<String, Integer> groupCalcSum = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, HashMap::new, Collectors.reducing(0, Student::getScore, Integer::sum)));
// 增加平均值计算
        HashMap<String, Double> groupCalcAverage = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, HashMap::new, Collectors.averagingDouble(Student::getScore)));
        System.out.println(groupByCourse);
        System.out.println(groupMapping);
        System.out.println(groupCalcSum);
        System.out.println(groupCalcAverage);

        // 去重 统计所有科目
        List<String> courses = students.stream().map(Student::getCourse).distinct().collect(Collectors.toList());
        System.out.println(courses);
        System.out.println("===========================");

        boolean b = students.stream().anyMatch(student -> student.getScore() >= 60);
        System.out.println(b);
        boolean b1 = students.stream().allMatch(student -> student.getScore() >= 60);
        System.out.println(b1);
        System.out.println("===========================");

        students.stream().takeWhile(student -> student.getScore() == 60).forEach(System.out::println);
        System.out.println("===========================");
        students.stream().dropWhile(student -> student.getScore() >= 60).forEach(System.out::println);

        System.out.println("===========================");
        List<Integer> numberList = Arrays.asList(11,1, 3, 5, 8, 10, 20, 35, 2, 5, 7);
        numberList.stream().sorted().takeWhile(num -> num <= 10).forEach(System.out::println);
        System.out.println("===========================");
        numberList.stream().sorted().dropWhile(num -> num <= 10).forEach(System.out::println);
        System.out.println("===========================");

        students.stream().filter(student -> student.getScore() > 10).findFirst().ifPresent(System.out::println);
        System.out.println("===========================");
        students.stream().sorted(Comparator.comparing(Student::getAge)).filter(student -> student.getScore() >= 60).findAny().ifPresent(System.out::println);
        System.out.println("===========================");
        System.out.println("========并发=======");
        students.stream().filter(student -> student.getScore() > 10).findAny().ifPresent(System.out::println);
        students.parallelStream().filter(student -> student.getScore() > 10).findAny().ifPresent(System.out::println);
        System.out.println("===========================");
        students.stream().filter(student -> student.getScore() > 10).findFirst().ifPresent(System.out::println);
        students.parallelStream().filter(student -> student.getScore() > 10).findFirst().ifPresent(System.out::println);




    }

    public static void printList(List<String> list) {
        System.out.println(list);
        System.out.println("===========================");
    }

}

