package ru.itgirls.library_project.service;

import ru.itgirls.library_project.dto.BookDto;
import ru.itgirls.library_project.entity.Book;

public interface BookService {
    Book getBookById(Long id);
}
