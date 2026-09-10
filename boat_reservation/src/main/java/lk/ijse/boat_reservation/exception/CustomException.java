package lk.ijse.boat_reservation.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(String message) {
        super(message);
        this.code = 400; // Default error code
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.code = 400;
    }

    public CustomException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}