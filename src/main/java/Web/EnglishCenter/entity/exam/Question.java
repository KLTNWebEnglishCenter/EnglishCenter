package Web.EnglishCenter.entity.exam;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Question")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false)
    private String content;

    private String correctAnswer;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    @JsonBackReference
    @ManyToMany(mappedBy = "questions",targetEntity = Exam.class,fetch = FetchType.LAZY)
    private List<Exam> exams;

    public Question(@NonNull String content, String correctAnswer, String answerA, String answerB) {
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.answerA = answerA;
        this.answerB = answerB;
    }
}
