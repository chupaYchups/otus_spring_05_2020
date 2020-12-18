package ru.chupaYchups.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorDto {

    private final String id;
    private final String name;

    @Override
    public String toString() {
        return "Аuthor - " +
                "id = " + id +
                ", name = '" + name + '\'';
    }
}
