package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.exam.UsersExamScores;
import Web.EnglishCenter.repo.UsersExamScoresRepo;
import Web.EnglishCenter.service.UsersExamScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersExamScoresServiceImpl implements UsersExamScoresService {

    @Autowired
    private UsersExamScoresRepo usersExamScoresRepo;

    @Override
    public UsersExamScores save(UsersExamScores usersExamScores) {
        return usersExamScoresRepo.save(usersExamScores);
    }

    @Override
    public void delete(UsersExamScores usersExamScores) {
        usersExamScoresRepo.delete(usersExamScores);
    }

    @Override
    public List<UsersExamScores> findAll() {
        return usersExamScoresRepo.findAll();
    }

    @Override
    public List<UsersExamScores> findByStudent(int studentId) {
        return usersExamScoresRepo.getScoreByStudent(studentId);
    }

    @Override
    public UsersExamScores getScoreOfStudentByExam(int studentId, int examId) {
        return usersExamScoresRepo.getScoreByStudentAndExam(studentId,examId);
    }
}
