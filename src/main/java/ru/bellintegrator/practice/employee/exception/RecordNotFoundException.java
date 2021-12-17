package ru.bellintegrator.practice.employee.exception;

/**
 * An exception is thrown if the desired record is missing in the database
 */
public class RecordNotFoundException extends RuntimeException {

    /**
     * Constructor
     *
     * @param message a message describing an exceptional situation
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
