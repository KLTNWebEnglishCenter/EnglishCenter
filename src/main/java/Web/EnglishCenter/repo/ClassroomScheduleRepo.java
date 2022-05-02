package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.schedule.ClassroomSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClassroomScheduleRepo extends JpaRepository<ClassroomSchedule,Integer > {

    /**
     * Test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @return
     */
    @Query(value = "select c.classname,s.day_of_week,s.lesson,u.full_name,cs.type,cs.location,cs.meeting_info from [dbo].[classroom] c\n" +
            "join [dbo].[classroom_schedule] cs on c.id=cs.classroom_id\n" +
            "join [dbo].[schedule] s on cs.schedule_id=s.id\n" +
            "join [dbo].[users] u on c.teacher_id=u.id\n" +
            "where c.teacher_id=:teacherId and (:currentDate between c.start_date and c.end_date) and s.day_of_week=:dayOfWeek",nativeQuery = true)
    List<String> getScheduleOfTeacher(int teacherId, LocalDate currentDate,String dayOfWeek);

    @Query(value = "select c.classname,s.day_of_week,s.lesson,u.full_name,cs.type,cs.location,cs.meeting_info from users_classroom uc\n" +
            "join classroom c on uc.classroom_id=c.id\n" +
            "join classroom_schedule cs on c.id=cs.classroom_id\n" +
            "join schedule s on cs.schedule_id=s.id\n" +
            "join users u on c.teacher_id=u.id\n" +
            "where uc.student_id=:studentId and (:currentDate between c.start_date and c.end_date ) and s.day_of_week=:dayOfWeek",nativeQuery = true)
    List<String> getScheduleOfStudent(int studentId, LocalDate currentDate,String dayOfWeek);
}
