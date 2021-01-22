package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.exception.NoSuchAuthorException;
import ru.chupaYchups.repository.AuthorRepository;
import ru.chupaYchups.dto.AuthorDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().
            map(author -> new AuthorDto(author.getId(), author.getName())).
            collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(String id) {
        Author author = authorRepository.
            findById(id).orElseThrow(() ->new NoSuchAuthorException(id));
        authorRepository.delete(author);
    }
}
