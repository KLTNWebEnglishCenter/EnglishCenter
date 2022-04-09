package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.user.Employee;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;

import java.util.List;

public interface UsersService {
    public Users save(Users users);
    public void delete(Users users);
    public List<Users> findAll();
    public Users findById(int id);
    public Users findByUsername(String username);
    public List<Teacher> findAllTeacher();
    public Teacher findTeacher(int teacherid);
    public Teacher findTeacherByClassroomId(int classroomId);
    public List<Student> findAllStudent();
    public Student findStudent(int studentid);
    public List<Employee> findAllEmployee();
    public Employee findEmployee(int employeeid);
}
