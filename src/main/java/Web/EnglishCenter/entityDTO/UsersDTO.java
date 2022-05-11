package Web.EnglishCenter.entityDTO;

import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.user.Authentication;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class UsersDTO implements Serializable {


	private int id;
	private String username;
	private String password;

	private String fullName;
	private LocalDate dob;
	private String gender;

	private String email;
	private String phoneNumber;
	private boolean enable;


	private String role;

	public UsersDTO(String username, String password, String fullName, String email) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.enable=true;
	}

	public UsersDTO(int id, @NonNull String username, @NonNull String password, @NonNull String fullName, LocalDate dob, String gender, @NonNull String email, String phoneNumber, boolean enable) {
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



}
