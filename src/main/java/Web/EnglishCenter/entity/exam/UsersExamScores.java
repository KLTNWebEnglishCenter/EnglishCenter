package Web.EnglishCenter.entity.exam;

import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Users_Exam_Scores")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersExamScores {

    @EmbeddedId
    private UsersExamScoresKey usersExamScoresKey;

    @JsonBackReference(value = "student_usersExamScores")
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonBackReference(value = "exams_usersExamScores")
    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;


    private int scores;


}
