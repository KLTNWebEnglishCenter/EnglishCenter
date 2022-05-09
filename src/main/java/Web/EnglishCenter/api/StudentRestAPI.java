package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
import Web.EnglishCenter.entityDTO.NotificationDTO;
import Web.EnglishCenter.entityDTO.UsersCourseRequestDTO;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.ClassroomService;
import Web.EnglishCenter.service.UsersCourseRequestService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class StudentRestAPI {

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
     * @param studentId
     * @return
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> findStudent(@PathVariable(value = "studentId") int studentId){
//        log.info(studentId+"");
        return ResponseEntity.ok().body(usersService.findStudent(studentId));
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



    /**
     * Duyệt học viên yêu cầu tham gia khóa học (đã hoàn tất học phí)
     * @author VQKHANH
     * @param courseId
     * @return
     */
    @GetMapping("/student/course/{courseId}")
    public ResponseEntity<List<Student>> findStudentRequestJoinCourseByCourseId(@PathVariable int courseId){
        List<UsersCourseRequest> usersCourseRequests=usersCourseRequestService.findByCourseId(courseId);
        List<Student> students=new ArrayList<>();
        if(usersCourseRequests.size()>0)
            for (UsersCourseRequest usersCourseRequest: usersCourseRequests) {
                students.add(usersCourseRequest.getStudent());
            }
        return ResponseEntity.ok().body(students);
    }

    /**
     *
     * @author VQKHANH
     * @param studentId
     * @param courseId
     * @param status
     * @return
     */
    @PostMapping("/student/requestcourse/status/")
    public ResponseEntity<UsersCourseRequest> updateStudentRequestCourseStatus(@RequestParam int studentId,@RequestParam int courseId, @RequestParam String status){
       UsersCourseRequest usersCourseRequest=usersCourseRequestService.findByCourseIdAndStudentId(courseId,studentId);
       if(usersCourseRequest!=null && !usersCourseRequest.getStatus().equalsIgnoreCase(UserRequestStatus.APPROVED)){
           usersCourseRequest.setStatus(status);
           return ResponseEntity.ok().body( usersCourseRequestService.save(usersCourseRequest));
       }
       return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }


    /**
     * for manage classroom of student
     * @author VQKHANH
     * @param request
     * @return
     */
    @GetMapping("/student/classrooms")
    public ResponseEntity<List<ClassroomDTO>> getClassroomsOfStudent(HttpServletRequest request){
        //get student info from access token
        Student student= (Student) jwtHelper.getUserFromRequest(request, UsersType.STUDENT);
        List<ClassroomDTO> classroomDTOS=new ArrayList<>();
        if(student.getClassrooms().size()>0){
            classroomDTOS=convertDTOHelper.convertListClassroom(student.getClassrooms());
        }
        return ResponseEntity.ok().body(classroomDTOS);
    }

    /**
     * for manage classroom of student
     * @param classroomId
     * @param request
     * @return
     */
    @GetMapping("/student/classroom/{classroomId}")
    public ResponseEntity<ClassroomDTO> getClassroomOfStudent(@PathVariable int classroomId,HttpServletRequest request){
        //get student info from access token
//        Student student= (Student) jwtHelper.getUserFromRequest(request, UsersType.STUDENT);

        Classroom classroom=classroomService.findById(classroomId);
        ClassroomDTO classroomDTO=convertDTOHelper.convertClassroomContainNotificationsTeacherAndCourse(classroom);
        return ResponseEntity.ok().body(classroomDTO);
    }

    /**
     * for manage classroom of student
     * @param request
     * @return
     */
    @GetMapping("/student/notifications")
    public ResponseEntity<List<NotificationDTO>> getNotificationsOfSTudent(HttpServletRequest request){
        //get student info from access token
        Student student= (Student) jwtHelper.getUserFromRequest(request, UsersType.STUDENT);

        List<Notification> notifications=new ArrayList<>();
        for (Classroom classroom: student.getClassrooms()) {
            notifications.addAll(classroom.getNotifications());
        }
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        if(notifications.size()>0){
            notificationDTOS=convertDTOHelper.convertListNotification(notifications);
        }
        return ResponseEntity.ok().body(notificationDTOS);
    }

}
