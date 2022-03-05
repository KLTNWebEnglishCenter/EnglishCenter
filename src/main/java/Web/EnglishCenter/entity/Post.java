package Web.EnglishCenter.entity;

import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Post")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String title;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String content;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Post(@NonNull String title, @NonNull String content) {
        this.title = title;
        this.content = content;
    }
}
