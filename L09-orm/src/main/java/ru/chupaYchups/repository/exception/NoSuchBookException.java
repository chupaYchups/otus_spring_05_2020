package ru.chupaYchups.repository.exception;

public class NoSuchBookException extends RuntimeException {

    private static final String CANNOT_FIND_BOOK_WITH_ID = "Cannot find book with id: ";

    public NoSuchBookException(long id) {
        super(CANNOT_FIND_BOOK_WITH_ID + id);
    }
}