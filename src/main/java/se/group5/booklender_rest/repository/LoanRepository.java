package se.group5.booklender_rest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.group5.booklender_rest.models.Loan;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    Optional<Loan> findByLoanTakerUserId(Integer userId);


 //List<Loan> findByBookId(Integer bookId);

    Loan findByConcluded( boolean concluded);

}
