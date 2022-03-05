package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.course.UsersCourseRequestKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersCourseRequestRepo extends JpaRepository<UsersCourseRequest, UsersCourseRequestKey> {
}
