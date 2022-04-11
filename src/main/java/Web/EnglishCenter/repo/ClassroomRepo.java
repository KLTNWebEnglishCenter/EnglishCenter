package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepo extends JpaRepository<Classroom,Integer> {
    @Query(value = "select * from classroom",nativeQuery = true)
    List<Classroom> findAllClassroom();

    @Query(value = "select course_id from classroom where id=:classroomId",nativeQuery = true)
    Integer findCourseId(@Param("classroomId") int classroomID);

    @Query(value = "select teacher_id from classroom where id=:classroomId",nativeQuery = true)
    Integer findTeacherId(@Param("classroomId") int classroomID);

}
