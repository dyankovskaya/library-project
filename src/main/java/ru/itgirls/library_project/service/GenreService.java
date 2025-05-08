package ru.itgirls.library_project.service;

import ru.itgirls.library_project.dto.http.response.GenreResponseDTO;

public interface GenreService {
    GenreResponseDTO getGenreById(Long id);
}

