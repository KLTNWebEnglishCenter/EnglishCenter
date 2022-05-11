package Web.EnglishCenter.entity.course;

import Web.EnglishCenter.entity.schedule.Classroom;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Course")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

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
//        this.createDate=LocalDate.now();
        this.enable=true;
    }

    public Course(int id, String name, String description, double price, LocalDate createDate, LocalDate modifiedDate, float discount, boolean enable, Level level, Category category) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.createDate=createDate;
        this.modifiedDate=modifiedDate;
        this.discount=discount;
        this.enable=enable;
        this.level=level;
        this.category=category;
    }
}
