package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.UsersCourseRequestKey;
import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.schedule.ClassroomSchedule;
import Web.EnglishCenter.entity.schedule.ClassroomScheduleKey;
import Web.EnglishCenter.entity.schedule.Schedule;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
import Web.EnglishCenter.service.*;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import Web.EnglishCenter.utils.JwtHelper;
import Web.EnglishCenter.utils.UsersType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private ConvertDTOHelper convertDTOHelper=new ConvertDTOHelper();

    @Autowired
    private ClassroomScheduleService classroomScheduleService;

    @Autowired
    private JwtHelper jwtHelper;

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


    /**
     * Uncomplete => change classroom and schedule construction => can meet error
     * @param schedule_id
     * @return
     */
    @PostMapping("/classroom/addschedule")
    public  ResponseEntity<Classroom> postTestAddScheduleToClass(Integer schedule_id){
        log.info(schedule_id+"");
        Schedule schedule=scheduleService.findById(schedule_id);
        Classroom classroom=classroomService.findById(1);

        ClassroomSchedule classroomSchedule=new ClassroomSchedule();
        classroomSchedule.setClassroom(classroom);
        classroomSchedule.setSchedule(schedule);
        classroomSchedule.setClassroomScheduleKey(new ClassroomScheduleKey(classroom.getId(),schedule.getId()));

        classroom.addSchedule(classroomSchedule);
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
    public ResponseEntity<String> deleteClassroom(@PathVariable("classroomId") int classroomId){
        Classroom classroom = classroomService.findById(classroomId);
        try {
            classroomService.delete(classroom);
            return ResponseEntity.ok().body("DeleteAccess");
        }catch (Exception e) {
            return ResponseEntity.ok().body("DeleteFail");
        }
    }

    /**
     * @author VQKHANH
     * @param courseId
     * @return
     */
    @GetMapping("/classroom/course/{courseId}")
    public ResponseEntity<List<ClassroomDTO>> findClassroomByCourseId(@PathVariable int courseId){
        List<Classroom> classrooms=classroomService.findByCourseID(courseId);
        List<ClassroomDTO> list=new ArrayList<>();
        if(classrooms.size()>0){
            list=convertDTOHelper.convertListClassroomContainStudentAndTeacher(classrooms);
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/classroom/countstudent/{classroomId}")
    public ResponseEntity<Integer> countStudent(@PathVariable int classroomId){
        int count=classroomService.countStudent(classroomId);
        return ResponseEntity.ok().body(count);
    }

    /**
     * @author VQKHANH
     * @param classroomId
     * @param students
     * @return
     */
    @PutMapping("/classroom/addstudent/{classroomId}")
    public ResponseEntity<Classroom> addStudentToClassroom(@PathVariable int classroomId,@RequestBody List<Student> students){
        Classroom classroom=classroomService.findById(classroomId);
        classroom.getStudents().addAll(students);
        return ResponseEntity.ok().body(classroomService.save(classroom));
    }

    @PostMapping("/classroom/findbyidorclassname")
    public ResponseEntity<List<ClassroomDTO>> findByIdOrClassName(@RequestParam String idOrClassName){
        List<ClassroomDTO> classroomDTOS = new ArrayList<>();
        List<Classroom> classrooms = classroomService.findByIdOrClassroomName(idOrClassName);
        if(classrooms.size() > 0){
            classrooms.forEach(c->{
                ClassroomDTO classroom = new ClassroomDTO(c.getId(),c.getStartDate(),c.getEndDate(),c.getStatus(),c.getClassname(),c.getMaxMember(),c.getCreateDate(),c.getModifiedDate());
                Teacher teacher = usersService.findTeacherByClassroomId(c.getId());
                Course course = courseService.findCourseByClassroom(c.getId());
                classroom.setTeacher(teacher);
                classroom.setCourse(course);
                classroomDTOS.add(classroom);
            });
        }
        return ResponseEntity.ok().body(classroomDTOS);
    }

    @GetMapping("/classrooms/students/{id}")
    public Object getListCourse(@PathVariable int id){
        List<Student> students = new ArrayList<>();
        try {
            students.addAll(classroomService.findById(id).getStudents());
        }catch (Exception e){
            return new ArrayList<Student>();
        }
        return ResponseEntity.ok().body(students);
    }

}
