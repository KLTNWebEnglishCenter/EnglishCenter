package Web.EnglishCenter.entity.exam;

import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Exam")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")
    private String description;
    @Column(columnDefinition = "nvarchar(255)")
    private String status;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Question.class)
    @JoinTable(
            name = "Exam_Question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;

    @JsonManagedReference
    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY)
    private List<UsersExamScores> usersExamScores;

//    public boolean addQuestion(Question question){
//        return questions.add(question);
//    }

    public Exam(String name, String description, String status, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.teacher = teacher;
    }

    public Exam(String name, String description, String status, Teacher teacher, List<Question> questions) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.teacher = teacher;
        this.questions = questions;
    }
}
