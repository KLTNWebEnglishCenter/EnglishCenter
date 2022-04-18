package Web.EnglishCenter.utils;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.entityDTO.ClassroomDTO;
import Web.EnglishCenter.entityDTO.NotificationDTO;
import Web.EnglishCenter.entityDTO.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class ConvertDTOHelper {

    /**
     * Convert list Notification to list NotificationDTO
     * @author VQKHANH
     * @param notifications
     * @return
     */
    public List<NotificationDTO> convertListNotification(List<Notification> notifications){
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        notifications.forEach(n->{
            notificationDTOS.add(convertNotification(n));
        });
        return notificationDTOS;
    }

    /**
     * Convert Notification to NotificationDTO
     * @author VQKHANH
     * @param notification
     * @return
     */
    public NotificationDTO convertNotification(Notification notification){
        ClassroomDTO classroom=convertClassroom(notification.getClassroom());
        NotificationDTO notificationDTO=new NotificationDTO(notification.getId(),notification.getTitle(),notification.getContent(),notification.getCreateDate(),notification.getModifiedDate(),trimTeacher(notification.getTeacher()) ,classroom);
        return notificationDTO;
    }

    /**
     * Convert list Classroom to list ClassroomDTO
     * @author VQKHANH
     * @param classrooms
     * @return
     */
    public List<ClassroomDTO> convertListClassroom(List<Classroom> classrooms){
        List<ClassroomDTO> classroomDTOS = new ArrayList<>();
        classrooms.forEach(c->{
            classroomDTOS.add(convertClassroom(c));
        });
        return classroomDTOS;
    }

    /**
     * Convert Classroom to ClassroomDTO
     * @author VQKHANH
     * @param classroom
     * @return
     */
    public ClassroomDTO convertClassroom(Classroom classroom){
        ClassroomDTO classroomdto = new ClassroomDTO(classroom.getId(),classroom.getStartDate(),classroom.getEndDate(),classroom.getStatus(),classroom.getClassname(),classroom.getMaxMember(),classroom.getCreateDate(),classroom.getModifiedDate());
        return classroomdto;
    }

    /**
     * cut off the unnecessary data of the classroom list
     * @author VQKHANH
     * @param classrooms
     * @return
     */
    public List<Classroom> trimListClassroom(List<Classroom> classrooms){
        List<Classroom> trim_classrooms = new ArrayList<>();
        classrooms.forEach(c->{
            trim_classrooms.add(trimClassroom(c));
        });
        return trim_classrooms;
    }


    public PostDTO convertPost(Post post){
        PostDTO postDTO = new PostDTO(post.getId(),post.getTitle(),post.getContent(),trimUsers(post.getUsers()));
        return postDTO;
    }

    public List<PostDTO> convertListPost(List<Post> posts){
        List<PostDTO> postDTOList = new ArrayList<>();
        posts.forEach(post -> {
            postDTOList.add(convertPost(post));
        });
        return postDTOList;
    }


    /**
     * cut off the unnecessary data, avoid infinite nested loop
     * @author VQKHANH
     * @param classroom
     * @return
     */
    public Classroom trimClassroom(Classroom classroom) {
        Classroom trim_classroom = new Classroom(classroom.getId(), classroom.getStartDate(), classroom.getEndDate(), classroom.getStatus(), classroom.getClassname(), classroom.getMaxMember(), classroom.getCreateDate(), classroom.getModifiedDate());
        return trim_classroom;
    }

    /**
     * cut off the unnecessary data, avoid infinite nested loop
     * @author VQKHANH
     * @param teacher
     * @return
     */
    public Teacher trimTeacher(Teacher teacher){
        Teacher trim_teacher=new Teacher(teacher.getId(),teacher.getUsername(),teacher.getPassword(),teacher.getFullName(),teacher.getDob(),teacher.getGender(),teacher.getEmail(),teacher.getPhoneNumber(), teacher.isEnable());
        return  trim_teacher;
    }

    public Users trimUsers(Users users){
        Users trim_users = new Users(users.getId(),users.getUsername(),users.getPassword(),users.getFullName(),users.getDob(),users.getGender(),users.getEmail(),users.getPhoneNumber(),users.isEnable());
        return trim_users;
    }
}
