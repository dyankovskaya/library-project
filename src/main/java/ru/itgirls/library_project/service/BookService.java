package ru.itgirls.library_project.service;

import ru.itgirls.library_project.dto.BookDto;

public interface BookService {
    BookDto getBookById(Long id);
}
