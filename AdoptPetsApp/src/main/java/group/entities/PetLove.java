package group.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PetLove
 *
 */
@Entity
public class PetLove implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int petLoveId;

	// many to one relationship - To user table
	@OneToOne
	private User user;

	// one to many relationship - To pet table
	@OneToMany
	private List<Pet> pets = new ArrayList<Pet>();

	public PetLove() {
		super();
	}

	public int getPetLoveId() {
		return petLoveId;
	}

	public void setPetLoveId(int petLoveId) {
		this.petLoveId = petLoveId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
