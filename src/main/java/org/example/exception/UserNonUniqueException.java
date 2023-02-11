package org.example.exception;

public class UserNonUniqueException extends Exception {
    public UserNonUniqueException(String massage) {
        super(massage);
    }
}
