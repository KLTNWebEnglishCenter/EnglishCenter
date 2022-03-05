package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.Question;
import Web.EnglishCenter.entity.exam.UsersExamScores;
import Web.EnglishCenter.entity.exam.UsersExamScoresKey;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.service.ExamService;
import Web.EnglishCenter.service.QuestionService;
import Web.EnglishCenter.service.UsersExamScoresService;
import Web.EnglishCenter.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ExamRestAPI {

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersExamScoresService usersExamScoresService;

    @GetMapping("/exam")
    public ResponseEntity<List<Exam>> getAll(){
        return ResponseEntity.ok().body(examService.findAll());
    }
    @GetMapping("/exam/{id}")
    public ResponseEntity<Exam> getOne(@PathVariable int id){
        return ResponseEntity.ok().body(examService.findById(id));
    }

//    @GetMapping("/exam/save")
//    public ResponseEntity<Exam> testSave(){
//        Question question=new Question("content","A","answerA","answerB");
//        Question question1=new Question("content1","B","answerA","answerB");
//        List<Question> questions=new ArrayList<>();
//        questions.add(question);
//        questions.add(question1);
//
//        Exam exam=new Exam( "name", "description","status", null, questions);
//
//        return ResponseEntity.ok().body(examService.save(exam));
//    }
//    @GetMapping("/exam/save")
//    public void testSave(){
//        Exam exam=examService.findById(7);
//        Users users=usersService.findById(6);
//
//        UsersExamScoresKey usersExamScoresKey=new UsersExamScoresKey(6,7);
//        UsersExamScores usersExamScores=new UsersExamScores(usersExamScoresKey,users,exam,80);
//
//        usersExamScoresService.save(usersExamScores);
//
//    }
}
