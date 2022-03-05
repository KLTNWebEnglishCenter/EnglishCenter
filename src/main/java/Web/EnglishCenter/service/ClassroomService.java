package Web.EnglishCenter.service;

import Web.EnglishCenter.entity.Classroom;

import java.util.List;

public interface ClassroomService {
    public Classroom save(Classroom classroom);
    public void delete(Classroom classroom);
    public List<Classroom> findAll();
    public Classroom findById(int id);
}
