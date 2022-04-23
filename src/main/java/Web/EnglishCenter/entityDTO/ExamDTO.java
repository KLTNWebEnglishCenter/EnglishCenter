package Web.EnglishCenter.entityDTO;

import Web.EnglishCenter.entity.user.Teacher;
import lombok.*;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {

    private int id;
    private String name;
    private String description;
    private String status;
    private Teacher teacher;
    private List<QuestionDTO> questionDTOS;

}
