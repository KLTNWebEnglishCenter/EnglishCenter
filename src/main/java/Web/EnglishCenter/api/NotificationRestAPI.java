package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.NotificationDTO;
import Web.EnglishCenter.repo.UsersRepo;
import Web.EnglishCenter.service.ClassroomService;
import Web.EnglishCenter.service.NotificationService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import Web.EnglishCenter.utils.JwtHelper;
import Web.EnglishCenter.utils.UsersType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/")
@Slf4j
public class NotificationRestAPI {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private JwtHelper jwtHelper;

    private ConvertDTOHelper convertDTOHelper=new ConvertDTOHelper();

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> getAll(HttpServletRequest request){
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request,UsersType.TEACHER);
        List<Notification> notifications=notificationService.findByTeacherId(teacher.getId());
        List<NotificationDTO> notificationDTOS=convertDTOHelper.convertListNotification(notifications);
        return ResponseEntity.ok().body(notificationDTOS);
    }

    @GetMapping("/notification/{notificationId}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable int notificationId){
        Notification notification=notificationService.findById(notificationId);
        NotificationDTO notificationDTO=convertDTOHelper.convertNotification(notification);
        return ResponseEntity.ok().body(notificationDTO);
    }

    @PostMapping("/notification/save")
    public  ResponseEntity<NotificationDTO> saveNotification(@RequestBody Notification notification){
        Notification notification1=notificationService.save(notification);
        NotificationDTO notificationDTO=convertDTOHelper.convertNotification(notification);
        return ResponseEntity.ok().body(notificationDTO);
    }

    @DeleteMapping("/notification/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable int notificationId){
        String msg=notificationService.delete(new Notification(notificationId));
        log.info(msg);
        return ResponseEntity.ok().body(msg);
    }


}
