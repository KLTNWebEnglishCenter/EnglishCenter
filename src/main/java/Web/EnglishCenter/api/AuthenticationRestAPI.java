package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthenticationRestAPI {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/auths")
    public ResponseEntity<List<Authentication>> getAll(){
        return ResponseEntity.ok().body(authenticationService.findAll());
    }
    @PostMapping("/auth/save")
    public ResponseEntity<Authentication> saveOne(@RequestBody Authentication authentication){
        return ResponseEntity.ok().body(authenticationService.save(authentication));
    }
}
