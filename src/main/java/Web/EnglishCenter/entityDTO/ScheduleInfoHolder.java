package Web.EnglishCenter.entityDTO;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleInfoHolder {

    String classname;
    String dayOfWeek;
    String lesson;
    String teacherName;
    String type;
    String location;
    String meetingInfo;

}

