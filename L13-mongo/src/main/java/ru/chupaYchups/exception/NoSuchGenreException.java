package ru.chupaYchups.exception;

public class NoSuchGenreException extends LibraryException {

    private static final String CANNOT_FIND_GENRE_WITH_ID_MSG = "Cannot find author with id: %s";

    public NoSuchGenreException(String id) {
        super(String.format(CANNOT_FIND_GENRE_WITH_ID_MSG, id));
    }
}
