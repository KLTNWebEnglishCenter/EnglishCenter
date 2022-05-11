package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.course.Course;

import java.util.List;

public interface CourseService {
    public Course save(Course course);
    public void delete(Course course);
    public List<Course> findAll();
    public Course findById(int id);
    public Course findCourseByClassroom(int classroomId);
    public List<Course> findByName(String name);
    public List<Course> findByIdOrCourseName(String idOrCourseName);
    public List<Course> findByCategory(int categoryId);
    public List<Course> findLimit(int limit);
}
