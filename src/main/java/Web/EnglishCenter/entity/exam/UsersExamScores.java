package Web.EnglishCenter.entity.exam;

import Web.EnglishCenter.entity.user.Users;
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

    @JsonManagedReference
    @ManyToOne
    @MapsId("usersId")
    @JoinColumn(name = "users_id")
    private Users users;

    @JsonManagedReference
    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;


    private int scores;


}
