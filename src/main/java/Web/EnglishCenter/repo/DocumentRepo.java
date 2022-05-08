package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Integer> {
    @Query(value = "select * from document where teacher_id=:teacherId",nativeQuery = true)
    public List<Document> findByTeacherId(int teacherId);

    @Query(value = "select * from document where teacher_id=:teacherId and id=:id",nativeQuery = true)
    public Document findByIdOfSpecifyTeacher(int id,int teacherId);

    @Query(value = "select * from document where teacher_id=:teacherId and name like %:name%",nativeQuery = true)
    public List<Document> findByName(String name,int teacherId);


}
