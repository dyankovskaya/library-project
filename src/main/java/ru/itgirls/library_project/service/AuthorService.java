package ru.itgirls.library_project.service;
import ru.itgirls.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);
}