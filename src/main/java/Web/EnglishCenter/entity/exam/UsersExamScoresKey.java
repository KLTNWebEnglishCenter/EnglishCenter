package Web.EnglishCenter.entity.exam;

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
public class UsersExamScoresKey implements Serializable {


    @Column(name = "student_id")
    private int studentId;

    @Column(name = "exam_id")
    private int examId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersExamScoresKey that = (UsersExamScoresKey) o;
        return studentId == that.studentId && examId == that.examId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, examId);
    }
}
