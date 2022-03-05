package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.course.Level;
import Web.EnglishCenter.service.CourseService;
import Web.EnglishCenter.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class CourseRestAPI {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LevelService levelService;

    @PostMapping("/course")
    public ResponseEntity<Course> save(@RequestBody Course course){
        return ResponseEntity.ok().body(courseService.save(course));
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getAll(){
        return ResponseEntity.ok().body(courseService.findAll());
    }

//    @GetMapping("/course/save")
//    public ResponseEntity<Course> testSave(){
//        Course course=new Course("test1",1000000);
//        Level level=new Level("level3");
////        Level level=levelService.findById(1);
//        course.setLevel(level);
//        return ResponseEntity.ok().body(courseService.save(course));
//    }

}
