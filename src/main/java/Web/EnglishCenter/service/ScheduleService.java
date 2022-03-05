package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    public Schedule save(Schedule schedule);
    public void delete(Schedule schedule);
    public List<Schedule> findAll();
    public Schedule findById(int id);
}
