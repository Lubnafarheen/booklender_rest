package se.group5.booklender_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.group5.booklender_rest.models.dto.BookDto;
import se.group5.booklender_rest.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookService.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        bookService.delete(id);
        //return ResponseEntity.noContent().build(); OR
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        BookDto createdBookDto = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDto);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody BookDto bookDto) {
        bookService.update(bookDto);
        return ResponseEntity.noContent().build();
    }

}
