package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entityDTO.UsersCourseRequestDTO;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.ClassroomService;
import Web.EnglishCenter.service.UsersCourseRequestService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import Web.EnglishCenter.utils.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/")
@Slf4j
public class UserRequestCourseStatusRestAPI {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersCourseRequestService usersCourseRequestService;

    @Autowired
    private JwtHelper jwtHelper;

    private ConvertDTOHelper convertDTOHelper=new ConvertDTOHelper();
    /**
     * @author VQKHANH
     * @return
     */
    @GetMapping("/requestcourses")
    public ResponseEntity<List<UsersCourseRequestDTO>> findStudentRequestJoinCourse(){
        List<UsersCourseRequest> usersCourseRequests=usersCourseRequestService.findAllExceptApproved();

        List<UsersCourseRequestDTO> usersCourseRequestDTOS=new ArrayList<>();
        if(usersCourseRequests.size()>0){
            usersCourseRequestDTOS.addAll(convertDTOHelper.convertListUsersCourseRequest(usersCourseRequests));
        }
        return ResponseEntity.ok().body(usersCourseRequestDTOS);
    }


    @PostMapping("/requestcourse/search")
    public ResponseEntity<List<UsersCourseRequestDTO>> search(@RequestParam String courseIdOrName,@RequestParam String studentIdOrFullName){
        log.info(courseIdOrName);
        log.info(studentIdOrFullName);
        List<UsersCourseRequest> usersCourseRequests=usersCourseRequestService.search(courseIdOrName,studentIdOrFullName);

        List<UsersCourseRequestDTO> usersCourseRequestDTOS=new ArrayList<>();
        if(usersCourseRequests.size()>0){
            usersCourseRequestDTOS.addAll(convertDTOHelper.convertListUsersCourseRequest(usersCourseRequests));
        }
        return ResponseEntity.ok().body(usersCourseRequestDTOS);
    }
}
