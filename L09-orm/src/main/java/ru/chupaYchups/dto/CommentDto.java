package ru.chupaYchups.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CommentDto {

    private final long commentId;
    private final String commentString;

    @Override
    public String toString() {
        return "Comment - " +
                "id = '" + commentId + '\'' +
                ", text = '" + commentString + '\'';
    }
}
