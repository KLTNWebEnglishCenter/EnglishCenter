package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.repo.NotificationRepo;
import Web.EnglishCenter.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public Notification save(Notification notification) {
        return notificationRepo.save(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationRepo.delete(notification);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    @Override
    public Notification findById(int id) {
        return notificationRepo.findById(id).get();
    }
}
