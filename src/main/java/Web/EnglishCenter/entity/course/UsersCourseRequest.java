package Web.EnglishCenter.entity.course;

import Web.EnglishCenter.entity.user.Users;
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

    @JsonManagedReference
    @ManyToOne()
    @MapsId("usersId")
    @JoinColumn(name = "users_id")
    private Users users;

    @JsonManagedReference
    @ManyToOne()
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(columnDefinition = "nvarchar(255)")
    private String status;

    public UsersCourseRequest(UsersCourseRequestKey userRequestCourseKey, Users users, Course course) {
        this.userRequestCourseKey = userRequestCourseKey;
        this.users = users;
        this.course = course;
        this.status="requesting";
    }
}
