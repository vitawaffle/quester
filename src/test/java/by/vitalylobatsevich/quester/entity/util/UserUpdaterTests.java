package by.vitalylobatsevich.quester.entity.util;

import org.junit.jupiter.api.Test;

import by.vitalylobatsevich.quester.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserUpdaterTests {

    User user = User.builder()
                    .id(1L)
                    .username("test_user_1")
                    .password("password")
                    .build();

    @Test
    void id_ShouldReturnWithNewId() {
        assertEquals(
                2L,
                user.updater()
                    .id(2L)
                    .update()
                    .getId()
        );
    }

    @Test
    void username_ShouldReturnWithNewUsername() {
        assertEquals(
                "new_username",
                user.updater()
                    .username("new_username")
                    .update()
                    .getUsername()
        );
    }

    @Test
    void password_ShouldReturnWithNewPassword() {
        assertEquals(
                "new_password",
                user.updater()
                    .password("new_password")
                    .update()
                    .getPassword()
        );
    }

}
