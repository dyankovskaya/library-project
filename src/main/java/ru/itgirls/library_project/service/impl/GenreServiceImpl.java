package ru.itgirls.library_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.http.response.AuthorNoBooksResponseDTO;
import ru.itgirls.library_project.dto.http.response.BookGenreResponseDTO;
import ru.itgirls.library_project.dto.http.response.GenreResponseDTO;
import ru.itgirls.library_project.entity.Genre;
import ru.itgirls.library_project.repository.GenreRepository;
import ru.itgirls.library_project.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public GenreResponseDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        System.out.println(genre);
        return convertToDto(genre);
    }

    public GenreResponseDTO convertToDto(Genre genre) {
        List<BookGenreResponseDTO> bookGenreResponseDTO = genre.getBooks()
                .stream()
                .map(book -> BookGenreResponseDTO.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .authors(
                                book.getAuthors().stream()
                                        .map(author -> AuthorNoBooksResponseDTO.builder()
                                                .id(author.getId())
                                                .name(author.getName())
                                                .surname(author.getSurname())
                                                .build()).collect(Collectors.toSet()))
                        .build()
                ).toList();
        return GenreResponseDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .book(bookGenreResponseDTO)
                .build();
    }
}