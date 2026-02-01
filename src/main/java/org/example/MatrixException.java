package org.example;

public class MatrixException extends Exception {
    public static String INVALID_MOVE = "Invalid move";

    public MatrixException(String message) {
        super(message);
    }
}
