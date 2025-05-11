package ru.itgirls.library_project.service;
import ru.itgirls.library_project.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO getAuthorById(Long id);

    List<AuthorDTO> getAuthorsByNameV1(String name);
    List<AuthorDTO> getAuthorsByNameV2(String name);
    List<AuthorDTO> getAuthorsByNameV3(String name);
}