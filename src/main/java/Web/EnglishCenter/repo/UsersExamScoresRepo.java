package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.exam.UsersExamScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersExamScoresRepo extends JpaRepository<UsersExamScores,Integer> {

    @Query(value = "select * from users_exam_scores where student_id =:studentId",nativeQuery = true)
    List<UsersExamScores> getScoreByStudent(int studentId);

    @Query(value = "select * from users_exam_scores where student_id =:studentId and exam_id=:examId",nativeQuery = true)
    UsersExamScores getScoreByStudentAndExam(int studentId,int examId);
}
