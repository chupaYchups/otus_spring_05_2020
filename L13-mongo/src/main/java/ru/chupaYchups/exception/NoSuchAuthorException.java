package ru.chupaYchups.exception;

public class NoSuchAuthorException extends RuntimeException {

    private static final String CANNOT_FIND_AUTHOR_WITH_ID_MSG = "Cannot find author with id: %s";

    public NoSuchAuthorException(String id) {
        super(String.format(CANNOT_FIND_AUTHOR_WITH_ID_MSG, id));
    }
}
