package by.vitalylobatsevich.quester.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.vavr.control.Option;

import by.vitalylobatsevich.quester.entity.User;
import by.vitalylobatsevich.quester.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceTests {

    @Mock
    UserRepository userRepository;
    
    UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    void loadUserByUsername_ExistingUsername_ShouldReturnNotNull() {
        Mockito.when(userRepository.findByUsername("test_user_1"))
               .thenReturn(Option.of(
                       User.builder()
                           .id(1L)
                           .username("test_user_1")
                           .password("password")
                           .build()
                ));
        assertNotNull(userDetailsService.loadUserByUsername("test_user_1"));
    }

    @Test
    void loadUserByUsername_NotExistingUsername_ShouldThrowsUsernameNotFoundException() {
        Mockito.when(userRepository.findByUsername("not_existing_username"))
               .thenReturn(Option.none());
        assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("not_existing_username")
        );
    }

}
