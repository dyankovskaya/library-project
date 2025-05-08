package ru.itgirls.library_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.http.response.AuthorNoBooksResponseDto;
import ru.itgirls.library_project.dto.http.response.BookResponseDTO;
import ru.itgirls.library_project.entity.Book;
import ru.itgirls.library_project.repository.BookRepository;
import ru.itgirls.library_project.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return convertToDto(book);
    }

    private BookResponseDTO convertToDto(Book book) {
        List<AuthorNoBooksResponseDto> authorNoBookResponseDtoList = book.getAuthors()
                .stream()
                .map(author -> AuthorNoBooksResponseDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .surname(author.getSurname())
                        .build()
                ).toList();
        return BookResponseDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .authors(authorNoBookResponseDtoList)
                .build();
    }
}