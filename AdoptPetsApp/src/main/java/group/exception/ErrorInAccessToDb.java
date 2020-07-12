package group.exception;

// Exception for access to DB with EntityManagerFactory

public class ErrorInAccessToDb extends Exception {
	public ErrorInAccessToDb(String errorMessage) {
        super(errorMessage);
    }
}
