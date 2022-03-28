package com.rasha.libraryManagement.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void setAuthorName() {
        String Actual ="ali";
        Book book = new Book();
        book.setAuthorName("ali");
       String Expected =book.getAuthorName();
     assertEquals(Expected, Actual);
    }

    @Test
    void setPublisher() {
        String Actual ="Danesh sabz";
        Book book = new Book();
        book.setPublisher("Danesh sabz");
        String Expected =book.getPublisher();
        assertEquals(Expected, Actual);

    }


    @Test
    void setBookName() {
        String Actual ="Thinking in Java";
        Book book = new Book();
        book.setBookName("Thinking in Java");
        String Expected =book.getBookName();
        assertEquals(Expected, Actual);
    }

    @Test
    void setNumberOfPage() {
        String Actual ="152";
        Book book = new Book();
        book.setNumberOfPage("152");
        String Expected =book.getNumberOfPage();
        assertEquals(Expected, Actual);

    }
}