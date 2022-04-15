package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.repo.CourseRepo;
import Web.EnglishCenter.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
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

}
