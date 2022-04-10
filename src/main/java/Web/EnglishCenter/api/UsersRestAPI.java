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

    /**
     * get list of all user
     * @return list of user
     */
    @GetMapping("/users")
    public ResponseEntity<List<Users>>  getAll(){
        return ResponseEntity.ok().body(usersService.findAll());
    }

    /**
     * get one user by id
     * @param userid
     * @return user
     */
    @GetMapping("/user/{userid}")
    public  ResponseEntity<Users> getUser(@PathVariable int userid){
        return ResponseEntity.ok().body(usersService.findById(userid));
    }

    /**
     * @author NHLAM
     * @param users
     * @return
     */
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

    /**
     * search user by id/username/full_name
     * @author VQKHANH
     * @param idOrUsername
     * @param fullName
     * @param dtype data type which you want to get from db
     * @return list of user
     */
    @PostMapping("/user/search")
    public ResponseEntity<List<Users>> searchUser(@RequestParam String idOrUsername, @RequestParam String fullName, @RequestParam String dtype){
        List<Users> users;
        if(!idOrUsername.equals("")&&fullName.equals("")){
            users=usersService.findByIdOrUsername(idOrUsername,dtype);
        }else if(idOrUsername.equals("")&&!fullName.equals("")){
            users=usersService.findByFullName(fullName,dtype);
        }else{
            users=usersService.searchUser(idOrUsername,fullName,dtype);
        }
        return ResponseEntity.ok().body(users);
    };
}
