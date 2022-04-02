package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Schedule;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.service.ClassroomService;
import Web.EnglishCenter.service.CourseService;
import Web.EnglishCenter.service.ScheduleService;
import Web.EnglishCenter.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class ClassroomRestAPI {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/classrooms")
    public ResponseEntity<List<Classroom>> getAll(){
        return ResponseEntity.ok().body(classroomService.findAll());
    }

    @GetMapping("/classroom/save")
    public ResponseEntity<Classroom> getTestSave(){
        Teacher teacher=usersService.findTeacher(6);
        Course course=courseService.findById(1);
        Classroom classroom=new Classroom(LocalDate.now(),LocalDate.now().plusMonths(3),teacher,course);
        return ResponseEntity.ok().body(classroomService.save(classroom));
    }

    @PostMapping("/classroom/addschedule")
    public  ResponseEntity<Classroom> postTestAddScheduleToClass(Integer schedule_id){
        log.info(schedule_id+"");
        Schedule schedule=scheduleService.findById(schedule_id);
        Classroom classroom=classroomService.findById(1);
        classroom.addSchedule(schedule);
        return ResponseEntity.ok().body(classroomService.save(classroom));
    }
}
