package ru.chupaYchups.exception;

public class NoSuchCommentException extends LibraryException {

    private static final String CANNOT_FIND_COMMENT_WITH_ID = "Cannot find comment with id: ";

    public NoSuchCommentException(long id) {
        super(CANNOT_FIND_COMMENT_WITH_ID + id);
    }

}
