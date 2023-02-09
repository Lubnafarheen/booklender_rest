package se.group5.booklender_rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.group5.booklender_rest.models.LibraryUser;

import java.util.Optional;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {

    Optional<LibraryUser> findByEmail(String email);
}


