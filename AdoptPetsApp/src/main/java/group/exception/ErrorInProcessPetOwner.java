package group.exception;

// Exception for create/update/delete pet owner data

public class ErrorInProcessPetOwner extends Exception {
	public ErrorInProcessPetOwner(String errorMessage) {
        super(errorMessage);
    }
}
