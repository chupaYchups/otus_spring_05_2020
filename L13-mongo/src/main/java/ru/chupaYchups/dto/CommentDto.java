package ru.chupaYchups.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentDto {

    private final String commentId;
    private final String commentString;

    @Override
    public String toString() {
        return "Comment - " +
                "id = '" + commentId + '\'' +
                ", text = '" + commentString + '\'';
    }
}