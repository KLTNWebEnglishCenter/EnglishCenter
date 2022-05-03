package Web.EnglishCenter.service;

import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.schedule.ClassroomSchedule;
import Web.EnglishCenter.entity.schedule.ClassroomScheduleKey;

import java.time.LocalDate;
import java.util.List;

public interface ClassroomScheduleService {
    public ClassroomSchedule save(ClassroomSchedule classroomSchedule);
    public void delete(ClassroomSchedule classroomSchedule);
    public List<ClassroomSchedule> findAll();
    public ClassroomSchedule findById(int id);

    public List<String> getScheduleOfTeacher(int teacherId, LocalDate currentDate, String dayOfWeek);
    public List<String> getScheduleOfStudent(int studentId, LocalDate currentDate,String dayOfWeek);

    public List<ClassroomSchedule> findByKey(ClassroomScheduleKey key);
    public List<ClassroomSchedule> findByClassroomId(int classroomId);
}
