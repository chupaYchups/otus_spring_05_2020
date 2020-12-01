package ru.chupaYchups.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.chupaYchups.dto.CommentDto;
import ru.chupaYchups.service.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    @ShellMethod(value = "Getting all comments for book.", key = {"gbc", "bc", "get-book-comments"})
    public String getBookComments(@ShellOption(value = {"id"}) Long bookId) {
        return commentService.getBookComments(bookId).stream().
            map(CommentDto::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Adding comment for book.", key = {"ac", "abc", "add-book-comment"})
    public String addBookComment(@ShellOption(value = {"id", "bid"})Long bookId, @ShellOption(value = {"text", "t"})String commentText) {
        commentService.addComment(bookId, commentText);
        return "Comment added successfully";
    }

    @ShellMethod(value = "Delete comment for book.", key = {"dbc", "dc", "delete-comment"})
    public String deleteComment(@ShellOption(value = {"bid"})Long bookId, @ShellOption(value = {"cid"})Long commentId) {
        commentService.deleteComment(commentId);
        return "Comment deleted successfully";
    }

    @ShellMethod(value = "Update comment for book.", key = {"ubc", "uc", "update-comment"})
    public String updateComment(@ShellOption(value = {"cid"})Long commentId, @ShellOption(value = {"text","t"})String text) {
        commentService.updateComment(commentId, text);
        return "Comment updated successfully";
    }
}
