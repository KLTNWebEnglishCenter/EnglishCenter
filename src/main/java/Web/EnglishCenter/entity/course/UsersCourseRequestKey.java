package Web.EnglishCenter.entity.course;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersCourseRequestKey implements Serializable {

    @Column(name = "users_id")
    private int usersId;

    @Column(name = "course_id")
    private int courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersCourseRequestKey that = (UsersCourseRequestKey) o;
        return usersId == that.usersId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, courseId);
    }
}
