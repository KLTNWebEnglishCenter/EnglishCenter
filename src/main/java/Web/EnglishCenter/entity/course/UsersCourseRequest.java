package Web.EnglishCenter.entity.course;

import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity(name="Users_Course_Request")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UsersCourseRequest {

    @EmbeddedId
    private UsersCourseRequestKey userRequestCourseKey;

    @JsonBackReference
    @ManyToOne()
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonBackReference
    @ManyToOne()
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(columnDefinition = "nvarchar(255)")
    private String status;

    public UsersCourseRequest(UsersCourseRequestKey userRequestCourseKey, Student student, Course course) {
        this.userRequestCourseKey = userRequestCourseKey;
        this.student = student;
        this.course = course;
        this.status="requesting";
    }
}
