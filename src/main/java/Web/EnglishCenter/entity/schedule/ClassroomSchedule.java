package Web.EnglishCenter.entity.schedule;

import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.course.UsersCourseRequestKey;
import Web.EnglishCenter.entity.user.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity(name="Classroom_Schedule")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomSchedule {

    @EmbeddedId
    private ClassroomScheduleKey classroomScheduleKey;

    @JsonBackReference(value = "classroom_schedule")
    @ManyToOne()
    @MapsId("classroomId")
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @JsonBackReference(value = "schedule_classroom")
    @ManyToOne()
    @MapsId("scheduleId")
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column(columnDefinition = "nvarchar(255)")
    private String type;

    @Column(columnDefinition = "nvarchar(255)")
    private String location;

    @Column(columnDefinition = "nvarchar(255)")
    private String meetingInfo;
}
