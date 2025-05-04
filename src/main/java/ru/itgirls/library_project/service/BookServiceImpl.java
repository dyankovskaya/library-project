package ru.itgirls.library_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.BookDto;
import ru.itgirls.library_project.entity.Book;
import ru.itgirls.library_project.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return convertToDto(book);
    }

    private BookDto convertToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .authors(book.getAuthors().toString())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .build();

    }
}
