package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.course.UsersCourseRequest;

import java.util.List;

public interface UsersCourseRequestService {
    public UsersCourseRequest save(UsersCourseRequest usersCourseRequest);
    public void delete(UsersCourseRequest usersCourseRequest);
    public List<UsersCourseRequest> findAll();

}
