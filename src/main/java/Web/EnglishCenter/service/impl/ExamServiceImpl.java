package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.Question;
import Web.EnglishCenter.repo.ExamRepo;
import Web.EnglishCenter.repo.QuestionRepo;
import Web.EnglishCenter.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public Exam save(Exam exam) {
        return examRepo.save(exam);
    }

    @Override
    public void delete(Exam exam) {
        examRepo.delete(exam);
    }

    @Override
    public List<Exam> findAll() {
        return examRepo.findAll();
    }

    @Override
    public Exam findById(int id) {
        return examRepo.findById(id).get();
    }

    @Override
    public Exam update(Exam exam) {
        return examRepo.saveAndFlush(exam);
    }
}
