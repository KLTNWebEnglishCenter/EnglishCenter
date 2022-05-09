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

    @Query(value = "select * from users_course_request where status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApproved();

    @Query(value = "select * from users_course_request where course_id=:courseId and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithCourseId(int courseId);

    @Query(value = "select * from users_course_request where course_id in \n" +
            "(select id from course where name like %:name%) and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithCourseName(String name);

    @Query(value = "select * from users_course_request where student_id=:studentId and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithStudentId(int studentId);

    @Query(value = "select * from users_course_request where student_id in\n" +
            "(select id from users where full_name like %:fullName%) and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithStudentName(String fullName);

    @Query(value = "select * from users_course_request where course_id=:courseId and student_id=:studentId and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithCourseIdAndStudentId(int courseId,int studentId);

    @Query(value = "select * from users_course_request where student_id in\n" +
            "(select id from users where full_name like %:fullName%) and course_id in \n" +
            "(select id from course where name like %:name%) and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithCourseNameAndStudentName(String name,String fullName);

    @Query(value = "select * from users_course_request where course_id=:courseId and student_id in\n" +
            "(select id from users where full_name like %:fullName%) and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithCourseIdAndStudentName(int courseId,String fullName);

    @Query(value = "select * from users_course_request where course_id in \n" +
            "(select id from course where name like %:name%) and student_id=:studentId and status!=N'Đã duyệt'",nativeQuery = true)
    List<UsersCourseRequest> findAllExceptApprovedWithCourseNameAndStudentId(String name,int studentId);

    @Query(value = "select * from users_course_request where course_id=:courseId and status=N'Hoàn tất học phí'",nativeQuery = true)
    List<UsersCourseRequest> findByCourseId(int courseId);

    @Query(value = "select * from users_course_request where course_id=:courseId and student_id=:studentId", nativeQuery = true)
    UsersCourseRequest findByCourseIdAndStudentId(int courseId,int studentId);
}
