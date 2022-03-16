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
public class UsersExamScores {

    @EmbeddedId
    private UsersExamScoresKey usersExamScoresKey;

    @JsonBackReference
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonBackReference
    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;


    private int scores;


}
