package com.llc.test;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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



    }

    public static void printList(List<String> list) {
        System.out.println(list);
        System.out.println("===========================");
    }

}

