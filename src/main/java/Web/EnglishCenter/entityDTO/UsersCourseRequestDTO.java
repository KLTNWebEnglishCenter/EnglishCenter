package Web.EnglishCenter.entityDTO;

import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.course.UsersCourseRequestKey;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.utils.UserRequestStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UsersCourseRequestDTO {

    private UsersCourseRequestKey userRequestCourseKey;

    private Student student;

    private CourseDTO course;

    private String status;

    public UsersCourseRequestDTO(UsersCourseRequestKey userRequestCourseKey, Student student, CourseDTO course) {
        this.userRequestCourseKey = userRequestCourseKey;
        this.student = student;
        this.course = course;
        this.status= UserRequestStatus.REQUESTING;
    }
}
