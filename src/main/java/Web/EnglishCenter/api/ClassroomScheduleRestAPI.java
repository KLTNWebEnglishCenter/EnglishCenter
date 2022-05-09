package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.schedule.ClassroomSchedule;
import Web.EnglishCenter.entity.schedule.ClassroomScheduleKey;
import Web.EnglishCenter.entity.schedule.Schedule;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
import Web.EnglishCenter.entityDTO.ClassroomScheduleDTO;
import Web.EnglishCenter.entityDTO.ScheduleInfoHolder;
import Web.EnglishCenter.service.ClassroomScheduleService;
import Web.EnglishCenter.service.ClassroomService;
import Web.EnglishCenter.service.ScheduleService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import Web.EnglishCenter.utils.JwtHelper;
import Web.EnglishCenter.utils.ScheduleHelper;
import Web.EnglishCenter.utils.UsersType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class ClassroomScheduleRestAPI {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ClassroomScheduleService classroomScheduleService;

    private ScheduleHelper utils=new ScheduleHelper();

    private ConvertDTOHelper convertDTOHelper = new ConvertDTOHelper();

    @PostMapping("/classroom/schedule/save")
    public ResponseEntity<ClassroomSchedule> save(@RequestBody ClassroomSchedule classroomSchedule){
        return ResponseEntity.ok().body(classroomScheduleService.save(classroomSchedule));
    }

    @GetMapping("/classroom/schedules")
    public ResponseEntity<List<ClassroomScheduleDTO>> getAll(){
        List<ClassroomScheduleDTO> list = new ArrayList<>();
        List<ClassroomSchedule> scheduleList = classroomScheduleService.findAll();
        scheduleList.forEach(classroomSchedule -> {
            Classroom classroom = classroomService.findById(classroomSchedule.getClassroomScheduleKey().getClassroomId());
            Schedule schedule = scheduleService.findById(classroomSchedule.getClassroomScheduleKey().getScheduleId());
            ClassroomScheduleDTO dto = new ClassroomScheduleDTO(classroom,schedule,classroomSchedule.getLocation(),classroomSchedule.getMeetingInfo(),classroomSchedule.getType());
            list.add(dto);
        });
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/classroom/schedule/{id}")
    public ResponseEntity<List<ClassroomSchedule>> getScheduleByClassroom(@PathVariable int id){
        ClassroomScheduleKey key = new ClassroomScheduleKey(id,36);
        List<ClassroomSchedule> scheduleList = classroomScheduleService.findByKey(key);
        return ResponseEntity.ok().body(scheduleList);
    }

    @GetMapping("/classroom/schedules/{id}")
    public ResponseEntity<List<ScheduleInfoHolder>> getSchedulesByClassroomId(@PathVariable int id){
        List<ScheduleInfoHolder> holders = new ArrayList<>();
        List<ClassroomSchedule> scheduleList = classroomScheduleService.findByClassroomId(id);
        Classroom classroom = classroomService.findById(id);
        Teacher teacher = usersService.findTeacherByClassroomId(id);
        scheduleList.forEach(classroomSchedule -> {
            ScheduleInfoHolder scheduleInfoHolder = new ScheduleInfoHolder();
            scheduleInfoHolder.setClassname(classroom.getClassname());
            scheduleInfoHolder.setDayOfWeek(classroomSchedule.getSchedule().getDayOfWeek());
            scheduleInfoHolder.setMeetingInfo(classroomSchedule.getMeetingInfo());
            scheduleInfoHolder.setLesson(classroomSchedule.getSchedule().getLesson());
            scheduleInfoHolder.setLocation(classroomSchedule.getLocation());
            scheduleInfoHolder.setTeacherName(teacher.getFullName());
            scheduleInfoHolder.setType(classroomSchedule.getType());
            holders.add(scheduleInfoHolder);
        });
        return ResponseEntity.ok().body(holders);
    }

    @PostMapping("/classroom/schedule/day")
    public ResponseEntity<List<ScheduleInfoHolder>> getclassroomScheduleOnDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate, @RequestParam String classroomId){
        String dayOfWeek = utils.getDayOfWeekOfSpecifyDate(selectedDate);
        int id = Integer.parseInt(classroomId);
        List<ScheduleInfoHolder> holders = new ArrayList<>();
        List<ClassroomSchedule> scheduleList = classroomScheduleService.findByClassroomId(id);
        Classroom classroom = classroomService.findById(id);
        Teacher teacher = usersService.findTeacherByClassroomId(id);
        scheduleList.forEach(classroomSchedule -> {
            if (classroomSchedule.getSchedule().getDayOfWeek().equals(dayOfWeek)){
                ScheduleInfoHolder scheduleInfoHolder = new ScheduleInfoHolder();
                scheduleInfoHolder.setScheduleId(classroomSchedule.getSchedule().getId());
                scheduleInfoHolder.setClassname(classroom.getClassname());
                scheduleInfoHolder.setDayOfWeek(classroomSchedule.getSchedule().getDayOfWeek());
                scheduleInfoHolder.setMeetingInfo(classroomSchedule.getMeetingInfo());
                scheduleInfoHolder.setLesson(classroomSchedule.getSchedule().getLesson());
                scheduleInfoHolder.setLocation(classroomSchedule.getLocation());
                scheduleInfoHolder.setTeacherName(teacher.getFullName());
                scheduleInfoHolder.setType(classroomSchedule.getType());
                holders.add(scheduleInfoHolder);
            }
        });

        return ResponseEntity.ok().body(holders);
    }

    @GetMapping("/classroom/schedule/delete/{classroomId}/{scheduleId}")
    public ResponseEntity<String> deleteClassroomSchedule(@PathVariable int classroomId,@PathVariable int scheduleId){
        try {
            ClassroomScheduleKey key = new ClassroomScheduleKey(classroomId,scheduleId);
            List<ClassroomSchedule> scheduleList =  classroomScheduleService.findByKey(key);
            classroomScheduleService.delete(scheduleList.get(0));
            return ResponseEntity.ok().body("DeleteAccess");
        }catch (Exception e){
            return ResponseEntity.ok().body("DeleteFail");
        }
    }
}
