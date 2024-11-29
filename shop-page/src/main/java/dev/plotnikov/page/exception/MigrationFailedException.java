package dev.plotnikov.page.exception;

public class MigrationFailedException extends Exception {

    public MigrationFailedException(String message) {
        super(message);
    }

    public MigrationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
