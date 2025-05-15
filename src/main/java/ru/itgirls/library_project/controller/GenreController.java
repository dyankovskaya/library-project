package ru.itgirls.library_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.library_project.dto.http.response.GenreResponseDTO;
import ru.itgirls.library_project.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genre/{id}")
    GenreResponseDTO getGenreById(@PathVariable("id") Long id) {
        return genreService.getGenreById(id);
    }

    @GetMapping("/genre")
    GenreResponseDTO getGenreByName(@RequestParam("name") String name) {
        return genreService.getGenreByName(name);
    }
}