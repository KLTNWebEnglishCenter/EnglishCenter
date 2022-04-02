package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Schedule;
import Web.EnglishCenter.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class ScheduleRestAPI {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getAll(){
        return ResponseEntity.ok().body(scheduleService.findAll());
    }

    @GetMapping("/schedule/save")
    public ResponseEntity<Schedule> getTestSave(){
        Schedule schedule=new Schedule("T2","4-6");
        return ResponseEntity.ok().body(scheduleService.save(schedule));
    }
}
