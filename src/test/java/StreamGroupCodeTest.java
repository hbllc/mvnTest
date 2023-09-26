import com.llc.test.stream.Student;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lilichuan
 */
public class StreamGroupCodeTest {

    static List<Student> students = Stream.of(Student.builder().name("小张").age(16).clazz("高一1班").course("历史").score(88).build(), Student.builder().name("小李").age(16).clazz("高一3班").course("数学").score(12).build(), Student.builder().name("小王").age(17).clazz("高二1班").course("地理").score(44).build(), Student.builder().name("小红").age(18).clazz("高二1班").course("物理").score(67).build(), Student.builder().name("李华").age(15).clazz("高二2班").course("数学").score(99).build(), Student.builder().name("小潘").age(19).clazz("高三4班").course("英语").score(100).build(), Student.builder().name("小聂").age(20).clazz("高三4班").course("物理").score(32).build()).collect(Collectors.toList());


    @Test
    public void test() {
        // 同组最小值
        Map<String, Optional<Student>> groupMin = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.minBy(Comparator.comparing(Student::getCourse))));
        printOption(groupMin);
    }


    private static void printCoureWithStudentScore2(Map<String, LinkedHashMap<String, Integer>> courseWithStudentScore2) {
        courseWithStudentScore2.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
        System.out.println("========================================");
    }

    private static void printCoureWithStudentScore(Map<String, Map<String, Integer>> courseWithStudentScore) {
        courseWithStudentScore.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
        System.out.println("========================================");
    }

    private static void printCourseMax(Map<String, Optional<Student>> groupCourseMax) {
        groupCourseMax.forEach((k, v) -> {
            System.out.println(k);
            v.ifPresent(System.out::println);
        });
        System.out.println("========================================");
    }

    private static void printCourseSum(Map<String, Integer> groupCalcSum) {
        groupCalcSum.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
        System.out.println("========================================");
    }


    public static void printGroupAngPartitionCount(Map<String, Map<Boolean, Map<Boolean, List<Student>>>> map) {
        map.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((k, v) -> {
                System.out.println(k);
                value.forEach((k1, v1) -> {
                    System.out.println(k1);
                    v1.forEach((kk, vv) -> {
                        System.out.println(kk);
                        System.out.println(vv);
                    });
                });
            });
            System.out.println("--------------------------------------------");

        });
        System.out.println("========================================");
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
