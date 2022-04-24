package Web.EnglishCenter.entityDTO;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private int id;
    private String content;
    private String correctAnswer;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
}
