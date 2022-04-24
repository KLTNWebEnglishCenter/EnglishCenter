package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.Category;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.course.Level;
import Web.EnglishCenter.entityDTO.CourseDTO;
import Web.EnglishCenter.service.CategoryService;
import Web.EnglishCenter.service.CourseService;
import Web.EnglishCenter.service.LevelService;
import Web.EnglishCenter.service.UsersCourseRequestService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class CourseRestAPI {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UsersCourseRequestService usersCourseRequestService;

    private ConvertDTOHelper convertDTOHelper=new ConvertDTOHelper();

    @PostMapping("/course")
    public ResponseEntity<Course> save(@RequestBody Course course){
        return ResponseEntity.ok().body(courseService.save(course));
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> getAll(){
        List<Course> courses = courseService.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        courses.forEach(e->{
            CourseDTO courseDTO = new CourseDTO(e.getId(),e.getName(),e.getDescription(),e.getPrice(),e.getCreateDate(),e.getModifiedDate(),e.getDiscount());
            courseDTO.setEnable(e.isEnable());
            Category category = categoryService.findCategoryByCourseId(e.getId());
            category.setCourses(null);
            Level level = levelService.findLevelByCourseId(e.getId());
            level.setCourses(null);
            courseDTO.setCategory(category);
            courseDTO.setLevel(level);
            courseDTOS.add(courseDTO);
        });
        return ResponseEntity.ok().body(courseDTOS);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id){
        return ResponseEntity.ok().body(courseService.findById(id));
    }

    @GetMapping("/course/delete/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id){
        Course course = courseService.findById(id);
        courseService.delete(course);
        return ResponseEntity.ok().body(course);
    }

    @GetMapping("/course/addLevelAndCategoryToCourse/{courseId}/{levelId}/{categoryId}")
    public ResponseEntity<Course> addLevelAndCategoryToCourse(@PathVariable(value = "courseId") int courseId, @PathVariable(value = "levelId") int levelId, @PathVariable(value = "categoryId") int categoryId){
        Level level = levelService.findById(levelId);
        Category category = categoryService.findById(categoryId);
        Course course = courseService.findById(courseId);
        course.setLevel(level);
        course.setCategory(category);
        return ResponseEntity.ok().body(courseService.save(course));
    }

    /**
     * @author VQKHANH
     * @param idOrCourseName
     * @return
     */
    @PostMapping("/course/findbyidorcoursename")
    public ResponseEntity<List<CourseDTO>> findByIdOrCourseName(@RequestParam String idOrCourseName){
        List<CourseDTO> courseDTOS=new ArrayList<>();
        List<Course> courses=courseService.findByIdOrCourseName(idOrCourseName);
        if(courses.size()>0)
            courseDTOS=convertDTOHelper.convertListCourse(courses);
        return ResponseEntity.ok().body(courseDTOS);
    };

//    @GetMapping("/course/request/{courseId}")
//    public ResponseEntity<List<Student>> findStudentRequestJoinCourseByCourseId(@PathVariable int courseId){
//        List<UsersCourseRequest> usersCourseRequests=usersCourseRequestService.findByCourseId(courseId);
//        List<Student> students=new ArrayList<>();
//        if(usersCourseRequests.size()>0)
//            for (UsersCourseRequest usersCourseRequest: usersCourseRequests) {
//                students.add(usersCourseRequest.getStudent());
//            }
//        return ResponseEntity.ok().body(students);
//    }
}
