package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
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

    /**
     * Find notification list were created by specify teacher (need access token)
     * @author VQKHANH
     * @param request
     * @return
     */
    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> getAll(HttpServletRequest request){
        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request,UsersType.TEACHER);
        //find notification list were created by specify teacher
        List<Notification> notifications=notificationService.findByTeacherId(teacher.getId());
        //convert notification list to notificationdto list
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        if(notifications.size()>0){
            notificationDTOS=convertDTOHelper.convertListNotification(notifications);
        }
//        List<NotificationDTO> notificationDTOS=convertDTOHelper.convertListNotification(notifications);
        return ResponseEntity.ok().body(notificationDTOS);
    }

    /**
     * @author VQKHANH
     * @param notificationId
     * @return
     */
    @GetMapping("/notification/{notificationId}")
    public ResponseEntity<NotificationDTO> getNotificationById(HttpServletRequest request,@PathVariable int notificationId){
        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request,UsersType.TEACHER);

        Notification notification=notificationService.findById(notificationId,teacher.getId());

        NotificationDTO notificationDTO=null;
        if(notification!=null)
            notificationDTO=convertDTOHelper.convertNotification(notification);
        return ResponseEntity.ok().body(notificationDTO);
    }

    /**
     * Save notification (need access token)
     * @author VQKHANH
     * @param request
     * @param notification
     * @return
     */
    @PostMapping("/notification/save")
    public  ResponseEntity<NotificationDTO> saveNotification(HttpServletRequest request, @RequestBody Notification notification){
        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request,UsersType.TEACHER);
        if(notification.getTeacher()==null){
            notification.setTeacher(teacher);
        }

        Notification notification1=notificationService.save(notification);
        //convert notification to notificationdto
        NotificationDTO notificationDTO=convertDTOHelper.convertNotification(notification);
        return ResponseEntity.ok().body(notificationDTO);
    }

    /**
     * Delete notification by notificationId
     * @author VQKHANH
     * @param notificationId
     * @return "Delete success!" if no error occur
     */
    @DeleteMapping("/notification/{notificationId}")
    public ResponseEntity<String> deleteNotification(HttpServletRequest request,@PathVariable int notificationId){
        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request,UsersType.TEACHER);

        String msg=notificationService.delete(new Notification(notificationId),teacher.getId());
        return ResponseEntity.ok().body(msg);
    }

    @PostMapping("/notification/search")
    public ResponseEntity<List<NotificationDTO>> searchNotification(HttpServletRequest request,@RequestParam String id,@RequestParam String classroomIdOrClassname){
        List<Notification> notifications=new ArrayList<>();

        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request,UsersType.TEACHER);
        if(id.equals("")&&classroomIdOrClassname.equals("")){
            //find notification list were created by specify teacher
            notifications=notificationService.findByTeacherId(teacher.getId());
            List<NotificationDTO> notificationDTOS=new ArrayList<>();
            if(notifications.size()>0){
                notificationDTOS=convertDTOHelper.convertListNotification(notifications);
            }
            return ResponseEntity.ok().body(notificationDTOS);
        }
        else if(!id.equals("")&&classroomIdOrClassname.equals("")){
            Notification notification=notificationService.findById(Integer.parseInt(id),teacher.getId());
            if(notification!=null)
                notifications.add(notification);
        }else if(id.equals("")&&!classroomIdOrClassname.equals("")){
            notifications.addAll(notificationService.findNotificationByClassroomIdOrClassname(classroomIdOrClassname,teacher.getId()));
        }else{
            int temp=0;
            if(!id.equals("")) temp=Integer.parseInt(id);
            notifications.addAll(notificationService.searchNotification(temp,classroomIdOrClassname, teacher.getId()));
        }
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        if(notifications.size()>0){
            notificationDTOS=convertDTOHelper.convertListNotification(notifications);
        }
        return ResponseEntity.ok().body(notificationDTOS);
    };

}
