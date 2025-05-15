package ru.itgirls.library_project.service;

import ru.itgirls.library_project.dto.BookDTO;
import ru.itgirls.library_project.dto.http.request.create.BookCreateDTO;
import ru.itgirls.library_project.dto.http.request.update.BookUpdateDTO;
import ru.itgirls.library_project.dto.http.response.BookResponseDTO;

public interface BookService {
    BookResponseDTO getBookById(Long id);
    BookDTO getBookByNameV1(String name);
    BookDTO getBookByNameV2(String name);
    BookDTO getBookByNameV3(String name);

    BookDTO createBook(BookCreateDTO bookCreateDTO);
    BookDTO updateBook(BookUpdateDTO bookUpdateDTO);
    void deleteBookById(Long id);
}
