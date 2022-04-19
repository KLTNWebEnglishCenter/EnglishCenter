package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.repo.UsersCourseRequestRepo;
import Web.EnglishCenter.service.UsersCourseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
     * @param courseId
     * @return
     */
    @Override
    public List<UsersCourseRequest> findByCourseId(int courseId) {
        return usersCourseRequestRepo.findByCourseId(courseId);
    }

    @Override
    public UsersCourseRequest findByCourseIdAndStudentId(int courseId, int studentId) {
        return usersCourseRequestRepo.findByCourseIdAndStudentId(courseId,studentId);
    }
}
