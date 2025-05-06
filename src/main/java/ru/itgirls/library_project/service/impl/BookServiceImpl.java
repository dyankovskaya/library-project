package ru.itgirls.library_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.BookDto;
import ru.itgirls.library_project.entity.Book;
import ru.itgirls.library_project.repository.BookRepository;
import ru.itgirls.library_project.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

//    private BookDto convertToDto(Book book) {
//        return BookDto.builder()
//                .id(book.getId())
//                .name(book.getName())
//                .genre(book.getGenre().getName())
//                .authors(book.getAuthors())
//                .build();
//    }
}
