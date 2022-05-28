package Web.EnglishCenter.api;


import Web.EnglishCenter.api.handel.InUseException;
import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.entityDTO.UsersDTO;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import Web.EnglishCenter.service.impl.AmazonClient;
import Web.EnglishCenter.utils.JwtHelper;
import Web.EnglishCenter.utils.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class UsersRestAPI {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationService authenticationService;


    private ConvertDTOHelper convertDTOHelper=new ConvertDTOHelper();

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
        Authentication authentication= authenticationService.findByRoleName(RoleType.STUDENT);
        Student student = new Student();
        student.setUsername(users.getUsername());
        student.setPassword(users.getPassword());
        student.setEmail(users.getEmail());
        student.setFullName(users.getFullName());
        student.setAuthentication(authentication);
        student.setEnable(true);
        return ResponseEntity.ok().body(usersService.save(student));
    }

    @PostMapping("/user/test")
    public ResponseEntity<String> testBeforeSave(@RequestBody Users users){
        Users usersInDB=  usersService.findByUsername(users.getUsername());
        if(usersInDB!=null&&usersInDB.getId()!= users.getId())
            throw new InUseException("Tên đăng nhập đã bị sử dụng!");
        usersInDB=  usersService.findByEmail(users.getEmail());
        if(usersInDB!=null&&usersInDB.getId()!= users.getId())
            throw new InUseException("Email đã bị sử dụng!");
        usersInDB= usersService.findByPhoneNumber(users.getPhoneNumber());
        if(usersInDB!=null&&usersInDB.getId()!= users.getId())
            throw new InUseException("Số điện thoại đã bị sử dụng!");
        return ResponseEntity.ok().body("Oke");
    }

    @PostMapping("/user/update")
    public ResponseEntity<Users> update(@RequestBody Users users){
        Users usersInDB=  usersService.findByUsername(users.getUsername());
        if(usersInDB!=null&&usersInDB.getId()!= users.getId())
            throw new InUseException("Tên đăng nhập đã bị sử dụng!");
        usersInDB=  usersService.findByEmail(users.getEmail());
        if(usersInDB!=null&&usersInDB.getId()!= users.getId())
            throw new InUseException("Email đã bị sử dụng!");
        usersInDB= usersService.findByPhoneNumber(users.getPhoneNumber());
        if(usersInDB!=null&&usersInDB.getId()!= users.getId())
            throw new InUseException("Số điện thoại đã bị sử dụng!");

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
    public ResponseEntity<UsersDTO> getUserFromToken(HttpServletRequest request){
        JwtHelper jwtHelper = new JwtHelper();
        String token = jwtHelper.getJwtFromRequest(request);
//        log.info(token);
        String username = jwtHelper.getUsernameFromJWT(token);

        Users users=usersService.findByUsername(username);
        UsersDTO usersDTO= convertDTOHelper.convertUsers(users);

        return ResponseEntity.ok().body(usersDTO);
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
        boolean rs = usersService.updatePassword(Integer.parseInt(id),oldPass,newPass);
        return ResponseEntity.ok().body(String.valueOf(rs));
    }

    @PostMapping("/user/profile/uploadImg")
    public ResponseEntity<String> uploadFileProfile(@RequestPart(value = "file") MultipartFile file) {
        String link=amazonClient.uploadFile(file);
        return ResponseEntity.ok().body(link);
    }

    @PostMapping("/user/update/password")
    public ResponseEntity<Users> changePass(@RequestBody Users users) {
        Users users1 = usersService.findById(users.getId());
        users1.setPassword(users.getPassword());
        log.info(users1.toString());
        return ResponseEntity.ok().body(usersService.save(users1));
    }

}
