package ru.chupaYchups.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CommentDto {
    private final Long commentId;
    private final String commentString;
}
