package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Users;
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
public class UsersRestAPI {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>>  getAll(){
        return ResponseEntity.ok().body(usersService.findAll());
    }

    @GetMapping("/user/{userid}")
    public  ResponseEntity<Users> getUser(@PathVariable int userid){
        return ResponseEntity.ok().body(usersService.findById(userid));
    }

    @PostMapping("/register")
    public ResponseEntity<Users> save(@RequestBody Users users){
        Authentication authentication= authenticationService.findById(1);
        Student student = new Student();
        student.setUsername(users.getUsername());
        student.setPassword(users.getPassword());
        student.setEmail(users.getEmail());
        student.setFullName(users.getFullName());
        student.setAuthentication(authentication);
        return ResponseEntity.ok().body(usersService.save(student));
    }
    @GetMapping("/user/save")
    public ResponseEntity<Users> testSave(){
        Users users=new Users("khanh123","123456","voquockhanh","khanh123@mail.com");
        Authentication authentication= authenticationService.findById(1);
        users.setAuthentication(authentication);
        return ResponseEntity.ok().body(usersService.save(users));
    }
}
