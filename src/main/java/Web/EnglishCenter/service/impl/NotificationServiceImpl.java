package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.repo.NotificationRepo;
import Web.EnglishCenter.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

//==========================================================Default===================================================================

    @Override
    public Notification save(Notification notification) {
        if(notification.getCreateDate()==null)notification.setCreateDate(LocalDate.now());
        if(notification.getModifiedDate()==null)notification.setModifiedDate(LocalDate.now());
        return notificationRepo.save(notification);
    }

    @Override
    public String delete(Notification notification) {
        try {
            findById(notification.getId());
        }catch (Exception exception){
            return  exception.getMessage();
        }
        notificationRepo.delete(notification);
        return "Delete success!";
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    @Override
    public Notification findById(int id) {
        return notificationRepo.findById(id).get();
    }
//==========================================================END===================================================================


//==========================================================For teacher manage notification===================================================================

    @Override
    public List<Notification> findByTeacherId(int teacherId){
        return notificationRepo.findByTeacherId(teacherId);
    }
//==========================================================END===================================================================

}
