package Web.EnglishCenter.entity.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity(name = "Level")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Level implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "level",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Course> courses;

    public Level( String name) {
        this.name = name;
    }


}
