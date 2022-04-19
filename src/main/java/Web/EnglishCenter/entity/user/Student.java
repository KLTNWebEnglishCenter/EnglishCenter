package Web.EnglishCenter.entity.user;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.UsersExamScores;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference(value = "student_userRequestCourses")
    @OneToMany(mappedBy = "student")
    private List<UsersCourseRequest> userRequestCourses;

//    @JsonBackReference(value = "classrooms_students")
    @ManyToMany(mappedBy = "students",fetch = FetchType.LAZY)
    private List<Classroom> classrooms;


    @JsonManagedReference(value = "student_usersExamScores")
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<UsersExamScores> usersExamScores;

    public Student(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String email) {
        super(username, password, fullName, email);
    }

    public Student(int id, @NonNull String username, @NonNull String password, @NonNull String fullName, LocalDate dob, String gender, @NonNull String email, String phoneNumber, boolean enable) {
        super(id, username, password, fullName, dob, gender, email, phoneNumber, enable);
    }
}
