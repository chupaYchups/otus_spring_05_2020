package ru.chupaYchups.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookDto {

    private final long id;
    private final String name;
    private final String author;
    private final String genre;

    @Override
    public String toString() {
        return "Book : " + "id = " + id +
                ", name = '" + name + '\'' +
                ", author = '" + author + '\'' +
                ", genre = '" + genre + '\'';
    }
}
