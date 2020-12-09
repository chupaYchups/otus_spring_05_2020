package ru.chupaYchups.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class BookDto {

    private final String id;
    private final String name;
    private final String author;
    private final String genre;
    private final List<CommentDto> commentDtoList;

    @Override
    public String toString() {
        return "|=========================================|\n" +
                "Book : " + "id = " + id +
                ", name = '" + name + '\'' +
                ", author = '" + author + '\'' +
                ", genre = '" + genre + '\'' +
                ", comments : \n" +
                commentDtoList.stream().
                    map(CommentDto::toString).
                    collect(Collectors.joining("\n"));
    }
}
