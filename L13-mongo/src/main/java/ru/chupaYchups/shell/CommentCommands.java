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

    @ShellMethod(value = "Getting all comments for book.", key = "get-book-comments")
    public String getBookComments(@ShellOption(value = {"--id"})String bookId) {
        return commentService.getBookComments(bookId).stream().
            map(CommentDto::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Adding comment for book.", key = "add-book-comment")
    public String addBookComment(@ShellOption(value = {"--id", "--bid"})String bookId, @ShellOption(value = {"--text", "--t"})String commentText) {
        commentService.addComment(bookId, commentText);
        return "Comment added successfully";
    }

    @ShellMethod(value = "Delete comment for book.", key = "delete-comment")
    public String deleteComment(@ShellOption(value = {"--cid"})String commentId) {
        commentService.deleteComment(commentId);
        return "Comment deleted successfully";
    }

    @ShellMethod(value = "Update comment for book.", key = "update-comment")
    public String updateComment(@ShellOption(value = {"--cid"})String commentId, @ShellOption(value = {"--text","--t"})String text) {
        commentService.updateComment(commentId, text);
        return "Comment updated successfully";
    }
}
