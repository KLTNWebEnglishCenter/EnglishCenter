package Web.EnglishCenter.service;

import Web.EnglishCenter.entity.exam.Question;

import java.util.List;

public interface QuestionService {
    public Question save(Question question);
    public void delete(Question question);
    public List<Question> findAll();
    public Question findById(int id);
    public Question update(Question question);
}
