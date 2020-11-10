package ru.chupaYchups.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorDto {

    private final String name;

    @Override
    public String toString() {
        return "Аuthor - " +
                "name = '" + name + '\'';
    }
}
