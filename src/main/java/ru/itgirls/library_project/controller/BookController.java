package ru.itgirls.library_project.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.library_project.dto.BookDTO;
import ru.itgirls.library_project.dto.http.response.BookResponseDTO;
import ru.itgirls.library_project.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    BookResponseDTO getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/book")
    BookDTO getBookByName(@RequestParam("name") String name) {
        return bookService.getBookByNameV1(name);
    }

    @GetMapping("/book/v2")
    BookDTO getBookByNameV2(@RequestParam("name") String name) {
        return bookService.getBookByNameV2(name);
    }

    @GetMapping("/book/v3")
    BookDTO getBookByNameV3(@RequestParam("name") String name) {
        return bookService.getBookByNameV3(name);
    }
}