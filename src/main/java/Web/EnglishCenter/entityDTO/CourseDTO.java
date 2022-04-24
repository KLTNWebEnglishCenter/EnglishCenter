package Web.EnglishCenter.entityDTO;

import Web.EnglishCenter.entity.course.Category;
import Web.EnglishCenter.entity.course.Level;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private int id;
    private String name;
    private String description;
    private double price;
    private LocalDate createDate;
    private LocalDate modifiedDate;
    private float discount;
    private boolean enable;

    private Level level;
    private Category category;

    public CourseDTO(@NonNull String name, @NonNull double price) {
        this.name = name;
        this.price = price;
        this.createDate=LocalDate.now();
        this.enable=true;
    }

    public CourseDTO(int id, String name, String description, double price, LocalDate createDate, LocalDate modifiedDate, float discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.discount = discount;
    }
}
