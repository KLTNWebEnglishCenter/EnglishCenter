package Web.EnglishCenter.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomScheduleKey implements Serializable {

    @Column(name = "classroom_id")
    private int classroomId;

    @Column(name = "schedule_id")
    private int scheduleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomScheduleKey that = (ClassroomScheduleKey) o;
        return classroomId == that.classroomId && scheduleId == that.scheduleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomId, scheduleId);
    }
}
