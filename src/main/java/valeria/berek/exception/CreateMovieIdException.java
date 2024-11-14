package valeria.berek.exception;

public class CreateMovieIdException extends Exception {

    // Constructeurs
    public CreateMovieIdException(String message) {
        super(message);
    }

    public CreateMovieIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
