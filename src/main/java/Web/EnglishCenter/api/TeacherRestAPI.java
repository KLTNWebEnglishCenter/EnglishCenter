package Web.EnglishCenter.api;

import Web.EnglishCenter.api.handel.InUseException;
import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.schedule.Schedule;
import Web.EnglishCenter.entity.user.*;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
import Web.EnglishCenter.entityDTO.ScheduleInfoHolder;
import Web.EnglishCenter.service.*;
import Web.EnglishCenter.utils.JwtHelper;
import Web.EnglishCenter.utils.RoleType;
import Web.EnglishCenter.utils.UsersType;
import Web.EnglishCenter.utils.ScheduleHelper;
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
public class TeacherRestAPI {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtHelper jwtHelper;

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
     * @param teacherId
     * @return
     */
//    @ResponseBody
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Teacher> findTeacher(@PathVariable(value = "teacherId") int teacherId){
//        log.info(teacherId+"");
        return ResponseEntity.ok().body(usersService.findTeacher(teacherId));
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

        Teacher teacherInDB= (Teacher) usersService.findByUsername(teacher.getUsername());
        if(teacherInDB!=null)
            throw new InUseException("Tên đăng nhập đã bị sử dụng!");
        teacherInDB= (Teacher) usersService.findByEmail(teacher.getEmail());
        if(teacherInDB!=null)
            throw new InUseException("Email đã bị sử dụng!");
        teacherInDB= (Teacher) usersService.findByPhoneNumber(teacher.getPhoneNumber());
        if(teacherInDB!=null)
            throw new InUseException("Số điện thoại đã bị sử dụng!");

        return ResponseEntity.ok().body((Teacher)usersService.save(teacher));
    }

    /**
     *
     * @author VQKHANH
     * @param teacher
     * @return data after saved to db
     */
    @PostMapping("/teacher/update")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher){
        if(teacher.getId()==0||teacher.getAuthentication()==null){
            Authentication authentication= authenticationService.findByRoleName(RoleType.TEACHER);
            if (authentication == null) {
                log.info("Don't have role " + RoleType.TEACHER + " in DB");
            }
            teacher.setAuthentication(authentication);

        }

        Users usersInDB= usersService.findByUsername(teacher.getUsername());
        if(usersInDB!=null&&usersInDB.getId()!= teacher.getId())
            throw new InUseException("Tên đăng nhập đã bị sử dụng!");
        usersInDB= usersService.findByEmail(teacher.getEmail());
        if(usersInDB!=null&&usersInDB.getId()!= teacher.getId())
            throw new InUseException("Email đã bị sử dụng!");
        usersInDB= usersService.findByPhoneNumber(teacher.getPhoneNumber());
        if(usersInDB!=null&&usersInDB.getId()!= teacher.getId())
            throw new InUseException("Số điện thoại đã bị sử dụng!");

        Teacher oldTeacher=usersService.findTeacher(teacher.getId());
        teacher.setPassword(oldTeacher.getPassword());

        return ResponseEntity.ok().body((Teacher)usersService.update(teacher));
    }

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private CourseService courseService;
    /**
     * @author VQKHANH
     * @param teacherId
     * @return
     */
    @GetMapping("/teacher/classrooms/{teacherId}")
    public ResponseEntity<List<ClassroomDTO>> getAllClassroom(@PathVariable(value = "teacherId") int teacherId){
        Teacher teacher=usersService.findTeacher(teacherId);
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
