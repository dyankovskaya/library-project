package ru.itgirls.library_project.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.AuthorDTO;
import ru.itgirls.library_project.dto.BookDTO;
import ru.itgirls.library_project.entity.Author;
import ru.itgirls.library_project.repository.AuthorRepository;
import ru.itgirls.library_project.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto(author);
    }

    private AuthorDTO convertToDto(Author author) {
        List<BookDTO> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        //В авторе по id нужно вывести список его книг,
                        //не нужно выводить авторов. хотя если бы у
                        //книги было несколько авторов, было бы полезно выводить их
//                       .authors(book.getAuthors().stream()
//                       .map(author1 -> AuthorNoBooksResponseDTO.builder()
//                               .id(author.getId())
//                               .name(author.getName())
//                               .surname(author.getSurname())
//                               .build()).collect(toList()))
                        .build()
                ).toList();
        return AuthorDTO.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public AuthorDTO getAuthorByNameV2(String name) {
        Author author = authorRepository.findAuthorByNameBySQL(name).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public AuthorDTO getAuthorByNameV3(String name) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });

        Author author = authorRepository.findOne(specification).orElseThrow();
        return convertToDto(author);
    }

}