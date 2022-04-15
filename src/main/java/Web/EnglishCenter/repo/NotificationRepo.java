package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {

    @Query(value = "select * from notification where teacher_id=:teacherId",nativeQuery = true)
    public List<Notification> findByTeacherId(int teacherId);

}
