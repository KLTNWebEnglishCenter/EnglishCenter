package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.course.UsersCourseRequestKey;
import Web.EnglishCenter.entity.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersCourseRequestRepo extends JpaRepository<UsersCourseRequest, UsersCourseRequestKey> {

    @Query(value = "select * from users_course_request where course_id=:courseId and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findByCourseId(int courseId);

    @Query(value = "select * from users_course_request where course_id=:courseId and student_id=:studentId", nativeQuery = true)
    UsersCourseRequest findByCourseIdAndStudentId(int courseId,int studentId);

    @Query(value = "select * from users_course_request where student_id=:studentId", nativeQuery = true)
    List<UsersCourseRequest> findByStudentId(int studentId);
}
