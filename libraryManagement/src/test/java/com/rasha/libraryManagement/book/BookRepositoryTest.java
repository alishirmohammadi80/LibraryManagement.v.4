package com.rasha.libraryManagement.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository repo;

    @Test
    public void testCreateBook() {
      Book book = new Book();
        book.setBookName("java");
        book.setNumberOfPage("125");
        book.setPublisher("Danesh sabz");
        book.setAuthorName("ali");

       Book savedBook = repo.save(book);

       Book existBook = entityManager.find(Book.class, savedBook.getId());

        assert (existBook.getBookName()).equals(book.getBookName());
        assert (existBook.getNumberOfPage()).equals(book.getNumberOfPage());
        assert (existBook.getPublisher()).equals(book.getPublisher());
        assert (existBook.getAuthorName()).equals(book.getAuthorName());

    }




}