package Web.EnglishCenter.utils;

import Web.EnglishCenter.entity.schedule.Classroom;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.course.Category;
import Web.EnglishCenter.entity.course.Course;
import Web.EnglishCenter.entity.course.Level;
import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.Question;
import Web.EnglishCenter.entity.user.Student;
import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.entityDTO.*;

import java.util.ArrayList;
import java.util.List;

public class ConvertDTOHelper {

    /**
     * Convert list Notification to list NotificationDTO
     *
     * @param notifications
     * @return
     * @author VQKHANH
     */
    public List<NotificationDTO> convertListNotification(List<Notification> notifications) {
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        notifications.forEach(n -> {
            notificationDTOS.add(convertNotification(n));
        });
        return notificationDTOS;
    }

    /**
     * Convert Notification to NotificationDTO
     *
     * @param notification
     * @return
     * @author VQKHANH
     */
    public NotificationDTO convertNotification(Notification notification) {
        ClassroomDTO classroom = convertClassroom(notification.getClassroom());
//        classroom.setTeacher(null);
        NotificationDTO notificationDTO = new NotificationDTO(notification.getId(), notification.getTitle(), notification.getContent(), notification.getCreateDate(), notification.getModifiedDate(), trimTeacher(notification.getTeacher()), classroom);
        return notificationDTO;
    }

    /**
     * Convert list Classroom to list ClassroomDTO
     *
     * @param classrooms
     * @return
     * @author VQKHANH
     */
    public List<ClassroomDTO> convertListClassroom(List<Classroom> classrooms) {
        List<ClassroomDTO> classroomDTOS = new ArrayList<>();
        classrooms.forEach(c -> {
            classroomDTOS.add(convertClassroom(c));
        });
        return classroomDTOS;
    }

    /**
     * Convert Classroom to ClassroomDTO
     *
     * @param classroom
     * @return
     * @author VQKHANH
     */
    public ClassroomDTO convertClassroom(Classroom classroom) {
        ClassroomDTO classroomdto = new ClassroomDTO(classroom.getId(), classroom.getStartDate(), classroom.getEndDate(), classroom.getStatus(), classroom.getClassname(), classroom.getMaxMember(), classroom.getCreateDate(), classroom.getModifiedDate());

        classroomdto.setTeacher(trimTeacher(classroom.getTeacher()));
        return classroomdto;
    }

    /**
     * cut off the unnecessary data of the classroom list
     *
     * @param classrooms
     * @return
     * @author VQKHANH
     */
    public List<Classroom> trimListClassroom(List<Classroom> classrooms) {
        List<Classroom> trim_classrooms = new ArrayList<>();
        classrooms.forEach(c -> {
            trim_classrooms.add(trimClassroom(c));
        });
        return trim_classrooms;
    }


    public PostDTO convertPost(Post post) {
        PostDTO postDTO = new PostDTO(post.getId(), post.getTitle(), post.getContent(), trimUsers(post.getUsers()));
        return postDTO;
    }

    public List<PostDTO> convertListPost(List<Post> posts) {
        List<PostDTO> postDTOList = new ArrayList<>();
        posts.forEach(post -> {
            postDTOList.add(convertPost(post));
        });
        return postDTOList;
    }


    /**
     * cut off the unnecessary data, avoid infinite nested loop
     *
     * @param classroom
     * @return
     * @author VQKHANH
     */
    public Classroom trimClassroom(Classroom classroom) {
        Classroom trim_classroom = new Classroom(classroom.getId(), classroom.getStartDate(), classroom.getEndDate(), classroom.getStatus(), classroom.getClassname(), classroom.getMaxMember(), classroom.getCreateDate(), classroom.getModifiedDate());
        return trim_classroom;
    }

    /**
     * cut off the unnecessary data, avoid infinite nested loop
     *
     * @param teacher
     * @return
     * @author VQKHANH
     */
    public Teacher trimTeacher(Teacher teacher) {
        Teacher trim_teacher = new Teacher(teacher.getId(), teacher.getUsername(), teacher.getPassword(), teacher.getFullName(), teacher.getDob(), teacher.getGender(), teacher.getEmail(), teacher.getPhoneNumber(), teacher.isEnable());
        return trim_teacher;
    }

    /**
     * @param courses
     * @return
     * @author VQKHANH
     */
    public List<CourseDTO> convertListCourse(List<Course> courses) {
        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses
        ) {
            courseDTOS.add(convertCourse(course));
        }
        return courseDTOS;
    }

    /**
     * @param course
     * @return
     * @author VQKHANH
     */
    public CourseDTO convertCourse(Course course) {
        CourseDTO courseDTO = new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getPrice(), course.getCreateDate(), course.getModifiedDate(), course.getDiscount());
        courseDTO.setEnable(course.isEnable());
        courseDTO.setLevel(trimLevel(course.getLevel()));
        courseDTO.setCategory(trimCategory(course.getCategory()));
        return courseDTO;
    }

    /**
     * @param level
     * @return
     * @author VQKHANH
     */
    public Level trimLevel(Level level) {
        level.setCourses(null);
        return level;
    }

    /**
     * @param category
     * @return
     * @author VQKHANH
     */
    public Category trimCategory(Category category) {
        category.setCourses(null);
        return category;
    }

    /**
     * Convert list Classroom to list ClassroomDTO
     *
     * @param classrooms
     * @return
     * @author VQKHANH
     */
    public List<ClassroomDTO> convertListClassroomContainStudentAndTeacher(List<Classroom> classrooms) {
        List<ClassroomDTO> list = new ArrayList<>();
        classrooms.forEach(c -> {
            list.add(convertClassroomContainStudentAndTeacher(c));
        });
        return list;
    }

    /**
     * Convert Classroom to ClassroomDTO
     *
     * @param classroom
     * @return
     * @author VQKHANH
     */
    public ClassroomDTO convertClassroomContainStudentAndTeacher(Classroom classroom) {
        ClassroomDTO convert_classroom = new ClassroomDTO(classroom.getId(), classroom.getStartDate(), classroom.getEndDate(), classroom.getStatus(), classroom.getClassname(), classroom.getMaxMember(), classroom.getCreateDate(), classroom.getModifiedDate());
        convert_classroom.setTeacher(trimTeacher(classroom.getTeacher()));
        convert_classroom.setStudents(trimListStudent(classroom.getStudents()));
        convert_classroom.setCourse(null);
        return convert_classroom;
    }

    /**
     * @param students
     * @return
     * @author VQKHANH
     */
    public List<Student> trimListStudent(List<Student> students) {
        List<Student> list = new ArrayList<>();
        for (Student student : students
        ) {
            list.add(trimStudent(student));
        }
        return list;
    }

    /**
     * @param student
     * @return
     * @author VQKHANH
     */
    public Student trimStudent(Student student) {
        Student trim_student = new Student(student.getId(), student.getUsername(), student.getPassword(), student.getFullName(), student.getDob(), student.getGender(), student.getEmail(), student.getPhoneNumber(), student.isEnable());
        return trim_student;
    }

    public Users trimUsers(Users users) {
        Users trim_users = new Users(users.getId(), users.getUsername(), users.getPassword(), users.getFullName(), users.getDob(), users.getGender(), users.getEmail(), users.getPhoneNumber(), users.isEnable());
        return trim_users;
    }

    public List<QuestionDTO> convertListQuestion(List<Question> questions){
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.forEach(question -> {
            questionDTOS.add(trimQuestion(question));
        });
        return questionDTOS;
    }

    public List<ExamDTO> convertListExam(List<Exam> exams){
        List<ExamDTO> examDTOS = new ArrayList<>();
        exams.forEach(exam -> {
            examDTOS.add(trimExam(exam));
        });
        return examDTOS;
    }

    public QuestionDTO trimQuestion(Question question){
        QuestionDTO questionDTO = new QuestionDTO(question.getId(), question.getContent(),question.getCorrectAnswer(),question.getAnswerA(),question.getAnswerB(),question.getAnswerC(),question.getAnswerD());
        return questionDTO;
    }

    public ExamDTO trimExam(Exam exam){
        List<QuestionDTO> questionDTOS = convertListQuestion(exam.getQuestions());
        ExamDTO examDTO = new ExamDTO(exam.getId(), exam.getName(),exam.getDescription(),exam.getStatus(),trimTeacher(exam.getTeacher()),questionDTOS);
        return examDTO;
    }

    /**
     * for manage classroom of student
     * @author VQKHANH
     * @param classroom
     * @return
     */
    public ClassroomDTO convertClassroomContainNotificationsTeacherAndCourse(Classroom classroom) {
        ClassroomDTO convert_classroom = new ClassroomDTO(classroom.getId(), classroom.getStartDate(), classroom.getEndDate(), classroom.getStatus(), classroom.getClassname(), classroom.getMaxMember(), classroom.getCreateDate(), classroom.getModifiedDate());
        convert_classroom.setTeacher(trimTeacher(classroom.getTeacher()));
        convert_classroom.setNotifications(classroom.getNotifications());
        convert_classroom.setStudents(null);
        Course course=classroom.getCourse();
        course.setUserRequestCourses(null);
        convert_classroom.setCourse(course);
        return convert_classroom;
    }
}