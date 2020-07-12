package group.exception;

// Exception for create/update/delete pet love data

public class ErrorInProcessPetLove extends Exception {
	public ErrorInProcessPetLove(String errorMessage) {
        super(errorMessage);
    }
}
