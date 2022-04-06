package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.course.Level;
import Web.EnglishCenter.repo.LevelRepo;
import Web.EnglishCenter.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepo levelRepo;

    @Override
    public Level save(Level level) {
        return levelRepo.save(level);
    }

    @Override
    public void delete(Level level) {
        levelRepo.delete(level);
    }

    @Override
    public List<Level> findAll() {
        return levelRepo.findAll();
    }

    @Override
    public Level findById(int id) {
        return levelRepo.findById(id).get();
    }

    @Override
    public Level findLevelByCourseId(int courseId) {
        return levelRepo.findLevelByCourseId(courseId);
    }
}
