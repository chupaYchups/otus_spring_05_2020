package ru.chupaYchups.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private long id;
    private String name;
    private String author;
    private String genre;
    private List<CommentDto> commentDtoList;
}
