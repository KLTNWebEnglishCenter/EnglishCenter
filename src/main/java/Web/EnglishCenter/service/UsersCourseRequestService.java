package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.user.Student;

import java.util.List;

public interface UsersCourseRequestService {
    public UsersCourseRequest save(UsersCourseRequest usersCourseRequest);
    public void delete(UsersCourseRequest usersCourseRequest);
    public List<UsersCourseRequest> findAll();

    public List<UsersCourseRequest> search(String courseIdOrName, String studentIdOrFullName);
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseIdOrCourseName(String courseIdOrName);
    public List<UsersCourseRequest> findAllExceptApprovedWithStudentIdOrStudentName(String studentIdOrFullName);
    public List<UsersCourseRequest> findAllExceptApproved();
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseId(int courseId);
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseName(String name);
    public List<UsersCourseRequest> findAllExceptApprovedWithStudentId(int studentId);
    public List<UsersCourseRequest> findAllExceptApprovedWithStudentName(String fullName);
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseIdAndStudentId(int courseId,int studentId);
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseNameAndStudentName(String name,String fullName);
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseIdAndStudentName(int courseId,String fullName);
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseNameAndStudentId(String name,int studentId);

    public List<UsersCourseRequest> findByCourseId(int courseId);
    public  UsersCourseRequest findByCourseIdAndStudentId(int courseId,int studentId);


}
