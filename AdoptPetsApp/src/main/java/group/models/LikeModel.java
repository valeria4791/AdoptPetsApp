package group.models;

import java.util.List;

import group.dataAccess.PetDAO;
import group.dataAccess.PetLoveDAO;
import group.dataAccess.UserDAO;
import group.entities.Pet;
import group.entities.PetLove;
import group.entities.User;
import group.exception.ErrorInProcessPetData;
import group.exception.ErrorInProcessPetLove;
import group.exception.ErrorInProcessUser;

//Class that process logic for create/update/delete likes of user likes 

public class LikeModel {

	// Access to Pet table in DB
	private PetDAO petAccess = new PetDAO();

	// Access to Love Pet table in DB
	private PetLoveDAO lovePetAccess = new PetLoveDAO();

	// Access to User table in DB
	private UserDAO userAccess = new UserDAO();

	// Create new like of pet by user
	public PetLove createNewLike(int userId, int petId)
			throws ErrorInProcessUser, ErrorInProcessPetData, ErrorInProcessPetLove {
		try {

			// Get user by user id
			User user = this.userAccess.getUser(userId);

			// Get pet by pet id
			Pet pet = this.petAccess.getPet(petId);

			// Check if user already has likes
			PetLove petLike = user.getPetLove();

			// Check if data was found
			if (petLike != null) {

				// Add new pet to list of likes
				petLike.getPets().add(pet);

				// Now update all changes in DB
				this.lovePetAccess.update(petLike);
			} else {

				// Create new object for user likes
				petLike = new PetLove();

				// Set data of this new like
				petLike.setUser(user);
				petLike.getPets().add(pet);

				user.setPetLove(petLike);

				// Now update all changes in DB
				this.lovePetAccess.create(petLike);
				this.userAccess.update(user);
			}
			return petLike;
		} catch (ErrorInProcessPetLove ePetLove) {
			throw ePetLove;
		} catch (ErrorInProcessUser eUser) {
			throw eUser;
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		}
	}

	// Delete like of pet by user
	public void deleteLike(int userId, int petId)
			throws ErrorInProcessPetData, ErrorInProcessPetLove, ErrorInProcessUser {
		try {

			// Get user by user id
			User user = this.userAccess.getUser(userId);

			// Get pet by pet id
			Pet pet = this.petAccess.getPet(petId);

			// Get user likes by user id
			PetLove petLike = user.getPetLove();

			// Check if data was found
			if (petLike != null) {

				// Delete pet from list of likes
				petLike.getPets().remove(pet);

				// Now update all changes in DB
				this.lovePetAccess.update(petLike);
			}
		} catch (ErrorInProcessPetLove ePetLove) {
			throw ePetLove;
		} catch (ErrorInProcessPetData ePet) {
			throw ePet;
		} catch (ErrorInProcessUser eUser) {
			throw eUser;
		}
	}

	// Show all likes of user
	public List<Pet> getAllLikes(int userId) throws ErrorInProcessUser {
		try {

			// Get user by user id
			User user = this.userAccess.getUser(userId);

			// Get user likes by user id
			PetLove petLike = user.getPetLove();

			// Get all likes of pets
			return petLike.getPets();

		} catch (ErrorInProcessUser eUser) {
			throw eUser;
		}
	}
}
