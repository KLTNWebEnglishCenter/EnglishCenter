package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.Notification;

import java.util.List;

public interface NotificationService {
    public Notification save(Notification notification);
    public void delete(Notification notification);
    public List<Notification> findAll();
    public Notification findById(int id);
}
