package Web.EnglishCenter.entity.user;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.UsersExamScores;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Student extends Users implements Serializable {

    @JsonBackReference
    @OneToMany(mappedBy = "student")
    private List<UsersCourseRequest> userRequestCourses;

    @JsonBackReference
    @ManyToMany(mappedBy = "students",fetch = FetchType.LAZY)
    private List<Classroom> classrooms;


    @JsonBackReference
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<UsersExamScores> usersExamScores;

    public Student(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String email) {
        super(username, password, fullName, email);
    }
}
