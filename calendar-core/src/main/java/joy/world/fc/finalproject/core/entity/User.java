package joy.world.fc.finalproject.core.entity;

import joy.world.fc.finalproject.core.util.Encryptor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Builder
public class User extends BaseEntity{

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;

    @Builder
    public User(String name, String email, String password, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public boolean isMatched(Encryptor encryptor, String pw) {
        return encryptor.isMatch(pw, this.password);
    }


}
