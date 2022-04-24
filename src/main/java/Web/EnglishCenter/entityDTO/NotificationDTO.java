package Web.EnglishCenter.entityDTO;


import Web.EnglishCenter.entity.user.Teacher;
import lombok.*;

import java.time.LocalDate;

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
