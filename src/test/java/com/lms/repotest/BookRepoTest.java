package com.lms.repotest;


import com.lms.domain.Author;
import com.lms.domain.Book;
import com.lms.domain.Image;
import com.lms.domain.Publisher;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.AuthorRepository;
import com.lms.repository.BookRepository;
import com.lms.repository.ImageRepository;
import com.lms.repository.PublisherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class BookRepoTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ImageRepository imageRepository;

    MultipartFile file;

    @Test
    public void testFindById() {
        Book book = createBook();
        bookRepository.save(book);
        Book result = bookRepository.findById(book.getId()).get();
        assertEquals(book.getId(), result.getId());
    }
    @Test
    public void testFindAll() {
        Book book = createBook();
        bookRepository.save(book);
        List<Book> result = new ArrayList<>();
        bookRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 1);
    }

    @Test
    public void testSave() {
        Book book = createBook();
        bookRepository.save(book);
        Book found = bookRepository.findById(book.getId()).get();
        assertEquals(book.getId(), found.getId());
    }
    @Test
    public void testDeleteById() {
        Book book = createBook();
        bookRepository.save(book);
        bookRepository.deleteById(book.getId());
        List<Book> result = new ArrayList<>();
        bookRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }

    private Book createBook() {
          //image ı save ettikten sonra return olan id
          //TODO:create image ile yapılacak
          Image image=imageRepository.findById("d04d9400-50e9-47bf-a180-2027508c6a70").get();

          Book book = new Book();
          Author author=authorRepository.findById(1L).get();
          Publisher publisher=publisherRepository.findById(1L).get();

          book.setName("My Sweet Orange Tree");
          book.setIsbn("999-99-99999-99-9");
          book.setPageCount(150);
          book.setAuthorId(author);
          book.setPublisherId(publisher);
          book.setPublishDate(1983);
          book.setLoanable(true);
          book.setImage(image);
          book.setShelfCode("WF-214");
          book.setActive(true);
          book.setFeatured(true);
          book.setCreateDate(LocalDateTime.now());
          book.setBuiltIn(false);
          return book;
    }

}
