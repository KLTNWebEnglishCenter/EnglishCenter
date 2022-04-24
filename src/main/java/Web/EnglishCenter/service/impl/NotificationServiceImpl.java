package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.repo.NotificationRepo;
import Web.EnglishCenter.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

//==========================================================Custom Default Function===================================================================

    /**
     * @author VQKHANH
     * @param notification
     * @return
     */
    @Override
    public Notification save(Notification notification) {
        if(notification.getCreateDate()==null)notification.setCreateDate(LocalDate.now());
        if(notification.getModifiedDate()==null)notification.setModifiedDate(LocalDate.now());
        if(notification.getModifiedDate().isBefore(LocalDate.now()))notification.setModifiedDate(LocalDate.now());
        return notificationRepo.save(notification);
    }

    /**
     * @author VQKHANH
     * @param notification
     * @return
     */
    @Override
    public String delete(Notification notification,int teacherId) {
        Notification notification1;
        try {
            notification1=findById(notification.getId(),teacherId);
        }catch (Exception exception){
            return  exception.getMessage();
        }
        if(notification!=null){
            notificationRepo.delete(notification);
            return "Delete success!";
        }else return "Notification not found";

    }

    /**
     * @author VQKHANH
     * @return
     */
    @Override
    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    /**
     * @author VQKHANH
     * @param id
     * @return
     */
    @Override
    public Notification findById(int id,int teacherId) {
        return notificationRepo.findById(id,teacherId);
    }

//==========================================================END===================================================================


//==========================================================For teacher manage notification===================================================================

    /**
     * @author VQKHANH
     * @param teacherId
     * @return
     */
    @Override
    public List<Notification> findByTeacherId(int teacherId){
        return notificationRepo.findByTeacherId(teacherId);
    }
//==========================================================END===================================================================


//==========================================================For search feature===================================================================


    /**
     * @author VQKHANH
     * @param id
     * @param classroomIdOrClassname
     * @param teacherId
     * @return
     */
    @Override
    public List<Notification> searchNotification(int id, String classroomIdOrClassname, int teacherId) {
        List<Notification> notifications=new ArrayList<>();

        if (id != 0) {
            Notification notification = findById(id,teacherId);
            if (notification!=null) notifications.add(notification);
        }
        else {
            List<Notification> temp=findNotificationByClassroomIdOrClassname(classroomIdOrClassname,teacherId);
            if(temp.size()>0)notifications.addAll(temp);
        }
        return notifications;
    }

    /**
     * @author VQKHANH
     * @param classroomIdOrClassname
     * @param teacherId
     * @return
     */
    @Override
    public List<Notification> findNotificationByClassroomIdOrClassname(String classroomIdOrClassname, int teacherId){
        List<Notification> notifications=new ArrayList<>();
        List<Notification> temp;
        try {
            int classroomId=Integer.parseInt(classroomIdOrClassname);
             temp=findByClassroomId(classroomId,teacherId);
            if(temp.size()>0)notifications.addAll(temp);
        }catch (Exception e){
            temp=findByClassname(classroomIdOrClassname,teacherId);
            if(temp.size()>0)notifications.addAll(temp);
        }
        return  notifications;
    }

    /**
     * @author VQKHANH
     * @param classroomId
     * @param teacherId
     * @return
     */
    @Override
    public List<Notification> findByClassroomId(int classroomId,int teacherId) {
        return notificationRepo.findByClassroomId(classroomId,teacherId);
    }

    /**
     * @author VQKHANH
     * @param classname
     * @param teacherId
     * @return
     */
    @Override
    public List<Notification> findByClassname(String classname,int teacherId) {
        return notificationRepo.findByClassname(classname,teacherId);
    }
//==========================================================END===================================================================

}
