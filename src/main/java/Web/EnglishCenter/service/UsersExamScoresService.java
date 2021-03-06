package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.exam.UsersExamScores;

import java.util.List;

public interface UsersExamScoresService {
    public UsersExamScores save(UsersExamScores usersExamScores);
    public void delete(UsersExamScores usersExamScores);
    public List<UsersExamScores> findAll();
    public List<UsersExamScores> findByStudent(int studentId);
    public UsersExamScores getScoreOfStudentByExam(int studentId,int examId);
}
