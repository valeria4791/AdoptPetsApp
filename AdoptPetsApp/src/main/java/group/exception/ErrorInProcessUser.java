package group.exception;

// Exception for create/update/delete user data

public class ErrorInProcessUser extends Exception {
	public ErrorInProcessUser(String errorMessage) {
        super(errorMessage);
    }
}
