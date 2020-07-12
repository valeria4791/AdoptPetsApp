package group.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.*;

import group.utilities.Category;
import group.utilities.Gender;
import group.utilities.PetSize;

/**
 * Entity implementation class for Entity: Pet
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "AllPets", query = "SELECT p FROM Pet p"),
		@NamedQuery(name = "PetsByCriteria", query = "SELECT p FROM Pet p WHERE p.category = :category") })
public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int petId;

	// one to one relationship - To pet owner table
	@OneToOne
	private PetOwner petOwner;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 15)
	private Category category;
	
	@Size(max = 65)
	@Column(name = "pet_name")
	private String petName;
	
	@Column(name = "pet_age")
	private int petAge;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "pet_size", length = 15)
	private PetSize petSize;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 10)
	private Gender gender;
	
	@Column(name = "short_desc")
	@Size(max = 150)
	private String shortDescription;
	
	@Column(name = "detail_desc")
	@Size(max = 300)
	private String detailDescription;
	
	@Lob
	@Column(name = "image")
	private byte[] petPhoto;

	public Pet() {
		super();
	}

	public int getPetId() {
		return this.petId;
	}

	public PetOwner getPetOwner() {
		return this.petOwner;
	}

	public void setPetOwner(PetOwner petOwner) {
		this.petOwner = petOwner;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPetName() {
		return this.petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getPetAge() {
		return this.petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public PetSize getPetSize() {
		return this.petSize;
	}

	public void setPetSize(PetSize petSize) {
		this.petSize = petSize;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailDescription() {
		return this.detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public byte[] getPetPhoto() {
		return petPhoto;
	}

	public void setPetPhoto(byte[] petPhoto) {
		this.petPhoto = petPhoto;
	}
}
