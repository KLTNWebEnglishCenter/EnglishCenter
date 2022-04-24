package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.Notification;

import java.util.List;

public interface NotificationService {

//==========================================================Custom Default function===================================================================

    public Notification save(Notification notification);

    public String delete(Notification notification,int teacherId);

    public List<Notification> findAll();

    public Notification findById(int id,int teacherId);
//==========================================================END===================================================================

//==========================================================For teacher manage notification===================================================================

    public List<Notification> findByTeacherId(int teacherId);

//==========================================================END===================================================================


//==========================================================For search feature===================================================================

    public List<Notification> searchNotification(int id, String classroomIdOrClassname, int teacherId);

    public List<Notification> findNotificationByClassroomIdOrClassname(String classroomIdOrClassname, int teacherId);

    public List<Notification> findByClassroomId(int classroomId,int teacherId);

    public List<Notification> findByClassname(String classname,int teacherId);

//==========================================================END===================================================================

}
