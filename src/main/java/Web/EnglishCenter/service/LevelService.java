package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.course.Level;

import java.util.List;

public interface LevelService {
    public Level save(Level level);
    public void delete(Level level);
    public List<Level> findAll();
    public Level findById(int id);
}
