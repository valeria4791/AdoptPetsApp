package group.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Entity implementation class for Entity: user
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "FindUser", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
		@NamedQuery(name = "OwnersByUser", query = "SELECT o FROM User u JOIN u.owners o WHERE u.userId = :userId"),
		@NamedQuery(name = "CheckUsername", query = "SELECT u FROM User u WHERE u.username = :username"), })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;

	@NotNull
	@Size(max = 100)
	@Column(name = "user_name")
	private String username;

	@NotNull
	@Size(max = 128)
	@Column(name = "password")
	private String password;

	@Size(max = 65)
	@Column(name = "first_name")
	private String firstName;

	@Size(max = 65)
	@Column(name = "last_Name")
	private String lastName;

	@Email
	@Size(max = 100)
	@Column(unique = true)
	private String email;

	// one to many relationship - To pet owner table
	@OneToMany(mappedBy = "user")
	private List<PetOwner> owners = new ArrayList<PetOwner>();

	// one to many relationship - To pet love table
	@OneToOne(mappedBy = "user", optional = true)
	private PetLove petLove;

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PetOwner> getOwners() {
		return owners;
	}

	public void setOwners(List<PetOwner> owners) {
		this.owners = owners;
	}

	public PetLove getPetLove() {
		return petLove;
	}

	public void setPetLove(PetLove petLove) {
		this.petLove = petLove;
	}
}
