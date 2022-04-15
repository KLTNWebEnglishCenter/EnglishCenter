package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.user.*;

import java.util.List;

public interface UsersService {

    public Users findByUsername(String username);
    public Teacher findTeacherByClassroomId(int classroomId);

//==========================================================Default===================================================================
    public Users save(Users users);
    public void delete(Users users);
    public List<Users> findAll();
    public Users findById(int id);
//==========================================================END===================================================================


//=========================================For Search Feature====================================================================================
    public List<Users> searchUser(String idOrUsername,String fullName,String dtype);
    public List<Users> findByIdOrUsername(String idOrUsername,String dtype);
    public List<Users> findByUsername(String username,String dtype);
    public List<Users> findByFullName(String fullName,String dtype);
//==========================================================END===================================================================


//=========================================Find All And Find One Child Class Of Users==========================================
    public List<Teacher> findAllTeacher();
    public Teacher findTeacher(int teacherid);
    public List<Student> findAllStudent();
    public Student findStudent(int studentid);
    public List<Employee> findAllEmployee();
    public Employee findEmployee(int employeeid);
//==========================================================END===================================================================
}
