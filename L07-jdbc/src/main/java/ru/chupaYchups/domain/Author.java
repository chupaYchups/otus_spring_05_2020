package ru.chupaYchups.domain;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {
    private Long id;
    private final String name;
}
