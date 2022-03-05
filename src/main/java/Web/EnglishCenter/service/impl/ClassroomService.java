package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.repo.ClassroomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClassroomService implements Web.EnglishCenter.service.ClassroomService {

    @Autowired
    private ClassroomRepo classroomRepo;

    @Override
    public Classroom save(Classroom classroom) {
        return classroomRepo.save(classroom);
    }

    @Override
    public void delete(Classroom classroom) {
        classroomRepo.delete(classroom);
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepo.findAll();
    }

    @Override
    public Classroom findById(int id) {
        return classroomRepo.findById(id).get();
    }
}
