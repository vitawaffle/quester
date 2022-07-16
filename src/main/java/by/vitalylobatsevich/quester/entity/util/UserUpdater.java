package by.vitalylobatsevich.quester.entity.util;

import by.vitalylobatsevich.quester.entity.User;

public class UserUpdater implements Updater<User> {

    private final User user;

    public UserUpdater(final User user) {
        this.user = user.clone();
    }

    public UserUpdater id(final Long id) {
        user.setId(id);
        return this;
    }

    public UserUpdater username(final String username) {
        user.setUsername(username);
        return this;
    }

    public UserUpdater password(final String password) {
        user.setPassword(password);
        return this;
    }

    @Override
    public User update() {
        return user;
    }

}
