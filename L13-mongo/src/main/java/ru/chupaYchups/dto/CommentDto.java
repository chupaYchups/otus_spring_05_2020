package ru.chupaYchups.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CommentDto {
    private final String commentId;
    private final String commentString;
}
