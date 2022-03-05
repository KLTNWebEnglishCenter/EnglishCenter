package Web.EnglishCenter.entity;

import Web.EnglishCenter.entity.user.Users;
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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Users creator;

    public Document(@NonNull String name, String link) {
        this.name = name;
        this.link = link;
    }
}
