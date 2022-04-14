package Web.EnglishCenter.utils;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
import Web.EnglishCenter.entityDTO.NotificationDTO;

import java.util.ArrayList;
import java.util.List;

public class ConvertDTOHelper {

    public List<NotificationDTO> convertListNotification(List<Notification> notifications){
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        notifications.forEach(n->{
            notificationDTOS.add(convertNotification(n));
        });
        return notificationDTOS;
    }


    public NotificationDTO convertNotification(Notification notification){
        ClassroomDTO classroom=convertClassroom(notification.getClassroom());
        NotificationDTO notificationDTO=new NotificationDTO(notification.getId(),notification.getTitle(),notification.getContent(),notification.getCreateDate(),notification.getModifiedDate(),trimTeacher(notification.getTeacher()) ,classroom);
        return notificationDTO;
    }

    public List<ClassroomDTO> convertListClassroom(List<Classroom> classrooms){
        List<ClassroomDTO> classroomDTOS = new ArrayList<>();
        classrooms.forEach(c->{
            classroomDTOS.add(convertClassroom(c));
        });
        return classroomDTOS;
    }

    public ClassroomDTO convertClassroom(Classroom classroom){
        ClassroomDTO classroomdto = new ClassroomDTO(classroom.getId(),classroom.getStartDate(),classroom.getEndDate(),classroom.getStatus(),classroom.getClassname(),classroom.getMaxMember(),classroom.getCreateDate(),classroom.getModifiedDate());
        return classroomdto;
    }


    public List<Classroom> trimListClassroom(List<Classroom> classrooms){
        List<Classroom> trim_classrooms = new ArrayList<>();
        classrooms.forEach(c->{
            trim_classrooms.add(trimClassroom(c));
        });
        return trim_classrooms;
    }

    public Classroom trimClassroom(Classroom classroom) {
        Classroom trim_classroom = new Classroom(classroom.getId(), classroom.getStartDate(), classroom.getEndDate(), classroom.getStatus(), classroom.getClassname(), classroom.getMaxMember(), classroom.getCreateDate(), classroom.getModifiedDate());
        return trim_classroom;
    }

    public Teacher trimTeacher(Teacher teacher){
        Teacher trim_teacher=new Teacher(teacher.getId(),teacher.getUsername(),teacher.getPassword(),teacher.getFullName(),teacher.getDob(),teacher.getGender(),teacher.getEmail(),teacher.getPhoneNumber(), teacher.isEnable());
        return  trim_teacher;
    }
}
