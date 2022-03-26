package Web.EnglishCenter.entity;

import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Classroom")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;

    @Column(columnDefinition = "nvarchar(255)")
    private String status;

    private int maxMember;

    private LocalDate createDate;

    private LocalDate modifiedDate;

    @JsonBackReference(value = "teacher_classrooms")
    @NonNull
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JsonBackReference(value = "classrooms_course")
    @NonNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

//    @JsonManagedReference(value = "classrooms_students")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Users_Classroom",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

//    @JsonManagedReference(value = "classrooms_schedules")
    @ManyToMany
    @JoinTable(
            name = "Classroom_Schedule",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private List<Schedule> schedules;

//    @JsonManagedReference(value = "classrooms_notifications")
    @ManyToMany(mappedBy = "classrooms", fetch = FetchType.LAZY)
    private List<Notification> notifications;

    public Classroom(@NonNull LocalDate startDate, @NonNull LocalDate endDate, @NonNull Teacher teacher, @NonNull Course course) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = LocalDate.now();
        this.teacher = teacher;
        this.course = course;
    }
    public Classroom(@NonNull LocalDate startDate, @NonNull LocalDate endDate, @NonNull Teacher teacher, @NonNull Course course,List<Schedule> schedules) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = LocalDate.now();
        this.teacher = teacher;
        this.course = course;
        this.schedules=schedules;
    }
}
