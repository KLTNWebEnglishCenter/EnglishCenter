package Web.EnglishCenter.entityDTO;

import Web.EnglishCenter.entity.schedule.Schedule;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDTO {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String classname;
    private int maxMember;
    private LocalDate createDate;
    private LocalDate modifiedDate;

    private Teacher teacher;
    private Course course;

    private List<Student> students;

    public ClassroomDTO(LocalDate startDate, LocalDate endDate, Teacher teacher, Course course) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = LocalDate.now();
        this.teacher = teacher;
        this.course = course;
    }
    public ClassroomDTO(LocalDate startDate, LocalDate endDate, Teacher teacher, Course course, List<Schedule> schedules) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = LocalDate.now();
        this.teacher = teacher;
        this.course = course;

    }

    public ClassroomDTO(int id, LocalDate startDate, LocalDate endDate, String status, String classname, int maxMember, LocalDate createDate, LocalDate modifiedDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.classname = classname;
        this.maxMember = maxMember;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", classname='" + classname + '\'' +
                ", maxMember=" + maxMember +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", teacher=" + teacher +
                ", course=" + course +
                '}';
    }
}
