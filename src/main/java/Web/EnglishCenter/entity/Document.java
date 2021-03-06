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
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String description;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String link;

    @JsonBackReference(value = "teacher_documents")
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Document(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Document(String name, String description, String link, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.teacher = teacher;
    }
}
