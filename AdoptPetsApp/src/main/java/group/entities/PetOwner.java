package group.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Entity implementation class for Entity: PetOwner
 *
 */
@Entity
public class PetOwner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int petOwnerId;

	// many to one relationship - To user table
	@ManyToOne
	private User user;

	@Size(max = 65)
	@Column(name = "first_name")
	private String firstName;

	@Size(max = 65)
	@Column(name = "last_Name")
	private String lastName;

	@Column(name = "phone_number")
	@Size(max = 15)
	private long phoneNumber;

	@Column(name = "city")
	@Size(max = 60)
	private String city;

	@Column(name = "street")
	@Size(max = 100)
	private String street;

	@Column(name = "house_number")
	private int houseNumber;

	// one to one relationship - To pet table
	@OneToOne(mappedBy = "petOwner")
	private Pet pet;

	public PetOwner() {
		super();
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

	public int getPetOwnerId() {
		return this.petOwnerId;
	}

	public void setPetOwnerId(int petOwnerId) {
		this.petOwnerId = petOwnerId;
	}

	public long getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
}
