package group.models;

//Class that process logic for create/update/delete pet data

import group.entities.Pet;
import group.entities.PetOwner;
import group.entities.User;
import group.exception.ErrorInProcessPetData;
import group.exception.ErrorInProcessPetOwner;
import group.exception.ErrorInProcessUser;
import group.utilities.Category;
import group.utilities.Gender;
import group.utilities.PetSize;

import java.util.ArrayList;
import java.util.List;

import group.dataAccess.PetDAO;
import group.dataAccess.PetOwnerDAO;
import group.dataAccess.UserDAO;

public class PetModel {

	// Access to pet owner table in DB
	private PetOwnerDAO petOwnerAccess = new PetOwnerDAO();

	// Access to Pet table in DB
	private PetDAO petAccess = new PetDAO();

	// Access to User table in DB
	private UserDAO userAccess = new UserDAO();

	// Create new pet with new owner
	public Pet createNewPet(int userId, Category petCategory, String petName, int petAge, PetSize petSize,
			Gender petGender, String shortDescription, String detailDescription, byte[] petPhoto, String firstName,
			String lastName, long ownerPhoneNumber, String ownerCity, String ownerStreet, int ownerHouseNumber)
			throws ErrorInProcessPetOwner, ErrorInProcessUser, ErrorInProcessPetData {

		// Get user by user id
		User user = this.userAccess.getUser(userId);

		// Start with created pet owner
		PetOwner newPetOwner = new PetOwner();

		// Set data of this pet owner
		newPetOwner.setUser(user);
		newPetOwner.setFirstName(firstName);
		newPetOwner.setLastName(lastName);
		newPetOwner.setPhoneNumber(ownerPhoneNumber);
		newPetOwner.setCity(ownerCity);
		newPetOwner.setStreet(ownerStreet);
		newPetOwner.setHouseNumber(ownerHouseNumber);

		// Set created pet owner to user list
		user.getOwners().add(newPetOwner);

		// Now we can create a new pet
		Pet newPet = new Pet();

		// Set data of this pet
		newPet.setPetOwner(newPetOwner);
		newPet.setCategory(petCategory);
		newPet.setPetName(petName);
		newPet.setPetAge(petAge);
		newPet.setPetSize(petSize);
		newPet.setGender(petGender);
		newPet.setShortDescription(shortDescription);
		newPet.setDetailDescription(detailDescription);
		newPet.setPetPhoto(petPhoto);

		// Now update all changes in DB
		try {
			this.petOwnerAccess.create(newPetOwner);
			this.userAccess.update(user);
			this.petAccess.create(newPet);

			return newPet;
		} catch (ErrorInProcessPetOwner ePetOwner) {
			throw ePetOwner;
		} catch (ErrorInProcessUser eUser) {
			throw eUser;
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		}
	}

	// Update exist pet and owner
	public Pet updatePet(int petId, Category petCategory, String petName, int petAge, PetSize petSize, Gender petGender,
			String shortDescription, String detailDescription, byte[] petPhoto, String firstName, String lastName,
			long ownerPhoneNumber, String ownerCity, String ownerStreet, int ownerHouseNumber)
			throws ErrorInProcessPetOwner, ErrorInProcessPetData {

		// Get Pet using id
		Pet currentPet = this.petAccess.getPet(petId);

		// Start with update pet owner
		PetOwner petOwner = currentPet.getPetOwner();

		// Set data of this pet owner
		petOwner.setFirstName(firstName);
		petOwner.setLastName(lastName);
		petOwner.setPhoneNumber(ownerPhoneNumber);
		petOwner.setCity(ownerCity);
		petOwner.setStreet(ownerStreet);
		petOwner.setHouseNumber(ownerHouseNumber);

		// Update data of this pet
		currentPet.setCategory(petCategory);
		currentPet.setPetName(petName);
		currentPet.setPetAge(petAge);
		currentPet.setPetSize(petSize);
		currentPet.setGender(petGender);
		currentPet.setShortDescription(shortDescription);
		currentPet.setDetailDescription(detailDescription);
		currentPet.setPetPhoto(petPhoto);

		// Now update all changes in DB
		try {
			this.petOwnerAccess.update(petOwner);
			this.petAccess.create(currentPet);

			return currentPet;
		} catch (ErrorInProcessPetOwner ePetOwner) {
			throw ePetOwner;
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		}
	}

	// Delete exist pet and his owner
	public void deletePet(int petId) throws ErrorInProcessPetOwner, ErrorInProcessUser, ErrorInProcessPetData {

		// Get Pet using id
		Pet currentPet = this.petAccess.getPet(petId);

		// Get pet owner
		PetOwner petOwner = currentPet.getPetOwner();

		// Get user that create this pet with owner
		User user = petOwner.getUser();

		// Delete pet owner from user
		user.getOwners().remove(petOwner);

		// Now update all changes in DB
		try {
			this.userAccess.update(user);
			this.petOwnerAccess.remove(petOwner.getPetOwnerId());
			this.petAccess.remove(petId);
		} catch (ErrorInProcessPetOwner ePetOwner) {
			throw ePetOwner;
		} catch (ErrorInProcessUser eUser) {
			throw eUser;
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		}
	}

	// Get all data of pet by pet Id
	public Pet getPetById(int petId) throws ErrorInProcessPetData {
		try {
			return this.petAccess.getPet(petId);
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		}
	}

	// Get all pets of user
	public List<Pet> getAllPetsByUser(int userId) throws ErrorInProcessPetOwner, ErrorInProcessUser {

		// Create list of pets of returning
		List<Pet> pets = new ArrayList<Pet>();

		try {

			// Get all pet owners that user create
			List<PetOwner> petOwners = this.userAccess.getAllPetOwners(userId);

			// Get from each pet owner a pet that connected to him
			for (PetOwner currPetOwner : petOwners) {

				// Get pet of owner
				Pet pet = this.petOwnerAccess.getPetByOwnerId(currPetOwner.getPetOwnerId());

				// Add pet
				pets.add(pet);
			}

			return pets;
		} catch (ErrorInProcessPetOwner ePetOwner) {
			throw ePetOwner;
		} catch (ErrorInProcessUser ePet) {
			throw ePet;
		}
	}

	// Get all pets in DB
	public List<Pet> getAllPets() throws ErrorInProcessPetData {
		try {
			return this.petAccess.getAllPets();
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		}
	}

	// Get pets by criterias
	public List<Pet> getPetsByCriteria(Category petCategory, int petAge, PetSize petSize, Gender petGender)
			throws ErrorInProcessPetData {
		try {
			return this.petAccess.getPetsByCriteria(petCategory, petAge, petSize, petGender);
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		}
	}
}
