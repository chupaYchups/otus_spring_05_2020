package ru.chupaYchups.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenreDto {

    private final String id;
    private final String name;

    @Override
    public String toString() {
        return "Genre - " +
                "name = '" + name + '\'';
    }
}
