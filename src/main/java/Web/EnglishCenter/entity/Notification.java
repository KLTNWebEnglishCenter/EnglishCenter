package Web.EnglishCenter.entity;


import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Notification")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)",nullable = false)
    private String title;

    @NonNull
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String content;

    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @JsonBackReference(value = "teacher_notifications")
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

//    @JsonManagedReference(value = "classrooms_notifications")
    @ManyToMany
    @JoinTable(
            name = "Classroom_Notification",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id"))
    private List<Classroom> classrooms;

    public Notification(@NonNull String title, @NonNull String content, Teacher teacher) {
        this.title = title;
        this.content = content;
        this.createDate = LocalDate.now();
        this.teacher = teacher;
    }
}
