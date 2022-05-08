package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.user.Student;

import java.util.List;

public interface UsersCourseRequestService {
    public UsersCourseRequest save(UsersCourseRequest usersCourseRequest);
    public void delete(UsersCourseRequest usersCourseRequest);
    public List<UsersCourseRequest> findAll();

    public List<UsersCourseRequest> findByCourseId(int courseId);
    public  UsersCourseRequest findByCourseIdAndStudentId(int courseId,int studentId);
    public List<UsersCourseRequest> findByStudentId(int studentId);
}
