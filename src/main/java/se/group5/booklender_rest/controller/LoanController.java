package se.group5.booklender_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.group5.booklender_rest.models.dto.BookDto;
import se.group5.booklender_rest.models.dto.LoanDto;
import se.group5.booklender_rest.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(loanService.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        loanService.delete(id);
        //return ResponseEntity.noContent().build(); OR
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/")
    public ResponseEntity<LoanDto> create(@RequestBody LoanDto loanDto) {
        LoanDto createdLoanDto = loanService.create(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoanDto);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody LoanDto loanDto ) {
        loanService.update(loanDto);
        return ResponseEntity.noContent().build();
    }
}
