package group.models;

import group.dataAccess.UserDAO;
import group.entities.User;
import group.exception.ErrorInProcessUser;

// Class that process logic for create/update/delete user data

public class UserModel {

	// User that sign in
	private User currentUser;

	// Access to User table in DB
	private UserDAO userAccess = new UserDAO();

	public User getCurrentUser() {
		return currentUser;
	}

	public void createNewUser(String username, String password, String firstName, String lastName, String email)
			throws ErrorInProcessUser {

		// Create new user
		User newUser = new User();

		// Set data of this user
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);

		// Save new User to DB
		try {
			this.userAccess.create(newUser);

			// Set to current user
			this.currentUser = newUser;
		} catch (ErrorInProcessUser e) {
			throw e;
		}
	}

	public void updateUser(String username, String password, String firstName, String lastName, String email)
			throws ErrorInProcessUser {

		// Check current user is set - otherwise can't be update
		if (this.currentUser != null) {

			// Save current user for go back if update didn't work
			User tempUser = this.currentUser;

			// Set updated data to current user
			this.currentUser.setUsername(username);
			this.currentUser.setPassword(password);
			this.currentUser.setFirstName(firstName);
			this.currentUser.setLastName(lastName);
			this.currentUser.setEmail(email);

			// Save updated User to DB
			try {
				this.userAccess.update(this.currentUser);
			} catch (ErrorInProcessUser e) {
				this.currentUser = tempUser;
				throw e;
			}
		}
	}

	public void deleteUser() throws ErrorInProcessUser {

		// Check current user is set - otherwise can't be delete
		if (this.currentUser != null) {

			// Save current user for go back if update didn't work
			User tempUser = this.currentUser;

			// Save updated User to DB
			try {
				this.userAccess.remove(this.currentUser.getUserId());
			} catch (ErrorInProcessUser e) {
				this.currentUser = tempUser;
				throw e;
			}
		}
	}
	
	// Find user for access application
	public User findUser(String username, String password) throws ErrorInProcessUser {

		// Find user in DB
		try {
			this.currentUser = this.userAccess.findUserByData(username, password);

			// Set found user
			return this.currentUser;
		} catch (ErrorInProcessUser e) {
			throw e;
		}
	}

	// Check if username already exist
	public boolean checkUsernameExists(String username) throws ErrorInProcessUser {

		// Find user in DB
		try {
			User foundUser = this.userAccess.checkUserName(username);

			if (foundUser != null)
				return true;
			else
				return false;
		} catch (ErrorInProcessUser e) {
			throw e;
		}
	}
}
