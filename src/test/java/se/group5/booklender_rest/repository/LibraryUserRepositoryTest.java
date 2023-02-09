package se.group5.booklender_rest.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.group5.booklender_rest.models.LibraryUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LibraryUserRepositoryTest {

    @Autowired
    LibraryUserRepository testObject;

    LibraryUser createdUser;

    @BeforeEach
    public void setUp() {
        LibraryUser user = new LibraryUser();
        user.setName("Test");
        user.setEmail("test@gmail.com");
        createdUser = testObject.save(user);
        assertNotNull(createdUser);
    }

    @Test
    public void test_findByEmail() {
        Optional<LibraryUser> actual = testObject.findByEmail(createdUser.getEmail());
        assertTrue(actual.isPresent());
        LibraryUser expected = createdUser;
        assertEquals(expected, actual.get());
    }

    @Test
    public void test_findById() {
        Optional<LibraryUser> actual = testObject.findById(createdUser.getUserId());
        assertTrue(actual.isPresent());
        LibraryUser expected = createdUser;
        assertEquals(expected, actual.get());
    }

    @Test
    public void test_delete() {
        testObject.deleteById(createdUser.getUserId());
        Optional<LibraryUser> result = testObject.findById(createdUser.getUserId());
        assertFalse(result.isPresent());
    }

    @Test
    public void test_update() {
        LibraryUser expected = createdUser;
        Optional<LibraryUser> result = testObject.findById(createdUser.getUserId());
        assertTrue(result.isPresent());
        result.get().setName("BOB");
        LibraryUser actual = testObject.save(result.get());
        assertEquals(expected, actual);
    }

}
