package Web.EnglishCenter.service.impl;


import Web.EnglishCenter.entity.exam.Question;
import Web.EnglishCenter.repo.QuestionRepo;
import Web.EnglishCenter.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public Question save(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public void delete(Question question) {
        questionRepo.delete(question);
    }

    @Override
    public List<Question> findAll() {
        return questionRepo.findAll();
    }

    @Override
    public Question findById(int id) {
        return questionRepo.findById(id).get();
    }
}
