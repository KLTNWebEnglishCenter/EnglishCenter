package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Employee;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersService;
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

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok().body(usersService.findAllEmployee());
    }

    //    @ResponseBody
    @GetMapping("/employee/{employeeid}")
    public ResponseEntity<Employee> findEmployee(@PathVariable(value = "employeeid") int employeeid){
        log.info(employeeid+"");
        return ResponseEntity.ok().body(usersService.findEmployee(employeeid));
    }

    @PostMapping("/employee/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        log.info(employee.toString());
        if(employee.getId()==0||employee.getAuthentication()==null){
            Authentication authentication= authenticationService.findById(3);
            employee.setAuthentication(authentication);
        }
        return ResponseEntity.ok().body((Employee) usersService.save(employee));
    }
}
