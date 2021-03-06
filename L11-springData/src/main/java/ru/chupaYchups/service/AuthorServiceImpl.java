package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.repository.AuthorRepository;
import ru.chupaYchups.dto.AuthorDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private static class AuthorDtoMapper implements Function<Author, AuthorDto> {
        @Override
        public AuthorDto apply(Author author) {
            return new AuthorDto(author.getId(), author.getName());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).
            map(new AuthorDtoMapper()).
            collect(Collectors.toList());
    }
}
