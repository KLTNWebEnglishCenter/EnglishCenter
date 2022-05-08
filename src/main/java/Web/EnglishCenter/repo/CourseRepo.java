package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
    @Query(value = "select *  from course where id = (select course_id from classroom where id =:classroomId)",nativeQuery = true)
    Course findCourseByClassroomId(@Param("classroomId") int classroomId);

    @Query(value = "select * from course where name like %:name%",nativeQuery = true)
    List<Course> findByName(String name);

    @Query(value = "select * from course where category_id =:category_id",nativeQuery = true)
    List<Course> findByCategory(int category_id);

}
