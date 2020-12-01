package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chupaYchups.repository.AuthorRepository;
import ru.chupaYchups.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.getAll().
            stream().
            map(author -> new AuthorDto(author.getName())).
            collect(Collectors.toList());
    }
}
