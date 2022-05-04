package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.Question;
import Web.EnglishCenter.entity.exam.UsersExamScores;
import Web.EnglishCenter.entity.exam.UsersExamScoresKey;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.entityDTO.ExamDTO;
import Web.EnglishCenter.entityDTO.QuestionDTO;
import Web.EnglishCenter.service.ExamService;
import Web.EnglishCenter.service.QuestionService;
import Web.EnglishCenter.service.UsersExamScoresService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private ConvertDTOHelper convertDTOHelper = new ConvertDTOHelper();

    @GetMapping("/exam")
    public ResponseEntity<List<ExamDTO>> getAll(){
        List<ExamDTO> examDTOS = convertDTOHelper.convertListExam(examService.findAll());
        return ResponseEntity.ok().body(examDTOS);
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<ExamDTO> getById(@PathVariable int id){
        return ResponseEntity.ok().body(convertDTOHelper.trimExam(examService.findById(id)));
    }

    @GetMapping("/exam/test/{id}")
    public ResponseEntity<Exam> getById2(@PathVariable int id){
        return ResponseEntity.ok().body(examService.findById(id));
    }

    @PostMapping("/exam/save/{teacherId}")
    public ResponseEntity<Exam> save(@RequestBody ExamDTO examDTO, @PathVariable int teacherId){
        Exam exam = new Exam(examDTO.getName(),examDTO.getDescription(),examDTO.getStatus(),null,null);
        Teacher teacher = (Teacher) usersService.findById(teacherId);
        exam.setTeacher(teacher);
        return ResponseEntity.ok().body(examService.save(exam));
    }

    @GetMapping("/exam/questions/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuestionByExam(@PathVariable int id){
        Exam exam = examService.findById(id);
        List<QuestionDTO> questions = convertDTOHelper.convertListQuestion(exam.getQuestions());
        return ResponseEntity.ok().body(questions);
    }

    @GetMapping("/exam/{examId}/addQuestion/{questionId}")
    public ResponseEntity<ExamDTO> addQuestionToExam(@PathVariable int examId, @PathVariable int questionId){
        Exam exam = examService.findById(examId);
        List<Question> questions = exam.getQuestions();
        Question question = questionService.findById(questionId);
        if(!questions.contains(question)){
            questions.add(question);
        }
        exam.setQuestions(questions);
        return ResponseEntity.ok().body(convertDTOHelper.trimExam(examService.update(exam)));
    }

//    @GetMapping("/exam/save/test")
//    public ResponseEntity<Exam> testSave1(){
//        Question question=new Question("content","A","answerA","answerB");
//        Question question1=new Question("content1","B","answerA","answerB");
//        List<Question> questions=new ArrayList<>();
//        questions.add(question);
//        questions.add(question1);
//
//        Exam exam=new Exam( "name1", "description","status", null, questions);
//
//        return ResponseEntity.ok().body(examService.save(exam));
//    }
//
//    @GetMapping("/exam/save")
//    public void testSave(){
//        Exam exam=examService.findById(7);
//        Student student = usersService.findStudent(6);
//
//        UsersExamScoresKey usersExamScoresKey=new UsersExamScoresKey(6,7);
//        UsersExamScores usersExamScores=new UsersExamScores(usersExamScoresKey,student,exam,80);
//
//        usersExamScoresService.save(usersExamScores);
//    }
}
