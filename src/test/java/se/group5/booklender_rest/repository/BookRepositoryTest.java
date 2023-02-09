package se.group5.booklender_rest.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.group5.booklender_rest.models.Book;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository testObject;



    Book createdBook;

    @BeforeEach
    public void setUp(){
        Book harryPotter = new Book("Harry Potter", 10, BigDecimal.valueOf(4.5),  "Harry Potter is a series of seven fantasy novels " );
        harryPotter.setAvailable(true);
        harryPotter.setReserved(true);
        createdBook = testObject.save(harryPotter);
        assertNotNull(createdBook);
    }

    @Test
    public void test_findById() {
        Book expected = createdBook;
        Optional<Book> actual = testObject.findById(createdBook.getBookId());
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void test_findBookByTitle(){
        List<Book> actual = testObject.findBookByTitle(createdBook.getTitle());
        assertEquals(1, actual.size());
    }

    @Test
    public void test_existsByAvailable(){
        Boolean result = testObject.existsByAvailable(createdBook.isAvailable());
        assertTrue(result);
    }


    @Test
    public void test_findBookByReserved(){
        Book result = testObject.findByReserved(createdBook.isReserved());
        assertEquals(createdBook, result);
    }

    @Test
    public void test_delete(){
        testObject.delete(createdBook);
        List<Book> result = testObject.findBookByTitle(createdBook.getTitle());
       assertEquals(0, result.size());
    }

    @Test
    public void test_update(){
        Book expected = createdBook;
        Optional<Book> result = testObject.findById(createdBook.getBookId());
        result.get().setMaxLoanDays(12);
        Book actual = testObject.save(result.get());
        assertEquals(expected, actual);
    }
}
