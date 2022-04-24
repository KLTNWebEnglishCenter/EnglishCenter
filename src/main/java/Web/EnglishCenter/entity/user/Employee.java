package Web.EnglishCenter.entity.user;

import lombok.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Employee extends Users implements Serializable {

    public Employee(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String email) {
        super(username, password, fullName, email);
    }


}
