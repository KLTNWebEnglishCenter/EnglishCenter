package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.course.Category;
import Web.EnglishCenter.entity.course.Level;
import Web.EnglishCenter.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class CategoryRestAPI {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> save(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.save(category));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok().body(categoryService.findAll());
    }
}
