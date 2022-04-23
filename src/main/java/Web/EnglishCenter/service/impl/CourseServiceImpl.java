package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.repo.CourseRepo;
import Web.EnglishCenter.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public Course save(Course course) {
        if(course.getCreateDate()==null)course.setCreateDate(LocalDate.now());
        if(course.getModifiedDate()==null)course.setModifiedDate(LocalDate.now());
        return courseRepo.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepo.delete(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course findById(int id) {
        return courseRepo.findById(id).get();
    }

    @Override
    public Course findCourseByClassroom(int classroomId) {
        return courseRepo.findCourseByClassroomId(classroomId);
    }

    @Override
    public List<Course> findByName(String name) {
        return courseRepo.findByName(name);
    }

    /**
     * @author VQKHANH
     * @param idOrCourseName
     * @return
     */
    @Override
    public List<Course> findByIdOrCourseName(String idOrCourseName){
        List<Course> courses=new ArrayList<>();
        log.info(idOrCourseName);
        try {
            log.info("Its ID");
            int id=Integer.parseInt(idOrCourseName);
            Course course=courseRepo.getById(id);
            if(course!=null) courses.add(course);
        }catch (Exception e){
            log.info("Its name");
            List<Course> temp=courseRepo.findByName(idOrCourseName);
            log.info("temp:"+temp.size());
            if(temp.size()>0)courses.addAll(temp);
        }
            log.info("courses:"+courses.size());
        for (Course course: courses) {
            course.setClassrooms(null);
            course.setUserRequestCourses(null);
            log.info(course.toString());
        }
        return courses;
    }
}
