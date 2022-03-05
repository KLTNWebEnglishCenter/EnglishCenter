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
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NonNull
    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String description;

    @NotNull
    @NonNull
    private double price;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    private float discount;

    private boolean enable;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Level level;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonBackReference
    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<UsersCourseRequest> userRequestCourses;

    @JsonBackReference
    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<Classroom> classrooms;

    public Course(@NonNull String name, @NonNull double price) {
        this.name = name;
        this.price = price;
        this.createDate=LocalDate.now();
        this.enable=true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", discount=" + discount +
                ", enable=" + enable +
                ", level=" + level +
                ", category=" + category +
                ", userRequestCourses=" + userRequestCourses +
                ", classrooms=" + classrooms +
                '}';
    }
}
