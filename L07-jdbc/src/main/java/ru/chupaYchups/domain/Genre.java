package ru.chupaYchups.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
 public class Genre {
    private long id;
    private final String name;
}
