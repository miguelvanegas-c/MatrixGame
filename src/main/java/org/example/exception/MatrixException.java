package org.example.exception;

public class MatrixException extends Exception {
    public static String INVALID_MOVE = "Invalid move";
    public static String UNEXPECTED_ERROR = "Unexpected error";
    public MatrixException(String message) {
        super(message);
    }
}
