package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.Level;
import Web.EnglishCenter.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class LevelRestAPI {

    @Autowired
    private LevelService levelService;

    @PostMapping("/level")
    public ResponseEntity<Level> save(@RequestBody Level level){
        return ResponseEntity.ok().body(levelService.save(level));
    }

    @GetMapping("/level")
    public ResponseEntity<List<Level>> getAll(){
        return ResponseEntity.ok().body(levelService.findAll());
    }
}
