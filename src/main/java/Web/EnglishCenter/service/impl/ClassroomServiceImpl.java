package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.repo.ClassroomRepo;
import Web.EnglishCenter.repo.CourseRepo;
import Web.EnglishCenter.repo.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

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

    /**
     * to ensure that all classroom attribute are correct
     * @author VQKHANH
     * @param classroom
     * @return
     */
    @Override
    public Classroom save(Classroom classroom) {
        if(classroom.getStudents().size()>classroom.getMaxMember())return null;
        if(classroom.getCreateDate()==null)classroom.setCreateDate(LocalDate.now());
        if(classroom.getModifiedDate()==null)classroom.setModifiedDate(LocalDate.now());
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

    /**
     * @author VQKHANH
     * @param courseId
     * @return
     */
    @Override
    public List<Classroom> findByCourseID(int courseId) {
        return classroomRepo.findByCourseID(courseId);
    }

    /**
     * @author VQKHANH
     * @param classroomId
     * @return
     */
    @Override
    public Integer countStudent(int classroomId){
        return classroomRepo.countStudent(classroomId);
    }
}
