package Web.EnglishCenter.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "Authentication")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String role;

    @NonNull
    private boolean enable;

    @JsonBackReference
    @OneToMany(mappedBy = "authentication",fetch = FetchType.LAZY)
    private List<Users> users;

    public Authentication(@NonNull String role) {
        this.role = role;
        this.enable=true;
    }

    public Authentication(@NonNull String role, @NonNull boolean enable) {
        this.role = role;
        this.enable = enable;
    }


}
