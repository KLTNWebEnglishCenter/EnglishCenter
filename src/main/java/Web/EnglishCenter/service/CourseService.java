package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.course.Course;

import java.util.List;

public interface CourseService {
    public Course save(Course course);
    public void delete(Course course);
    public List<Course> findAll();
    public Course findById(int id);
}
