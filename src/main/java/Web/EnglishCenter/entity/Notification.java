package Web.EnglishCenter.entity;


import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.user.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    @JsonBackReference(value = "classroom_notifications")
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

//    @JsonManagedReference(value = "classrooms_notifications")
//    @ManyToMany
//    @JoinTable(
//            name = "Classroom_Notification",
//            joinColumns = @JoinColumn(name = "notification_id"),
//            inverseJoinColumns = @JoinColumn(name = "classroom_id"))
//    private List<Classroom> classrooms;

    public Notification(int id) {
        this.id = id;
    }

    public Notification(@NonNull String title, @NonNull String content, Teacher teacher) {
        this.title = title;
        this.content = content;
        this.teacher = teacher;
    }
}
