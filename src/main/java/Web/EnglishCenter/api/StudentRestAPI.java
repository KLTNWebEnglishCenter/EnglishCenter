package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersCourseRequestService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.RoleType;
import Web.EnglishCenter.utils.UserRequestStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class StudentRestAPI {

    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersCourseRequestService usersCourseRequestService;

    /**
     * get list of all student
     * @author VQKHANH
     * @return
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAll(){
        return ResponseEntity.ok().body(usersService.findAllStudent());
    }

    /**
     * get student info by id
     * @author VQKHANH
     * @param studentId
     * @return
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> findStudent(@PathVariable(value = "studentId") int studentId){
//        log.info(studentId+"");
        return ResponseEntity.ok().body(usersService.findStudent(studentId));
    }

    /**
     * @author VQKHANH
     * @param student
     * @return data after saved to db
     */
    @PostMapping("/student/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
//        log.info(student.toString());
        if(student.getId()==0||student.getAuthentication()==null){
            Authentication authentication= authenticationService.findByRoleName(RoleType.STUDENT);
            if (authentication == null) {
                log.info("Don't have role " + RoleType.STUDENT + " in DB");
            }
            student.setAuthentication(authentication);
        }
        return ResponseEntity.ok().body((Student) usersService.save(student));
    }

    /**
     * @author VQKHANH
     * @param courseId
     * @return
     */
    @GetMapping("/student/course/{courseId}")
    public ResponseEntity<List<Student>> findStudentRequestJoinCourseByCourseId(@PathVariable int courseId){
        List<UsersCourseRequest> usersCourseRequests=usersCourseRequestService.findByCourseId(courseId);
        List<Student> students=new ArrayList<>();
        if(usersCourseRequests.size()>0)
            for (UsersCourseRequest usersCourseRequest: usersCourseRequests) {
                students.add(usersCourseRequest.getStudent());
            }
        return ResponseEntity.ok().body(students);
    }

    /**
     * @author VQKHANH
     * @param studentId
     * @param courseId
     * @return
     */
    @PutMapping("/student/requestcourse/status/{studentId}/{courseId}")
    public ResponseEntity<UsersCourseRequest> updateStudentRequestCourseStatus(@PathVariable int studentId,@PathVariable int courseId){
       UsersCourseRequest usersCourseRequest=usersCourseRequestService.findByCourseIdAndStudentId(courseId,studentId);
       if(usersCourseRequest!=null) usersCourseRequest.setStatus(UserRequestStatus.APPROVED);
       return ResponseEntity.ok().body( usersCourseRequestService.save(usersCourseRequest));
    }
}
