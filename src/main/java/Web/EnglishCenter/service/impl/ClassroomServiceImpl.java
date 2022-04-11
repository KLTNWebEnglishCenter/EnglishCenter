package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.repo.ClassroomRepo;
import Web.EnglishCenter.repo.CourseRepo;
import Web.EnglishCenter.repo.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ClassroomServiceImpl implements Web.EnglishCenter.service.ClassroomService {

    @Autowired
    private ClassroomRepo classroomRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private CourseRepo courseRepo;

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
