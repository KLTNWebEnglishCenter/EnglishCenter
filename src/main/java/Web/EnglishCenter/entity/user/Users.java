package Web.EnglishCenter.entity.user;

import Web.EnglishCenter.entity.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

	@Column(columnDefinition = "varchar(255)", unique = true, nullable = false)
	private String username;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String password;

	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String fullName;
	private LocalDate dob;
	@Column(columnDefinition = "nvarchar(255)")
	private String gender;

	@Column(columnDefinition = "varchar(255)",unique = true, nullable = false)
	private String email;
	@Column(columnDefinition = "nvarchar(10)",unique = true, nullable = false)
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

	@JsonBackReference(value = "users_posts")
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

	public Users(int id, @NonNull String username, @NonNull String password, @NonNull String fullName, LocalDate dob, String gender, @NonNull String email, String phoneNumber, boolean enable) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enable = enable;
	}

	public Users(int id, @NonNull String username, @NonNull String password, @NonNull String fullName, LocalDate dob, String gender, @NonNull String email, String phoneNumber, boolean enable, Authentication authentication) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enable = enable;
		this.authentication = authentication;
	}

	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", fullName='" + fullName + '\'' +
				", dob=" + dob +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", enable=" + enable +
				'}';
	}
}
