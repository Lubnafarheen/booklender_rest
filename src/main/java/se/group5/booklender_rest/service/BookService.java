package se.group5.booklender_rest.service;

import se.group5.booklender_rest.models.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findByReserved(boolean reserved);

    List<BookDto> findByAvailable(boolean available);

    List<BookDto> findByTitle(String title);

    BookDto findById(Integer bookId);

    List<BookDto> findAll();

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    boolean delete(Integer bookId);


}
