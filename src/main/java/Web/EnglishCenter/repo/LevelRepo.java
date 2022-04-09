package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.course.Category;
import Web.EnglishCenter.entity.course.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepo extends JpaRepository<Level,Integer> {
    @Query(value = "select * from [dbo].[level] where id = (select level_id from course where id =:courseId)",nativeQuery = true)
    Level findLevelByCourseId(@Param("courseId") int courseId);
}
