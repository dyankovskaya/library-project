package ru.itgirls.library_project.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.library_project.dto.AuthorDTO;
import ru.itgirls.library_project.dto.BookDTO;
import ru.itgirls.library_project.dto.http.request.create.BookCreateDTO;
import ru.itgirls.library_project.dto.http.request.update.AuthorUpdateDTO;
import ru.itgirls.library_project.dto.http.request.update.BookUpdateDTO;
import ru.itgirls.library_project.dto.http.response.BookResponseDTO;
import ru.itgirls.library_project.service.BookService;

@RestController

public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

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

//содание новой книги
    @PostMapping("/book/create")
    BookDTO createBook(@RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.createBook(bookCreateDTO);
    }

//обновление существующей книги
    @PutMapping("/book/update")
    BookDTO updateBook(@RequestBody BookUpdateDTO bookUpdateDto) {
    return bookService.updateBook(bookUpdateDto);
}

//удаление книги
    @DeleteMapping("/book/delete/{id}")
    void updateBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }
// MVC Controller
    @GetMapping("/books")
    String getBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }
}
