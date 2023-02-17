package se.group5.booklender_rest.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.group5.booklender_rest.exception.DataNotFoundException;
import se.group5.booklender_rest.models.Book;
import se.group5.booklender_rest.models.dto.BookDto;
import se.group5.booklender_rest.repository.BookRepository;
import se.group5.booklender_rest.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    ModelMapper modelMapper;

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        List<Book> bookList = bookRepository.findByReserved(reserved);
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        List<Book> bookList = bookRepository.existsByAvailable(available);
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        if (title == null) throw new IllegalArgumentException("Title was null");
        List<Book> bookByTitle = bookRepository.findBookByTitle(title);
        return modelMapper.map(bookByTitle, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    @Override
    public BookDto findById(Integer bookId) {
        if (bookId == null) throw new IllegalArgumentException("Book Id is null");
        Optional<Book> result = bookRepository.findById(bookId);
        if (result.isPresent()) {
            Book book = result.get();
            return modelMapper.map(book, BookDto.class);
        }
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        Iterable<Book> bookList = bookRepository.findAll();
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("Book Dto is null");
        if (bookDto.getBookId() != 0) throw new IllegalArgumentException("Book Id should not be zero or null");
        Book bookEntity = bookRepository.save(modelMapper.map(bookDto, Book.class));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("Book Dto is null");
        if (bookDto.getBookId() == 0) throw new IllegalArgumentException("Book Id should not be zero or null");

        if (!bookRepository.findById(bookDto.getBookId()).isPresent())
            throw new DataNotFoundException("Data not found");
        Book bookEntity = bookRepository.save(modelMapper.map(bookDto, Book.class));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public boolean delete(Integer bookId) {
        BookDto bookDto = findById(bookId);
        if (bookDto != null) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }
}
