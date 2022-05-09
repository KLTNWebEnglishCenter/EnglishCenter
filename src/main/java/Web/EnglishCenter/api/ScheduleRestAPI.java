package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.schedule.Schedule;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.ScheduleInfoHolder;
import Web.EnglishCenter.service.ClassroomScheduleService;
import Web.EnglishCenter.service.ScheduleService;
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
public class ScheduleRestAPI {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ClassroomScheduleService classroomScheduleService;

    private ScheduleHelper utils=new ScheduleHelper();

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getAll(){
        return ResponseEntity.ok().body(scheduleService.findAll());
    }

    @GetMapping("/schedule/save")
    public ResponseEntity<Schedule> getTestSave(){
        Schedule schedule=new Schedule("T2","4-6");
        return ResponseEntity.ok().body(scheduleService.save(schedule));
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> getById(@PathVariable int id){
        return ResponseEntity.ok().body(scheduleService.findById(id));
    }


    /**
     * format="yyyy-MM-dd"
     * Test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
     * @param request
     * @param selectedDate
     * @return
     */
    @PostMapping("/schedule/teacher")
    public ResponseEntity<List<ScheduleInfoHolder>> getScheduleOfTeaher(HttpServletRequest request, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate){
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request, UsersType.TEACHER);

//        LocalDate localDate=utils.convertStringToLocalDate(selectedDate);

        String dayOfWeek=utils.getDayOfWeekOfSpecifyDate(selectedDate);

        List<String> list=classroomScheduleService.getScheduleOfTeacher(teacher.getId(),selectedDate,dayOfWeek);
        List<ScheduleInfoHolder> scheduleInfoHolders = new ArrayList<>();
        scheduleInfoHolders.addAll(utils.convertStringToListScheduleHolder(list));
//        for (ScheduleInfoHolder holder: scheduleInfoHolders
//        ) {
//            log.info(holder.toString());
//        }
        return ResponseEntity.ok().body(scheduleInfoHolders);
    }


    @PostMapping("/schedule/student")
    public ResponseEntity<List<ScheduleInfoHolder>> getScheduleOfStudent(HttpServletRequest request, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate){
        Student student= (Student) jwtHelper.getUserFromRequest(request,UsersType.STUDENT);

        String dayOfWeek=utils.getDayOfWeekOfSpecifyDate(selectedDate);

        List<String> list=classroomScheduleService.getScheduleOfStudent(student.getId(),selectedDate,dayOfWeek);
        List<ScheduleInfoHolder> scheduleInfoHolders=utils.convertStringToListScheduleHolder(list);
//        for (ScheduleInfoHolder holder: scheduleInfoHolders
//        ) {
//            log.info(holder.toString());
//        }
        return ResponseEntity.ok().body(scheduleInfoHolders);
    }
}
