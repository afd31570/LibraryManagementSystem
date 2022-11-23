package com.lms.repotest;


import com.lms.domain.*;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private CategoryRepository categoryRepository;

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

          Book book = new Book();

          Image newImage=getImage();
          Author author=getAuthor();
          Publisher publisher=getPublisher();
          Category category=getCategory();

          book.setName("My Sweet Orange Tree");
          book.setIsbn("999-99-99999-99-9");
          book.setPageCount(150);
          book.setAuthorId(author);
          book.setPublisherId(publisher);
          book.setPublishDate(1983);
          book.setCategoryId(category);
          book.setLoanable(true);
          book.setImage(newImage);
          book.setShelfCode("WF-214");
          book.setActive(true);
          book.setFeatured(true);
          book.setCreateDate(LocalDateTime.now());
          book.setBuiltIn(false);
          return book;
    }


    private Author getAuthor() {
        Author newAuthor=new Author(1L,"tolstoy",false);
        Author savedAuthor=authorRepository.save(newAuthor);
        Author author=authorRepository.findById(1L).get();
        return author;
    }
    private Publisher getPublisher() {
        Publisher newPublisher=new Publisher(1L,"xyz",false);
        Publisher savedPublisher=publisherRepository.save(newPublisher);
        Publisher publisher=publisherRepository.findById(1L).get();
        return publisher;
    }
    private Category getCategory() {
        Category newCategory=new Category(1L,"abc",5,false);
        Category savedCategory=categoryRepository.save(newCategory);
        Category category=categoryRepository.findById(1L).get();
        return category;
    }




    private Image getImage(){
        // read the image from the file
        BufferedImage imageFile = null;
        try {
            imageFile = ImageIO.read(new File("src/main/resources/static/book.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create the object of ByteArrayOutputStream class
        ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();

        // write the image into the object of ByteArrayOutputStream class
        try {
            ImageIO.write(imageFile, "png", outStreamObj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create the byte array from image
        byte [] img= outStreamObj.toByteArray();

        Image newImage=new Image("book.png",img);
        Image savedImage=imageRepository.save(newImage);
        Image image=imageRepository.findById(savedImage.getId()).get();
        return image;
    }

}
