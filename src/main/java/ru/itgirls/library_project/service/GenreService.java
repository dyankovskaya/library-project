package ru.itgirls.library_project.service;

import ru.itgirls.library_project.dto.GenreDto;

public interface GenreService {
    GenreDto getGenreById(Long id);
}

