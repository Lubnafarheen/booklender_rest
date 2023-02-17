package se.group5.booklender_rest.service;

import se.group5.booklender_rest.models.dto.LoanDto;

import java.util.List;

public interface LoanService {

    LoanDto findById(Long loanId);

    List<LoanDto> findByBookId(Integer bookId);

    List<LoanDto> findByUserId(Integer userId);

    List<LoanDto> findByConcluded(boolean concluded);

    List<LoanDto> findAll();

    LoanDto create(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    boolean delete(Long loanId);


}
