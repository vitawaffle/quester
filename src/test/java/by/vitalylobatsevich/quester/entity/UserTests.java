package by.vitalylobatsevich.quester.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    User user1 = User.builder()
                     .id(1L)
                     .username("some_user")
                     .password("password")
                     .build();

    User user2 = User.builder()
                     .id(1L)
                     .username("some_user")
                     .password("password")
                     .build();

    User user3 = User.builder()
                     .id(1L)
                     .username("some_user")
                     .password("password")
                     .build();

    @Test
    void equals_Equal_ShouldBeEqual() {
        assertEquals(user1, user2);
    }

    @Test
    void equals_NotEqual_ShouldBeNotEqual() {
        assertNotEquals(
                user1,
                user1.updater()
                     .id(2L)
                     .update()
        );
    }

    @Test
    void equals_Reflexive_ShouldBeEquals() {
        assertEquals(user1, user1);
    }

    @Test
    void equals_Symmetric_ShouldBeEqual() {
        assertEquals(user1, user2);
        assertEquals(user2, user1);
    }

    @Test
    void equals_Transitive_ShouldBeEquals() {
        assertEquals(user1, user2);
        assertEquals(user2, user3);
        assertEquals(user1, user3);
    }

    @Test
    void equals_NullEntity_ShouldBeNotEqual() {
        assertNotEquals(user1, null);
    }

    @Test
    void hashCode_ShouldReturnSameHashCodeForOneObject() {
        assertEquals(user1.hashCode(), user1.hashCode());
    }

    @Test
    void hashCode_ShouldReturnSaveHashCodeForSameObjects() {
        assertEquals(user1.hashCode(), user2.hashCode());
    }

}
