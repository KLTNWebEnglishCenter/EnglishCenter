package Web.EnglishCenter.entityDTO;


import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.user.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {


    private int id;


    private String title;


    private String content;


    private LocalDate createDate;

    private LocalDate modifiedDate;


    private Teacher teacher;

    private ClassroomDTO classroom;


}
