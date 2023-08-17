package com.llc.test;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lilichuan
 */
public class StreamTest {

    static List<Student> students = Stream.of(Student.builder().name("小张").age(16).clazz("高一1班").course("历史").score(88).build(), Student.builder().name("小李").age(16).clazz("高一3班").course("数学").score(12).build(), Student.builder().name("小王").age(17).clazz("高二1班").course("地理").score(44).build(), Student.builder().name("小红").age(18).clazz("高二1班").course("物理").score(67).build(), Student.builder().name("李华").age(15).clazz("高二2班").course("数学").score(99).build(), Student.builder().name("小潘").age(19).clazz("高三4班").course("英语").score(100).build(), Student.builder().name("小聂").age(20).clazz("高三4班").course("物理").score(32).build()).collect(Collectors.toList());

    public static void main(String[] args) {
        // 字段映射 分组显示每个课程的学生信息
        Map<String, List<Student>> filedKey = students.stream().collect(Collectors.groupingBy(Student::getCourse));
        print(filedKey);
        // 组合字段 分组现实每个班不同课程的学生信息
        Map<String, List<Student>> combineFiledKey = students.stream().collect(Collectors.groupingBy(student -> student.getClazz() + "#" + student.getCourse()));
        print(combineFiledKey);

        // 根据两级范围 将学生划分及格不及格两类
        Map<Boolean, List<Student>> customRangeKey = students.stream().collect(Collectors.groupingBy(student -> student.getScore() > 60));
        printBoolean(customRangeKey);
        // 根据多级范围 根据学生成绩来评分
        Map<String, List<Student>> customMultiRangeKey = students.stream().collect(Collectors.groupingBy(student -> {
            if (student.getScore() < 60) {
                return "C";
            } else if (student.getScore() < 80) {
                return "B";
            }
            return "A";
        }));
        print(customMultiRangeKey);


        // 计数
        Map<String, Long> groupCount = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.counting()));
        System.out.println(groupCount);

        // 求和
        Map<String, Integer> groupSum = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.summingInt(Student::getScore)));
        System.out.println(groupSum);

        // 增加平均值计算
        Map<String, Double> groupAverage = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.averagingInt(Student::getScore)));
        System.out.println(groupAverage);
        System.out.println("========================================");

        // 同组最小值
        Map<String, Optional<Student>> groupMin = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.minBy(Comparator.comparing(Student::getCourse))));
        printOption(groupMin);

        // 使用Collectors.collectingAndThen方法，处理Optional类型的数据
        Map<String, Student> groupMin2 = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse,
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Student::getCourse)), op -> op.orElse(null))));
        printStudent(groupMin2);
        // 同组最大值
        Map<String, Optional<Student>> groupMax = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.maxBy(Comparator.comparing(Student::getCourse))));

        printOption(groupMax);

        // 统计方法同时统计同组的最大值、最小值、计数、求和、平均数信息
        HashMap<String, IntSummaryStatistics> groupStat = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, HashMap::new,Collectors.summarizingInt(Student::getScore)));
        groupStat.forEach((k, v) -> {
            // 返回结果取决于用的哪种计算方式
            System.out.println("v.getAverage():"+v.getAverage());
            System.out.println("v.getCount():"+v.getCount());
            System.out.println("v.getMax():"+v.getMax());
            System.out.println("v.getMin():"+v.getMin());
            System.out.println("v.getSum():"+v.getSum());
            System.out.println("========================================");
        });



        // 切分结果，同时统计大于60和小于60分的人的信息
        Map<String, Map<Boolean, List<Student>>> groupPartition = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.partitioningBy(s -> s.getScore() > 60)));
        printGroupPartition(groupPartition);

// 同样的，我们还可以对上面两个分组的人数数据进行统计
        Map<String, Map<Boolean, Long>> groupPartitionCount = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.partitioningBy(s -> s.getScore() > 60, Collectors.counting())));

        printGroupPartitionCount(groupPartitionCount);

    }

    public static void printGroupPartitionCount(Map<String, Map<Boolean, Long>> map) {
        map.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((k, v) -> {
                System.out.println(k);
                System.out.println(v);
            });
            System.out.println("--------------------------------------------");

        });
        System.out.println("========================================");
    }

    public static void printGroupPartition(Map<String, Map<Boolean, List<Student>>> map) {
        map.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((k, v) -> {
                System.out.println(k);
                v.forEach(System.out::println);
            });
            System.out.println("--------------------------------------------");

        });
        System.out.println("========================================");
    }

    public static void printStudent(Map<String, Student> map) {
        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
        System.out.println("========================================");
    }


    public static void printOption(Map<String, Optional<Student>> map) {
        map.forEach((key, value) -> {
            System.out.println(key);
            value.ifPresent(System.out::println);
        });
        System.out.println("========================================");
    }

    public static void printBoolean(Map<Boolean, List<Student>> map) {
        map.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        });
        System.out.println("========================================");
    }

    public static void print(Map<String, List<Student>> map) {
        map.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        });
        System.out.println("========================================");
    }

}

@lombok.Data
@AllArgsConstructor
@Builder
class Student {

    private String name;
    private Integer age;
    private Integer score;
    private String clazz;
    private String course;


}
