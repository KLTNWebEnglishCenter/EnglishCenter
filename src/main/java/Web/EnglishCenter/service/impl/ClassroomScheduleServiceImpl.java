package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.schedule.ClassroomSchedule;
import Web.EnglishCenter.repo.ClassroomScheduleRepo;
import Web.EnglishCenter.service.ClassroomScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ClassroomScheduleServiceImpl implements ClassroomScheduleService {

    @Autowired
    private ClassroomScheduleRepo classroomScheduleRepo;

    @Override
    public ClassroomSchedule save(ClassroomSchedule classroomSchedule) {
        return classroomScheduleRepo.save(classroomSchedule);
    }

    @Override
    public void delete(ClassroomSchedule classroomSchedule) {
        classroomScheduleRepo.delete(classroomSchedule);
    }

    @Override
    public List<ClassroomSchedule> findAll() {
        return classroomScheduleRepo.findAll();
    }

    @Override
    public ClassroomSchedule findById(int id) {
        return classroomScheduleRepo.findById(id).get();
    }


    /**
     * @author VQKHANH
     * @param teacherId
     * @param currentDate
     * @param dayOfWeek
     * @return
     */
    public List<String> getScheduleOfTeacher(int teacherId, LocalDate currentDate, String dayOfWeek){
        List<String> ls= classroomScheduleRepo.getScheduleOfTeacher( teacherId, currentDate, dayOfWeek);
//        for (String s :ls
//                ) {
//            log.info(s);
//        }
        return ls;
    };
}
