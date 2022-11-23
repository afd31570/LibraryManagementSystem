package com.lms.repotest;

import com.lms.domain.Author;
import com.lms.repository.AuthorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthorRepoTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Test
    public void testCreateAuthor(){
        Author author = new Author();
        author.setName("Paulo Coelho");
        authorRepository.save(author);

        assertNotNull(author);
        assertTrue(author.getId()>0);

    }
    @Test
    public void getAuthorById(){
        Author author = authorRepository.findById(1L).get();
        assertTrue(author.getId().equals(1l));
    }

    @Test
    public void updateAuthor(){
        Author author = authorRepository.findById(1l).get();
        author.setName("John Carole");
        authorRepository.save(author);
        Assertions.assertThat(author.getId().equals(1l));
        assertEquals(author.getName(), "John Carole");
    }
}
