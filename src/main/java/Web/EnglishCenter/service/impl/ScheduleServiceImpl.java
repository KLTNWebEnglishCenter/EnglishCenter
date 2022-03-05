package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Schedule;
import Web.EnglishCenter.repo.ScheduleRepo;
import Web.EnglishCenter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        scheduleRepo.delete(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepo.findAll();
    }

    @Override
    public Schedule findById(int id) {
        return scheduleRepo.findById(id).get();
    }
}
