package Web.EnglishCenter.entity.user;

import Web.EnglishCenter.entity.Classroom;
import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.entity.Notification;
import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.course.UsersCourseRequest;
import Web.EnglishCenter.entity.exam.Exam;
import Web.EnglishCenter.entity.exam.UsersExamScores;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

//@MappedSuperclass
@Entity(name = "Users")
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NonNull
	@Column(unique = true)
	private String username;

	@NonNull
	private String password;

	@NonNull
	@Column(columnDefinition = "nvarchar(255)")
	private String fullName;
	private LocalDate dob;
	@Column(columnDefinition = "nvarchar(255)")
	private String gender;

	@NonNull
	private String email;
	private String phoneNumber;
	@Column(columnDefinition = "true")
	private boolean enable;


		@JsonBackReference(value = "users_authentication")
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "authentication_id", nullable = false)
	private Authentication authentication;

//    @OneToMany(mappedBy = "users")
//    private List<UsersCourseRequest> userRequestCourses;
//
//    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
//    private List<Classroom> classrooms;

//    @OneToMany(mappedBy = "creator",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Document> documents;
//
//    @OneToMany(mappedBy = "creator",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Notification> notifications;

	@JsonManagedReference(value = "users_posts")
//	@JsonManagedReference
	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Post> posts;

//    @OneToMany(mappedBy = "creator",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Exam> exams;

//    @JsonBackReference
//    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
//    private List<UsersExamScores> usersExamScores;

	public Users( String username,  String password, String fullName,  String email) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.enable=true;
	}
}
