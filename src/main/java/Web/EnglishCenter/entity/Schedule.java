package Web.EnglishCenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Schedule")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String dayOfWeek;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String lesson;

    @JsonManagedReference
    @ManyToMany(mappedBy = "schedules",fetch = FetchType.LAZY)
    private List<Classroom> classrooms;

    public Schedule(@NonNull String dayOfWeek, @NonNull String lesson) {
        this.dayOfWeek = dayOfWeek;
        this.lesson = lesson;
    }
}
