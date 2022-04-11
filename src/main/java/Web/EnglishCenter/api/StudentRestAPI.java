package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class StudentRestAPI {

    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthenticationService authenticationService;

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
     * @param studentid
     * @return
     */
    @GetMapping("/student/{studentid}")
    public ResponseEntity<Student> findStudent(@PathVariable(value = "studentid") int studentid){
//        log.info(studentid+"");
        return ResponseEntity.ok().body(usersService.findStudent(studentid));
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
}
