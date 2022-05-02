package Web.EnglishCenter.entity.schedule;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Schedule")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String dayOfWeek;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String lesson;

//    @JsonManagedReference(value = "classrooms_schedules")
//    @ManyToMany(mappedBy = "schedules",fetch = FetchType.LAZY)
//    private List<Classroom> classrooms;

    @JsonManagedReference(value = "schedule_classroom")
    @OneToMany(mappedBy = "schedule")
    private List<ClassroomSchedule> classroomSchedules;

    public Schedule( String dayOfWeek, String lesson) {
        this.dayOfWeek = dayOfWeek;
        this.lesson = lesson;
    }
}
