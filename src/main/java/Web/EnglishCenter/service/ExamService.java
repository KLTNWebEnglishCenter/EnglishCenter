package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.exam.Exam;

import java.util.List;

public interface ExamService {
    public Exam save(Exam exam);
    public void delete(Exam exam);
    public List<Exam> findAll();
    public Exam findById(int id);
}
