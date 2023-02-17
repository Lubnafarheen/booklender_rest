package se.group5.booklender_rest.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;
import se.group5.booklender_rest.models.Book;
import se.group5.booklender_rest.models.LibraryUser;
import se.group5.booklender_rest.models.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    LoanRepository testObject;

    Loan createdLoan;


    @BeforeEach
    public void  setUp(){
        Book harryPotter = new Book("Harry Potter", 10, BigDecimal.valueOf(4.5),  "Harry Potter is a series of seven fantasy novels " );
        LibraryUser user = new LibraryUser("Test", "test@gmail.com");
       Loan userLoan = new Loan(user,harryPotter, LocalDate.now(), false);
       createdLoan = testObject.save(userLoan);
       assertNotNull(createdLoan);
    }

    @Test
    public void test_findById(){
        Optional<Loan> actual = testObject.findById(createdLoan.getLoanId());
        assertTrue(actual.isPresent());
        Loan expected = createdLoan;
        assertEquals(expected, actual.get());
    }
    @Test
    public void test_remove(){
        testObject.delete(createdLoan);
        Optional<Loan> actual = testObject.findById(createdLoan.getLoanId());
        assertFalse(actual.isPresent());
    }

    @Test
    public void test_Update(){
        Optional<Loan> actual = testObject.findById(createdLoan.getLoanId());
        assertTrue(actual.isPresent());
        actual.get().setLoanDate(LocalDate.parse("2023-02-12"));
        Loan expected = testObject.save(actual.get());
        assertEquals(expected, actual.get());
    }

    @Test
    public void test_findByLoanTakerUserId(){
        List<Loan> result = testObject.findByLoanTakerUserId(createdLoan.getLoanTaker().getUserId());
        Loan expected = createdLoan;
        assertEquals(1, result.size());
    }

    /*@Test
    public void findByBook_BookId() {
        Loan actual = testObject.findByBookId(createdLoan.getBook().getBookId());
        Loan expected = createdLoan;
        assertEquals(expected, actual);
    }
*/

    @Test
    public void findByConcluded(){
        Loan result = testObject.findByConcluded(createdLoan.isConcluded());
        assertEquals(createdLoan, result);

    }





}
