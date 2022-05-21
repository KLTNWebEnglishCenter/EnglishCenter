package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {

    @Query(value = "select * from notification where id=:id and teacher_id=:teacherId",nativeQuery = true)
    public Notification findById(int id, int teacherId);

    @Query(value = "select * from notification where teacher_id=:teacherId order by modified_date desc",nativeQuery = true)
    public List<Notification> findByTeacherId(int teacherId);

    @Query(value = "select * from notification where classroom_id=:classroomId and teacher_id=:teacherId order by modified_date desc",nativeQuery = true)
    public List<Notification> findByClassroomId(int classroomId,int teacherId);

    @Query(value = "select * from notification where teacher_id=:teacherId and classroom_id in ( select id from classroom where classname=:classname) order by modified_date desc", nativeQuery = true)
    public List<Notification> findByClassname(String classname, int teacherId);

    @Query(value = "select * from notification where classroom_id in (\n" +
            "select classroom_id from users_classroom where student_id=:studentId\n" +
            ") order by modified_date desc",nativeQuery = true)
    public List<Notification> findByStudentId(int studentId);
}

