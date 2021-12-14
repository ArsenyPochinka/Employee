package ru.bellintegrator.practice.employee.exception;

/**
 * An exception is thrown if there are no required fields in the request, or an incorrect value the fields
 */
public class WrongRequestException extends RuntimeException {

    /**
     * Constructor
     *
     * @param message a message describing an exceptional situation that has occurred
     */
    public WrongRequestException(String message) {
        super(message);
    }
}
