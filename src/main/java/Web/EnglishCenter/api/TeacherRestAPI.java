package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class TeacherRestAPI {

    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> findAll(){
        return ResponseEntity.ok().body(usersService.findAllTeacher());
    }

    @GetMapping("/teacher/{teacherid}")
    public ResponseEntity<Teacher> findTeacher(@PathVariable int teacherid){
        return ResponseEntity.ok().body(usersService.findTeacher(teacherid));
    }

    @PostMapping("/teacher/save")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){
        log.info(teacher.toString());
        if(teacher.getId()==0||teacher.getAuthentication()==null){
            Authentication authentication= authenticationService.findById(2);
            teacher.setAuthentication(authentication);
        }
        return ResponseEntity.ok().body((Teacher)usersService.save(teacher));
    }

//    @GetMapping("/teacher/save")
//    public void save(){
//        Teacher teacher = new Teacher("teacher2","123456","teacher2","teacher1@mail.com");
//        Authentication authentication=authenticationService.findById(2);
//        teacher.setAuthentication(authentication);
//        usersService.save(teacher);
//    }

}
