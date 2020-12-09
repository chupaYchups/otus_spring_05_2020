package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.repository.AuthorRepository;
import ru.chupaYchups.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).
            map(author -> new AuthorDto(author.getName())).
            collect(Collectors.toList());
    }
}
