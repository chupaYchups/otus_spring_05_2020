package ru.chupaYchups.exception;

public class NoSuchBookException extends LibraryException {

    private static final String CANNOT_FIND_BOOK_WITH_ID = "Cannot find book with id: ";

    public NoSuchBookException(long id) {
        super(CANNOT_FIND_BOOK_WITH_ID + id);
    }
}
