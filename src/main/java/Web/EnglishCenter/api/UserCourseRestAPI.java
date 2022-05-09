package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.service.UsersCourseRequestService;
import Web.EnglishCenter.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class UserCourseRestAPI {

    @Autowired
    private UsersCourseRequestService courseRequestService;

    @PostMapping("/user/signup/course")
    public ResponseEntity<UsersCourseRequest> save(@RequestBody UsersCourseRequest request){
        return ResponseEntity.ok().body(courseRequestService.save(request));
    }

    @GetMapping("/user/signup/course/list")
    public ResponseEntity<List<UsersCourseRequest>> getAll(){
        return ResponseEntity.ok().body(courseRequestService.findAll());
    }
}
