package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.user.*;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.service.impl.AmazonClient;
import Web.EnglishCenter.utils.JwtHelper;
import Web.EnglishCenter.utils.UsersType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class UsersRestAPI {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AmazonClient amazonClient;
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

    @PostMapping("/user/update")
    public ResponseEntity<Users> update(@RequestBody Users users){
        Users users1 = usersService.findById(users.getId());
        users1.setFullName(users.getFullName());
        users1.setEmail(users.getEmail());
        users1.setDob(users.getDob());
        users1.setGender(users.getGender());
        users1.setPhoneNumber(users.getPhoneNumber());
        users1.setImg(users.getImg());
        return ResponseEntity.ok().body(usersService.update(users1));
    }

//    @GetMapping("/user/save")
//    public ResponseEntity<Users> testSave(){
//        Users users=new Users("khanh123","123456","voquockhanh","khanh123@mail.com");
//        Authentication authentication= authenticationService.findById(1);
//        users.setAuthentication(authentication);
//        return ResponseEntity.ok().body(usersService.save(users));
//    }

    @PostMapping("/user/fromToken")
    public ResponseEntity<Users> getUserFromToken(HttpServletRequest request){
        JwtHelper jwtHelper = new JwtHelper();
        String token = jwtHelper.getJwtFromRequest(request);
//        log.info(token);
        String username = jwtHelper.getUsernameFromJWT(token);
        return ResponseEntity.ok().body(usersService.findByUsername(username));
    }

    @PostMapping("/user/author")
    public ResponseEntity<String> getAuthorFromToken(HttpServletRequest request){
        JwtHelper jwtHelper = new JwtHelper();
        String token = jwtHelper.getJwtFromRequest(request);
//        log.info(token);
        String author = jwtHelper.getAuthorities(token);
        return ResponseEntity.ok().body(author);
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

    @PostMapping("/user/change/password")
    public ResponseEntity<String> updatePassword(@RequestParam String id,@RequestParam String oldPass,@RequestParam String newPass){
        Users users = usersService.updatePassword(Integer.parseInt(id),oldPass,newPass);
        if (users != null){
            return ResponseEntity.ok().body("true");
        }
        return ResponseEntity.ok().body("false");
    }

    @PostMapping("/user/profile/uploadImg")
    public ResponseEntity<String> uploadFileProfile(@RequestPart(value = "file") MultipartFile file) {
        String link=amazonClient.uploadFile(file);
        return ResponseEntity.ok().body(link);
    }

}
