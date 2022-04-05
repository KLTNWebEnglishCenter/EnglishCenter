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

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Teacher extends Users implements Serializable {

    @Column(columnDefinition = "nvarchar(255)")
    private String certificate;

    @JsonBackReference(value = "teacher_classrooms")
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Classroom> classrooms;

    @JsonManagedReference(value = "teacher_documents")
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> documents;

    @JsonManagedReference(value = "teacher_notifications")
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @JsonManagedReference(value = "teacher_exams")
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Exam> exams;


    public Teacher(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String email, String certificate) {
        super(username, password, fullName, email);
        this.certificate = certificate;
    }

    public Teacher(@NonNull String username, @NonNull String password, @NonNull String fullName, @NonNull String email) {
        super(username, password, fullName, email);
    }


    public Teacher(String certificate) {
        this.certificate = certificate;
    }
}
