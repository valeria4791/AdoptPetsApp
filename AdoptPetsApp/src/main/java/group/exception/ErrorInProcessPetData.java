package group.exception;

// Exception for create/update/delete pet data

public class ErrorInProcessPetData extends Exception {
	public ErrorInProcessPetData(String errorMessage) {
        super(errorMessage);
    }
}
