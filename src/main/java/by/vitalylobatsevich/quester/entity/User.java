package by.vitalylobatsevich.quester.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.*;

import by.vitalylobatsevich.quester.entity.util.UserUpdater;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends LongIdEntity implements Cloneable {

    private String username;

    private String password;

    @Builder
    public User(final Long id, final String username, final String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

    @Override
    public User clone() {
        User user = null;
        try {
            user = (User) super.clone();
        } catch (final CloneNotSupportedException cloneNotSupportedException) {
            user = User.builder()
                       .id(getId())
                       .username(username)
                       .password(password)
                       .build();
        }
        return user;
    }

    public UserUpdater updater() {
        return new UserUpdater(this);
    }

}
