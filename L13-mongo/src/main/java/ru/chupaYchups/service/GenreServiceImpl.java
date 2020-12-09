package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chupaYchups.repository.GenreRepository;
import ru.chupaYchups.dto.GenreDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false).
            map(genre -> new GenreDto(genre.getName())).
            collect(Collectors.toList());
    }
}
