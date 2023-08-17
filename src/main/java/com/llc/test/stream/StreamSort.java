package com.llc.test.stream;

import java.util.Comparator;
import java.util.List;

/**
 * @author lilichuan
 */
public class StreamSort {

    public static void main(String[] args) {
        List<Student> list = List.of(Student.builder().build());
        //返回 对象集合以Student getName升序排序
        list.sort(Comparator.comparing(Student::getName));
        list.stream().sorted(Comparator.comparing(Student::getName));

//返回 对象集合以Student getName降序排序 注意两种写法

        list.stream().sorted(Comparator.comparing(Student::getName).reversed());//先以getName升序,结果进行getName降序

        list.stream().sorted(Comparator.comparing(Student::getName, Comparator.reverseOrder()));//以getName降序

//返回 对象集合以Student getName升序 getCourse升序

        list.stream().sorted(Comparator.comparing(Student::getName).thenComparing(Student::getCourse));

//返回 对象集合以Student getName降序 getCourse升序 注意两种写法

        list.stream().sorted(Comparator.comparing(Student::getName).reversed().thenComparing(Student::getCourse));//先以getName升序,升序结果进行getName降序,再进行getCourse升序

        list.stream().sorted(Comparator.comparing(Student::getName, Comparator.reverseOrder()).thenComparing(Student::getCourse));//先以getName降序,再进行getCourse升序

//返回 对象集合以Student getName降序 getCourse降序 注意两种写法

        list.stream().sorted(Comparator.comparing(Student::getName).reversed().thenComparing(Student::getCourse, Comparator.reverseOrder()));//先以getName升序,升序结果进行getName降序,再进行getCourse降序

        list.stream().sorted(Comparator.comparing(Student::getName, Comparator.reverseOrder()).thenComparing(Student::getCourse, Comparator.reverseOrder()));//先以getName降序,再进行getCourse降序

//返回 对象集合以Student getName升序 getCourse降序 注意两种写法

        list.stream().sorted(Comparator.comparing(Student::getName).reversed().thenComparing(Student::getCourse).reversed());//先以getName升序,升序结果进行getName降序,再进行getCourse升序,结果进行getName降序getCourse降序

        list.stream().sorted(Comparator.comparing(Student::getName).thenComparing(Student::getCourse, Comparator.reverseOrder()));//先以getName升序,再进行getCourse降序<br><br><br>


    }

}