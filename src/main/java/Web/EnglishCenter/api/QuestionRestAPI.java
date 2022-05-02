package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.exam.Question;
import Web.EnglishCenter.entityDTO.QuestionDTO;
import Web.EnglishCenter.service.QuestionService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class QuestionRestAPI {

    @Autowired
    private QuestionService questionService;

    private ConvertDTOHelper convertDTOHelper = new ConvertDTOHelper();

    @GetMapping("/question")
    public ResponseEntity<List<QuestionDTO>> getAll(){
        List<Question> questions = questionService.findAll();
        return ResponseEntity.ok().body(convertDTOHelper.convertListQuestion(questions));
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionDTO> getById(@PathVariable int id){
        Question question = questionService.findById(id);
        return ResponseEntity.ok().body(convertDTOHelper.trimQuestion(question));
    }

    @PostMapping("/question")
    public ResponseEntity<QuestionDTO> save(@RequestBody Question question){
        Question question1 = questionService.save(question);
        return ResponseEntity.ok().body(convertDTOHelper.trimQuestion(question1));
    }

    @GetMapping("/question/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        Question question = questionService.findById(id);
        try {
            questionService.delete(question);
        }catch (Exception e){
            return ResponseEntity.ok().body("DeleteFail");
        }
        return ResponseEntity.ok().body("DeleteSuccess");
    }

    @GetMapping("/question/update")
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody Question question){
        Question question1 = questionService.update(question);
        return ResponseEntity.ok().body(convertDTOHelper.trimQuestion(question1));
    }
}
