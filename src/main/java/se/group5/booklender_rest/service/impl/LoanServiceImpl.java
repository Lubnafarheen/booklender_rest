package se.group5.booklender_rest.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.group5.booklender_rest.exception.DataNotFoundException;
import se.group5.booklender_rest.models.Loan;
import se.group5.booklender_rest.models.dto.LoanDto;
import se.group5.booklender_rest.repository.LoanRepository;
import se.group5.booklender_rest.service.LoanService;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository loanRepository;

    ModelMapper modelMapper;

    @Override
    public LoanDto findById(Long loanId) {
        if (loanId == null) throw new IllegalArgumentException("Loan Id is null");
        Optional<Loan> result = loanRepository.findById(loanId);
        if (result.isPresent()) {
            Loan entity = result.get();
            return modelMapper.map(entity, LoanDto.class);
        }
        return null;
    }

   /* @Override
    public List<LoanDto> findByBookId(Integer bookId) {
        if (bookId == null) throw new IllegalArgumentException("Book Id is null");
        List<Loan> loanList = loanRepository.findByBookId(bookId);
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>() {
        }.getType());
    }*/

    @Override
    public List<LoanDto> findByUserId(Integer userId) {
        if (userId == null) throw new IllegalArgumentException("User Id is null");
        Optional<Loan> result = loanRepository.findByLoanTakerUserId(userId);
        if (result.isPresent()) {
            Loan loan = result.get();
            //todo
        }
        return null;
    }

    @Override
    public List<LoanDto> findByConcluded(boolean concluded) {
        return null;
    }

    @Override
    public List<LoanDto> findAll() {
        Iterable<Loan> loanList = loanRepository.findAll();
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>() {
        }.getType());
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        if (loanDto == null) throw new IllegalArgumentException("loan Dto is null");
        if (loanDto.getLoanId() != 0) throw new IllegalArgumentException("loan Id should not be zero or null");
        Loan loanEntity = loanRepository.save(modelMapper.map(loanDto, Loan.class));
        return modelMapper.map(loanEntity, LoanDto.class);
    }

    @Override
    public LoanDto update(LoanDto loanDto) {
        if (loanDto == null) throw new IllegalArgumentException("loan Dto is null");
        if (loanDto.getLoanId() == 0) throw new IllegalArgumentException("loan Id should not be zero or null");

        if (!loanRepository.findById(loanDto.getLoanId()).isPresent())
            throw new DataNotFoundException("Data not found");
        Loan loanEntity = loanRepository.save(modelMapper.map(loanDto, Loan.class));
        return modelMapper.map(loanEntity, LoanDto.class);
    }

    @Override
    public boolean delete(Long loanId) {
        LoanDto loanDto = findById(loanId);
        if (loanDto != null) {
            loanRepository.deleteById(loanId);
            return true;
        }
        return false;
    }
}
