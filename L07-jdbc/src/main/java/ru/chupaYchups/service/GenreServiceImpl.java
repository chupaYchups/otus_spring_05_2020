package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chupaYchups.dao.GenreDao;
import ru.chupaYchups.dto.GenreDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{
    private final GenreDao genreDao;

    @Override
    public List<GenreDto> getAllGenres() {
        return genreDao.getAll().
            stream().
            map(genre -> new GenreDto(genre.getName())).
            collect(Collectors.toList());
    }
}
