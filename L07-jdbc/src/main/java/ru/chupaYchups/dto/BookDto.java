package ru.chupaYchups.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookDto {

    private final String name;
    private final String author;
    private final String genre;

    @Override
    public String toString() {
        return "Book : '" + name + '\'' +
                ", author = '" + author + '\'' +
                ", genre='" + genre + '\'';
    }
}
