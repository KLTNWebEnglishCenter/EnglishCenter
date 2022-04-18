package Web.EnglishCenter.entity;

import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Post")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String title;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String content;

    @JsonBackReference(value = "users_posts")
//    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Post(@NonNull String title, @NonNull String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", users=" + users +
                '}';
    }
}
