package Web.EnglishCenter.entity;

import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Document")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String description;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String link;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Document(@NonNull String name, String link) {
        this.name = name;
        this.link = link;
    }
}
