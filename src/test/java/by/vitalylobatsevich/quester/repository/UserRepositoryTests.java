package by.vitalylobatsevich.quester.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import javax.transaction.Transactional;

import by.vitalylobatsevich.quester.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(userRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(userRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(userRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userRepository.save(
                User.builder()
                    .username("some_user")
                    .password("$2a$10$aJSyrxGVgKc3dy9wFonZSeznyM7H.zMYVgPZjtK5Xq4DmD8l1Euf2")
                    .build()
        ));
    }

    @Test
    void save_NullUsernameEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> userRepository.save(
                User.builder()
                    .password("$2a$10$aJSyrxGVgKc3dy9wFonZSeznyM7H.zMYVgPZjtK5Xq4DmD8l1Euf2")
                    .build()
        ));
    }

    @Test
    void save_NotUniqueUsernameEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> userRepository.save(
                User.builder()
                    .username("test_user_1")
                    .password("$2a$10$aJSyrxGVgKc3dy9wFonZSeznyM7H.zMYVgPZjtK5Xq4DmD8l1Euf2")
                    .build()
        ));
    }

    @Test
    void save_NullPasswordEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> userRepository.save(
                User.builder()
                    .username("some_user")
                    .build()
        ));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldReturnNotEmpty() {
        assertDoesNotThrow(() -> userRepository.delete(
                User.builder()
                    .id(1L)
                    .build()
        ));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldReturnEmpty() {
        assertDoesNotThrow(() -> userRepository.delete(
                User.builder()
                    .id(0L)
                    .build()
        ));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccess() {
        assertThrows(EmptyResultDataAccessException.class, () -> userRepository.deleteById(0L));
    }

    @Test
    void findByUsername_ExistingUsername_ShouldReturnNotEmpty() {
        assertFalse(userRepository.findByUsername("test_user_1").isEmpty());
    }

    @Test
    void findByUsername_NotExistingUsername_ShouldReturnEmpty() {
        assertTrue(userRepository.findByUsername("not_existing_user").isEmpty());
    }

}
