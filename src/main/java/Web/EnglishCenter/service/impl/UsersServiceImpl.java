package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.user.*;
import Web.EnglishCenter.repo.UsersRepo;
import Web.EnglishCenter.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UsersServiceImpl implements UsersService,UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public Users save(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepo.save(users);
    }

    @Override
    public void delete(Users users) {
        usersRepo.delete(users);
    }

    @Override
    public List<Users> findAll() {
        return usersRepo.findAll();
    }

    @Override
    public Users findById(int id) {
        return usersRepo.findById(id).get();
    }

    //For JWT
    @Override
    public Users findByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return usersRepo.findAllTeacher();
    }

    @Override
    public Teacher findTeacher(int teacherid) {
        return usersRepo.findTeacher(teacherid);
    }

    @Override
    public Teacher findTeacherByClassroomId(int classroomId) {
        return usersRepo.findTeacherByClassroomId(classroomId);
    }

    public UserDetails loadUserById(int userId) {
        Users user= usersRepo.findById(userId).get();
        return new CustomUserDetails(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        Users users = usersRepo.findByUsername(username);
        if (users == null) {
            log.info("username: {}",username);
            log.info("User not found in the database!");
            throw new UsernameNotFoundException("User not found in the database!");
        } else {
            log.info("User found in the database!");
        }
        return new CustomUserDetails(users);
    }

    @Override
    public List<Student> findAllStudent() {
        return usersRepo.findAllStudent();
    }

    @Override
    public Student findStudent(int studentid) {
        return usersRepo.findStudent(studentid);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return usersRepo.findAllEmployee();
    }

    @Override
    public Employee findEmployee(int employeeid) {
        return usersRepo.findEmployee(employeeid);
    }

}
