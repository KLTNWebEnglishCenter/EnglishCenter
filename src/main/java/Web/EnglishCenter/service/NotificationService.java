package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.Notification;

import java.util.List;

public interface NotificationService {

//==========================================================Default===================================================================

    public Notification save(Notification notification);
    public String delete(Notification notification);
    public List<Notification> findAll();
    public Notification findById(int id);
//==========================================================END===================================================================

//==========================================================For teacher manage notification===================================================================

    public List<Notification> findByTeacherId(int teacherId);


//==========================================================END===================================================================
}
