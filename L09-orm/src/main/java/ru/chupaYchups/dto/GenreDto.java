package ru.chupaYchups.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GenreDto {
    private final long id;
    private final String name;
}
