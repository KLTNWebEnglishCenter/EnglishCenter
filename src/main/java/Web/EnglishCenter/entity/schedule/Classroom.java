package Web.EnglishCenter.entity.schedule;

import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.schedule.Schedule;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Classroom")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id")
public class Classroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NonNull
    private LocalDate startDate;

//    @NonNull
    private LocalDate endDate;

    @Column(columnDefinition = "nvarchar(255)")
    private String status;

    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String classname;

    private int maxMember;

    private LocalDate createDate;

    private LocalDate modifiedDate;

    @JsonBackReference(value = "teacher_classrooms")
//    @NonNull
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JsonBackReference(value = "classrooms_course")
//    @NonNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @JsonBackReference(value = "classrooms_students")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Users_Classroom",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

//    @JsonBackReference(value = "classrooms_schedules")
//    @ManyToMany
//    @JoinTable(
//            name = "Classroom_Schedule",
//            joinColumns = @JoinColumn(name = "classroom_id"),
//            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
//    private List<Schedule> schedules;

    @JsonManagedReference(value = "classroom_schedule")
    @OneToMany(mappedBy = "classroom")
    private List<ClassroomSchedule> classroomSchedules;

    @JsonManagedReference(value = "classroom_notifications")
    @OneToMany(mappedBy = "classroom",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public Classroom( LocalDate startDate,  LocalDate endDate, Teacher teacher,  Course course) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
        this.course = course;
    }

    public void addSchedule(ClassroomSchedule classroomSchedule){
        if(classroomSchedules==null)classroomSchedules=new ArrayList<>();
        else classroomSchedules.add(classroomSchedule);
    }

    public Classroom(int id, LocalDate startDate, LocalDate endDate, String status, String classname, int maxMember, LocalDate createDate, LocalDate modifiedDate) {
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
                ", students=" + students +
                ", schedules=" + classroomSchedules +
                ", notifications=" + notifications +
                '}';
    }
}
