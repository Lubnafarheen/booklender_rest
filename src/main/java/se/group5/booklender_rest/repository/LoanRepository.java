package se.group5.booklender_rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.group5.booklender_rest.models.Loan;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findByLoanTakerUserId(Integer userId);


    List<Loan> findAllByBookBookId(Integer bookId);

    Loan findByConcluded(boolean concluded);

}
