package ru.chupaYchups.service;

import ru.chupaYchups.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
    void delete(String id);
}
