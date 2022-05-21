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
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UsersServiceImpl implements UsersService,UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    /**
     * For JWT
     * @author VQKHANH
     * @param username
     * @return
     */
    @Override
    public Users findByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepo.findByEmail(email);
    }

    @Override
    public Users findByPhoneNumber(String phoneNumber) {
        return usersRepo.findByPhoneNumber(phoneNumber);
    }

    /**
     * For JWT
     * @author VQKHANH
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
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

    /**
     * @author NHLAM
     * @param classroomId
     * @return Teacher
     */
    @Override
    public Teacher findTeacherByClassroomId(int classroomId) {
        return usersRepo.findTeacherByClassroomId(classroomId);
    }



//==========================================================Default===================================================================

    /**
     * save user data with encrypt password
     * @author VQKHANH
     * @param users
     * @return user data after saved to db
     */
    @Override
    public Users save(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepo.save(users);
    }

    /**
     * default
     * @param users
     */
    @Override
    public void delete(Users users) {
        usersRepo.delete(users);
    }

    /**
     * default
     * @return
     */
    @Override
    public List<Users> findAll() {
        return usersRepo.findAll();
    }

    /**
     * default
     * @param id
     * @return
     */
    @Override
    public Users findById(int id) {
        return usersRepo.findById(id).get();
    }

    @Override
    public Users update(Users users) {
        return usersRepo.save(users);
    }

    @Override
    public boolean updatePassword(int id,String oldPass, String newPass) {
        Users users = usersRepo.getById(id);
        boolean rs = passwordEncoder.matches(oldPass,users.getPassword());
        return rs;
    }

    //==========================================================END===================================================================


//=========================================For Search Feature====================================================================================

    /**
     * find the user by id or username at first, if not found then find by full_name
     * @author VQKHANH
     * @param idOrUsername
     * @param fullName
     * @param dtype data type which you want to get from db
     * @return list object of parent class
     *
     */
    @Override
    public List<Users> searchUser(String idOrUsername,String fullName,String dtype){
        List<Users> users=new ArrayList<>();
        List<Users> list =findByIdOrUsername(idOrUsername,dtype);
        if (list!=null&&list.size()>0)users.addAll(list);
        else{
            list=findByFullName(fullName,dtype);
            if (list!=null&&list.size()>0)users.addAll(list);
        }
        return users;
    };

    /**
     * find user by id or username
     * @author VQKHANH
     * @param idOrUsername
     * @param dtype data type which you want to get from db
     * @return list object of parent class
     *
     */
    @Override
    public List<Users> findByIdOrUsername(String idOrUsername,String dtype){
        List<Users> users=new ArrayList<>();
        try {
            int id=Integer.parseInt(idOrUsername);
            Users user=findById(id);
            if(user!=null)  users.add(user);
        }catch (Exception e){
            Users user=findByUsername(idOrUsername,dtype);
            if (user!=null)users.add(user);
        }
        return users;
    };

    /**
     * find user by username
     * @author VQKHANH
     * @param username
     * @param dtype data type which you want to get from db
     * @return list object of parent class
     *
     */
    @Override
    public Users findByUsername(String username,String dtype){
        return usersRepo.findByUsername(username,dtype);
    };

    /**
     * find user by full_name
     * @author VQKHANH
     * @param fullName
     * @param dtype data type which you want to get from db
     * @return list object of parent class
     *
     */
    @Override
    public List<Users> findByFullName(String fullName,String dtype){
        return usersRepo.findByFullName(fullName,dtype);
    };

//==========================================================END===================================================================


//=========================================Find All And Find One Child Class Of Users==========================================

    /**
     * @author VQKHANH
     * @return
     */
    @Override
    public List<Teacher> findAllTeacher() {
        return usersRepo.findAllTeacher();
    }

    /**
     * @author VQKHANH
     * @param teacherId
     * @return
     */
    @Override
    public Teacher findTeacher(int teacherId) {
        return usersRepo.findTeacher(teacherId);
    }

    /**
     * @author VQKHANH
     * @return
     */
    @Override
    public List<Student> findAllStudent() {
        return usersRepo.findAllStudent();
    }

    /**
     * @author VQKHANH
     * @param studentId
     * @return
     */
    @Override
    public Student findStudent(int studentId) {
        return usersRepo.findStudent(studentId);
    }

    /**
     * @author VQKHANH
     * @return
     */
    @Override
    public List<Employee> findAllEmployee() {
        return usersRepo.findAllEmployee();
    }

    /**
     * @author VQKHANH
     * @param employeeId
     * @return
     */
    @Override
    public Employee findEmployee(int employeeId) {
        return usersRepo.findEmployee(employeeId);
    }

    /**
     * @author NHLam
     * @param userId
     * @return list Authentication
     */


    //==========================================================END===================================================================

//    //For JWT
//    public UserDetails loadUserById(int userId) {
//        Users user= usersRepo.findById(userId).get();
//        return new CustomUserDetails(user);
//    }

}
