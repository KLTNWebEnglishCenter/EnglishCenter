package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Schedule;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
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
import java.util.Collections;
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
    public ResponseEntity<List<ClassroomDTO>> getAll(){
        List<Classroom> classrooms = classroomService.findAll();
        List<ClassroomDTO> classroomDTOS = new ArrayList<>();
        classrooms.forEach(c->{
            ClassroomDTO classroom = new ClassroomDTO(c.getId(),c.getStartDate(),c.getEndDate(),c.getStatus(),c.getClassname(),c.getMaxMember(),c.getCreateDate(),c.getModifiedDate());
            Teacher teacher = usersService.findTeacherByClassroomId(c.getId());
            Course course = courseService.findCourseByClassroom(c.getId());
            classroom.setTeacher(teacher);
            classroom.setCourse(course);
            classroomDTOS.add(classroom);
        });
        return ResponseEntity.ok().body(classroomDTOS);
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

    @PostMapping("/classroom/save")
    public ResponseEntity<Classroom> saveClassroom(@RequestBody Classroom classroom){
        return ResponseEntity.ok().body(classroomService.save(classroom));
    }

    @GetMapping("/classroom/addTeacherAndCourseToClassroom/{classroomId}/{teacherId}/{courseId}")
    public ResponseEntity<Classroom> addTeacherToClassroom(@PathVariable(value = "teacherId") int teacherId,@PathVariable(value = "courseId") int courseId,@PathVariable(value = "classroomId") int classroomId){
        Classroom c = classroomService.findById(classroomId);
        Teacher teacher = usersService.findTeacher(teacherId);
        Course course = courseService.findById(courseId);
//        ClassroomDTO classroomDTO = new ClassroomDTO(c.getId(),c.getStartDate(),c.getEndDate(),c.getStatus(),c.getClassname(),c.getMaxMember(),c.getCreateDate(),c.getModifiedDate());
//        classroomDTO.setTeacher(teacher);
        c.setTeacher(teacher);
        c.setCourse(course);
        return ResponseEntity.ok().body(classroomService.save(c));
    }
    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<ClassroomDTO> findTeacher(@PathVariable int classroomId){
        Classroom c = classroomService.findById(classroomId);
        ClassroomDTO classroom = new ClassroomDTO(c.getId(),c.getStartDate(),c.getEndDate(),c.getStatus(),c.getClassname(),c.getMaxMember(),c.getCreateDate(),c.getModifiedDate());
        Teacher teacher = usersService.findTeacherByClassroomId(c.getId());
        Course course = courseService.findCourseByClassroom(c.getId());
        classroom.setTeacher(teacher);
        classroom.setCourse(course);
        return ResponseEntity.ok().body(classroom);
    }

    @GetMapping("/classroom/delete/{classroomId}")
    public ResponseEntity<Classroom> deleteClassroom(@PathVariable("classroomId") int classroomId){
        Classroom classroom = classroomService.findById(classroomId);
        classroomService.delete(classroom);
        return ResponseEntity.ok().body(classroom);
    }
}
