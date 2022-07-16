package by.vitalylobatsevich.quester.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import by.vitalylobatsevich.quester.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsTests {

    User user = User.builder()
                    .id(1L)
                    .username("test_user_1")
                    .password("password")
                    .build();

    UserDetails userDetails = UserDetailsImpl.builder()
                                             .user(user)
                                             .build();

    @Test
    void getAuthorities_ShouldReturnEmpty() {
        assertTrue(userDetails.getAuthorities().isEmpty());
    }

    @Test
    void getPassword_ShouldReturnNotBlank() {
        assertFalse(userDetails.getPassword().isBlank());
    }

    @Test
    void getUsername_ShouldReturnNotBlank() {
        assertFalse(userDetails.getUsername().isBlank());
    }

    @Test
    void isAccountNonExpired_ShouldReturnTrue() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked_ShouldReturnTrue() {
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired_ShouldReturnTrue() {
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled_ShouldReturnTrue() {
        assertTrue(userDetails.isEnabled());
    }

}
