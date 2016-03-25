package org.xiaoqiaotq;

import org.junit.Test;
import org.xiaoqiaotq.domain.Book;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/14
 */
public class OptionalTest <T>{
    @Test
    public void test() throws Exception {
        Book book = new Book();
        book.setName("dfsafasdf");
        Optional<Book> o = Optional.of(book);
        String name = o.map(Book::getName).orElseThrow(RuntimeException::new);
        System.err.println(name);
    }
    @Test
    public void test2() throws Exception {
        Book book = new Book();
        book.setName(null);
        Optional<Book> o = Optional.of(book);
        Integer name = o.map(Book::getName).map(Integer::parseInt).orElseThrow(()->new RuntimeException("asdf"));
        System.err.println(name);
    }
    @Test
    public void test3() throws Exception {
        List<Integer> integers = Arrays.asList(new Integer[]{1, 3, 5, 6, 8, 2});
        List<Integer> integers2 = Arrays.asList(new Integer[]{1, 2,3, 5, 6, 8});
        System.err.println(integers.equals(integers2));
        Collections.sort(integers);
        System.err.println(integers);
        System.err.println(integers.equals(integers2));
    }
    @Test
    public void test4() throws Exception {

    }
    private  Stream<T> stream;
    public <T,R>  Stream<R> map(Function<T,R> function){
        return null;
    }
    public <T,R>  Stream<R> filter(Predicate<T> predicate){
        return null;
    }
}
