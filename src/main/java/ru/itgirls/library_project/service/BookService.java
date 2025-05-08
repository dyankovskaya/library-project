package ru.itgirls.library_project.service;

import ru.itgirls.library_project.dto.BookDto;
import ru.itgirls.library_project.dto.http.response.BookResponseDTO;
import ru.itgirls.library_project.entity.Book;

public interface BookService {
    BookResponseDTO getBookById(Long id);
}
