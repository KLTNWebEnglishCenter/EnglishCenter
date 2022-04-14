package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Employee;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class EmployeeRestAPI {

    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * get list of all employee
     * @author VQKHANH
     * @return
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok().body(usersService.findAllEmployee());
    }

    /**
     * get employee info by id
     * @author VQKHANH
     * @param employeeId
     * @return
     */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> findEmployee(@PathVariable(value = "employeeId") int employeeId){
//        log.info(employeeId+"");
        return ResponseEntity.ok().body(usersService.findEmployee(employeeId));
    }

    /**
     * @author VQKHANH
     * @param employee
     * @return data after saved to db
     */
    @PostMapping("/employee/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
//        log.info(employee.toString());
        if(employee.getId()==0||employee.getAuthentication()==null){
            Authentication authentication= authenticationService.findByRoleName(RoleType.EMPLOYEE);
            if (authentication == null) {
                log.info("Don't have role " + RoleType.EMPLOYEE + " in DB");
            }
            employee.setAuthentication(authentication);
        }
        return ResponseEntity.ok().body((Employee) usersService.save(employee));
    }
}
