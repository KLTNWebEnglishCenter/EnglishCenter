package Web.EnglishCenter.entityDTO;

import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.schedule.Schedule;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomScheduleDTO {

    private Classroom classroom;
    private Schedule schedule;
    private String type;
    private String location;
    private String meetingInfo;
}
