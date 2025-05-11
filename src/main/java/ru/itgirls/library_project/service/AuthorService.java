package ru.itgirls.library_project.service;
import ru.itgirls.library_project.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO getAuthorById(Long id);

    List<AuthorDTO> getAuthorsByNameV1(String name);
    AuthorDTO getAuthorByNameV2(String name);
    AuthorDTO getAuthorByNameV3(String name);
}