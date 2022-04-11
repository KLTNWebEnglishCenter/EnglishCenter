package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.user.Employee;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {

    @Query(value ="select * from users where username=:username",nativeQuery = true)
    Users findByUsername(@Param("username") String username);

    @Query(value = "select * from users where dtype = 'Teacher' and id = (select teacher_id from classroom where id =:classroomId)", nativeQuery = true)
    Teacher findTeacherByClassroomId(@Param("classroomId") int classroomId);


//=========================================Find Search===========================================================================

    @Query(value = "select * from users where dtype=:dtype and username=:username",nativeQuery = true)
    List<Users> findByUsername(String username,String dtype);

    @Query(value = "select * from users where dtype=:dtype and full_name like %:fullName%",nativeQuery = true)
    List<Users> findByFullName(String fullName,String dtype);
//==========================================================END===================================================================


//=========================================Find All And Find One Child Class Of Users==========================================
    @Query(value = "select * from users where dtype='Teacher'",nativeQuery = true)
    List<Teacher> findAllTeacher();

    @Query(value = "select * from users where dtype='Teacher' and id=:teacherid",nativeQuery = true)
    Teacher findTeacher(int teacherid);
    @Query(value = "select * from users where dtype='Student'",nativeQuery = true)
    List<Student> findAllStudent();

    @Query(value = "select * from users where dtype='Student' and id=:studentid",nativeQuery = true)
    Student findStudent(int studentid);

    @Query(value = "select * from users where dtype='Employee'",nativeQuery = true)
    List<Employee> findAllEmployee();

    @Query(value = "select * from users where dtype='Employee' and id=:employeeid",nativeQuery = true)
    Employee findEmployee(int employeeid);
//==========================================================END===================================================================


//    @Query(value = "select * from users where dtype=:dtype",nativeQuery = true)
//    List<Users> findAllWithDtype(String dtype);
//
//    @Query(value = "select * from users where dtype=:dtype and id=:userid",nativeQuery = true)
//    Users findUsersWithDtype(int userid, String dtype);
}
