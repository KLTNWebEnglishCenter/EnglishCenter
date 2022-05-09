package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.repo.UsersCourseRequestRepo;
import Web.EnglishCenter.service.UsersCourseRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UsersCourseRequestServiceImpl implements UsersCourseRequestService {

    @Autowired
    private UsersCourseRequestRepo usersCourseRequestRepo;


    @Override
    public UsersCourseRequest save(UsersCourseRequest usersCourseRequest) {

        return usersCourseRequestRepo.save(usersCourseRequest);
    }

    @Override
    public void delete(UsersCourseRequest usersCourseRequest) {
        usersCourseRequestRepo.delete(usersCourseRequest);
    }

    @Override
    public List<UsersCourseRequest> findAll() {
        return usersCourseRequestRepo.findAll();
    }


    /**
     * @author VQKHANH
     * @param courseIdOrName
     * @param studentIdOrFullName
     * @return
     */
    @Override
    public List<UsersCourseRequest> search(String courseIdOrName, String studentIdOrFullName) {
        if(!courseIdOrName.equals("")&&studentIdOrFullName.equals("")){
            log.info("findAllExceptApprovedWithCourseIdOrCourseName");
            return findAllExceptApprovedWithCourseIdOrCourseName(courseIdOrName);
        }else if(courseIdOrName.equals("")&&!studentIdOrFullName.equals("")){
            log.info("findAllExceptApprovedWithStudentIdOrStudentName");
            return findAllExceptApprovedWithStudentIdOrStudentName(studentIdOrFullName);
        }else if(courseIdOrName.equals("")&&studentIdOrFullName.equals("")){
            log.info("findAllExceptApproved");
            return findAllExceptApproved();
        }else{
            int courseId=-1;
            int studentId=-1;
            try {
                 courseId=Integer.parseInt(courseIdOrName);
            }catch (Exception exception){

            }
            try {
                studentId=Integer.parseInt(studentIdOrFullName);
            }catch (Exception exception){

            }
            log.info("courseId:"+courseId);
            log.info("studentId:"+studentId);
            if(courseId!=-1&&studentId!=-1)return findAllExceptApprovedWithCourseIdAndStudentId(courseId,studentId);
            else if(courseId!=-1&&studentId==-1)return findAllExceptApprovedWithCourseIdAndStudentName(courseId,studentIdOrFullName);
            else if(courseId==-1&&studentId!=-1)return findAllExceptApprovedWithCourseNameAndStudentId(courseIdOrName,studentId);
            else return findAllExceptApprovedWithCourseNameAndStudentName(courseIdOrName,studentIdOrFullName);
        }

    }

    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseIdOrCourseName(String courseIdOrName) {
        try {
            int courseId=Integer.parseInt(courseIdOrName);
            return findAllExceptApprovedWithCourseId(courseId);
        }catch (Exception exception){
            return findAllExceptApprovedWithCourseName(courseIdOrName);
        }
    }

    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithStudentIdOrStudentName(String studentIdOrFullName) {
       try {
           int studentId=Integer.parseInt(studentIdOrFullName);
           return findAllExceptApprovedWithStudentId(studentId);
       }catch (Exception exception){
           return findAllExceptApprovedWithStudentName(studentIdOrFullName);
       }
    }

    /**
     * for update student request course status
     * @author VQKHANH
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApproved() {
        return usersCourseRequestRepo.findAllExceptApproved();
    }

    /**
     * for search
     * @author VQKHANH
     * @param courseId
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseId(int courseId) {
        return usersCourseRequestRepo.findAllExceptApprovedWithCourseId(courseId);
    }

    /**
     * for search
     * @author VQKHANH
     * @param name
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseName(String name) {
        return usersCourseRequestRepo.findAllExceptApprovedWithCourseName(name);
    }

    /**
     * for search
     * @author VQKHANH
     * @param studentId
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithStudentId(int studentId) {
        return usersCourseRequestRepo.findAllExceptApprovedWithStudentId(studentId);
    }

    /**
     * for search
     * @author VQKHANH
     * @param fullName
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithStudentName(String fullName) {
        return usersCourseRequestRepo.findAllExceptApprovedWithStudentName(fullName);
    }

    /**
     * for search
     * @author VQKHANH
     * @param courseId
     * @param studentId
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseIdAndStudentId(int courseId, int studentId) {
        return usersCourseRequestRepo.findAllExceptApprovedWithCourseIdAndStudentId(courseId,studentId);
    }

    /**
     * for search
     * @author VQKHANH
     * @param name
     * @param fullName
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseNameAndStudentName(String name, String fullName) {
        return usersCourseRequestRepo.findAllExceptApprovedWithCourseNameAndStudentName(name,fullName);
    }

    /**
     * for search
     * @author VQKHANH
     * @param courseId
     * @param fullName
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseIdAndStudentName(int courseId, String fullName) {
        return usersCourseRequestRepo.findAllExceptApprovedWithCourseIdAndStudentName(courseId, fullName);
    }

    /**
     * for search
     *@author VQKHANH
     * @param name
     * @param studentId
     * @return
     */
    @Override
    public List<UsersCourseRequest> findAllExceptApprovedWithCourseNameAndStudentId(String name, int studentId) {
        return usersCourseRequestRepo.findAllExceptApprovedWithCourseNameAndStudentId(name, studentId);
    }

    /**
     * @author VQKHANH
     * @param courseId
     * @return
     */
    @Override
    public List<UsersCourseRequest> findByCourseId(int courseId) {
        return usersCourseRequestRepo.findByCourseId(courseId);
    }

    /**
     * @author VQKHANH
     * @param courseId
     * @param studentId
     * @return
     */
    @Override
    public UsersCourseRequest findByCourseIdAndStudentId(int courseId, int studentId) {
        return usersCourseRequestRepo.findByCourseIdAndStudentId(courseId,studentId);
    }

    @Override
    public List<UsersCourseRequest> findByStudentId(int studentId) {
        return usersCourseRequestRepo.findByStudentId(studentId);
    }
}
