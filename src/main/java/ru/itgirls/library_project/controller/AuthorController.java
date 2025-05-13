package ru.itgirls.library_project.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.library_project.dto.AuthorDTO;
import ru.itgirls.library_project.dto.http.request.create.AuthorCreateDTO;
import ru.itgirls.library_project.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    AuthorDTO getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author/v1")
    List<AuthorDTO> getAuthorByNameV1(@RequestParam("name") String name) {
        return authorService.getAuthorsByNameV1(name);
    }

    @GetMapping("/author/v2")
    List<AuthorDTO> getAuthorByNameV2(@RequestParam("name") String name) {
        return authorService.getAuthorsByNameV2(name);
    }

    @GetMapping("/author/v3")
    List<AuthorDTO> getAuthorsByNameV3(@RequestParam("name") String name) {
        return authorService.getAuthorsByNameV3(name);
    }

    @PostMapping("/author/create")
    AuthorDTO createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO) {
        return authorService.createAuthor(authorCreateDTO);
    }
}