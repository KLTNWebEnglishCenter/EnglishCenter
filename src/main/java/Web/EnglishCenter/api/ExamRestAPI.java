package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.Question;
import Web.EnglishCenter.entity.exam.UsersExamScores;
import Web.EnglishCenter.entity.exam.UsersExamScoresKey;
import Web.EnglishCenter.entity.user.Student;
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

    @PostMapping("/exam/save")
    public ResponseEntity<Exam> save(@RequestBody ExamDTO examDTO){
//        ExamDTO examDTO = convertDTOHelper.trimExam(examService.save(exam));

        List<QuestionDTO> questionDTOS = examDTO.getQuestionDTOS();
        List<Question> questions = new ArrayList<>();

        questionDTOS.forEach(questionDTO -> {
            Question question = new Question();
//            question.setId(questionDTO.getId());
            question.setAnswerA(questionDTO.getAnswerA());
            question.setAnswerB(questionDTO.getAnswerB());
            question.setAnswerC(questionDTO.getAnswerC());
            question.setAnswerD(questionDTO.getAnswerD());
            question.setContent(questionDTO.getContent());
            question.setCorrectAnswer(questionDTO.getCorrectAnswer());
            questions.add(question);
        });
        Exam exam = new Exam(examDTO.getName(),examDTO.getDescription(),examDTO.getStatus(),null,questions);
        exam.setTeacher(examDTO.getTeacher());
        return ResponseEntity.ok().body(examService.save(exam));
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
