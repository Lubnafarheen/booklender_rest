package se.group5.booklender_rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.group5.booklender_rest.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findBookByTitle(String title);

    Boolean existsByAvailable(boolean book);

    Book findByReserved(boolean book);

}
