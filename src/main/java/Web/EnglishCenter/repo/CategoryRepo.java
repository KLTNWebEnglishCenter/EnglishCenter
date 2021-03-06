package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.course.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    @Query(value = "select * from category where id = (select category_id from course where id =:courseId)",nativeQuery = true)
    Category findCategoryByCourseId(@Param("courseId") int courseId);

}
