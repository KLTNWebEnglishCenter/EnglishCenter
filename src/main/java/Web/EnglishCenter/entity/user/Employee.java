package Web.EnglishCenter.entity.user;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.UsersExamScores;
import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Employee extends Users implements Serializable {

    public Employee(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String email) {
        super(username, password, fullName, email);
    }


}
