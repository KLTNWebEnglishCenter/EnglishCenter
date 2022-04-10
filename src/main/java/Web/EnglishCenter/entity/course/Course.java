package Web.EnglishCenter.entity.course;

import Web.EnglishCenter.entity.Classroom;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity(name = "Course")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
//    @NonNull
    @Column(columnDefinition = "nvarchar(255)",nullable = false,unique = true)
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String description;

//    @NotNull
//    @NonNull
    @Column(nullable = false)
    private double price;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    private float discount;

    private boolean enable;

    @JsonBackReference(value = "course_level")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Level level;

    @JsonBackReference(value = "course_category")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference(value = "course_userRequestCourses")
    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<UsersCourseRequest> userRequestCourses;

    @JsonBackReference(value = "classrooms_course")
    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<Classroom> classrooms;

    public Course(String name, double price) {
        this.name = name;
        this.price = price;
        this.createDate=LocalDate.now();
        this.enable=true;
    }

}
