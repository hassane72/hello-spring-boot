package org.xiaoqiaotq;

import org.junit.Test;
import org.xiaoqiaotq.domain.Book;

import java.io.*;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/14
 */
public class OptionalTest {
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
}
