package org.example.exceptions;

public class FileNotFoundExc extends RuntimeException {
    public FileNotFoundExc(String message) {
        super(message);
    }
}
