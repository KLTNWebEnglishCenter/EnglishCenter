package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.ClassroomService;
import Web.EnglishCenter.service.CourseService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class TeacherRestAPI {

    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * get list of all teacher
     * @author VQKHANH
     * @return
     */
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> findAll(){
        return ResponseEntity.ok().body(usersService.findAllTeacher());
    }

    /**
     * get teacher info by id
     * @author VQKHANH
     * @param teacherid
     * @return
     */
//    @ResponseBody
    @GetMapping("/teacher/{teacherid}")
    public ResponseEntity<Teacher> findTeacher(@PathVariable(value = "teacherid") int teacherid){
//        log.info(teacherid+"");
        return ResponseEntity.ok().body(usersService.findTeacher(teacherid));
    }

    /**
     *
     * @author VQKHANH
     * @param teacher
     * @return data after saved to db
     */
    @PostMapping("/teacher/save")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){
//        log.info(teacher.toString());
        if(teacher.getId()==0||teacher.getAuthentication()==null){
            Authentication authentication= authenticationService.findByRoleName(RoleType.TEACHER);
            if (authentication == null) {
                log.info("Don't have role " + RoleType.TEACHER + " in DB");
            }
            teacher.setAuthentication(authentication);

        }
        return ResponseEntity.ok().body((Teacher)usersService.save(teacher));
    }

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private CourseService courseService;
    /**
     * @author VQKHANH
     * @param teacherid
     * @return
     */
    @GetMapping("/teacher/classrooms/{teacherid}")
    public ResponseEntity<List<ClassroomDTO>> getAllClassroom(@PathVariable(value = "teacherid") int teacherid){
        Teacher teacher=usersService.findTeacher(teacherid);
        List<Classroom> classrooms=teacher.getClassrooms();

        List<ClassroomDTO> classroomDTOS = new ArrayList<>();
        classrooms.forEach(c->{
            ClassroomDTO classroom = new ClassroomDTO(c.getId(),c.getStartDate(),c.getEndDate(),c.getStatus(),c.getClassname(),c.getMaxMember(),c.getCreateDate(),c.getModifiedDate());

            Course course = courseService.findCourseByClassroom(c.getId());
            classroom.setTeacher(teacher);
            classroom.setCourse(course);
            classroomDTOS.add(classroom);
        });

        return ResponseEntity.ok().body(classroomDTOS);
    }

}
