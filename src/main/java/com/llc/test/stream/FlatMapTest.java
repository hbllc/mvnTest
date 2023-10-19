package com.llc.test.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lilichuan
 */
public class FlatMapTest {
        public static void main(String[] args) {
            System.out.println("---------->存储的图书信息: ");
            System.out.println(initInfo());
            System.out.println("---------->测试map方法:");
            testMap();
            System.out.println("---------->测试flatMap方法:");
            testFlatMap();
        }
        private static void testMap() {
            initInfo().stream()
                .map(library -> library.getBook())
                .forEach(book -> System.out.println(book));
        }

        private static void testFlatMap() {
            initInfo().stream()
                .flatMap(library -> library.getBook().stream())
                .forEach(book -> System.out.println(book));

            List<BookTest> collect = initInfo().stream().map(Library::getBook).flatMap(List::stream).collect(Collectors.toList());
        }

        public static List<Library> initInfo() {
            Library library1 = new Library("新华图书", null);
            Library library2 = new Library("大家图书", null);
            Library library3 = new Library("瀚海图书", null);

            BookTest book1 = new BookTest("西游记", "吴承恩", 49);
            BookTest book2 = new BookTest("水浒传", "施耐庵", 57);
            BookTest book3 = new BookTest("三国演义", "罗贯中", 52);
            BookTest book4 = new BookTest("朝花夕拾", "鲁迅", 30);

            List<BookTest> library1Book = new ArrayList<>();
            List<BookTest> library2Book = new ArrayList<>();
            List<BookTest> library3Book = new ArrayList<>();

            library1Book.add(book1);
            library1Book.add(book2);

            library2Book.add(book2);
            library2Book.add(book3);

            library3Book.add(book3);
            library3Book.add(book4);

            library1.setBook(library1Book);
            library2.setBook(library2Book);
            library3.setBook(library3Book);

            return new ArrayList<>(Arrays.asList(library1, library2, library3));
        }
    }
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class Library {
    private String name;
    private List<BookTest> book;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class BookTest {
    private String name;
    private String author;
    private Integer price;
}
