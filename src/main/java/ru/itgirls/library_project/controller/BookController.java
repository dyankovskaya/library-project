package ru.itgirls.library_project.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.library_project.dto.BookDto;
import ru.itgirls.library_project.entity.Book;
import ru.itgirls.library_project.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }
}