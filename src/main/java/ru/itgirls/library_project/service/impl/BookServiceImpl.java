package ru.itgirls.library_project.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.BookDTO;
import ru.itgirls.library_project.dto.http.response.AuthorNoBooksResponseDTO;
import ru.itgirls.library_project.dto.http.response.BookResponseDTO;
import ru.itgirls.library_project.entity.Book;
import ru.itgirls.library_project.repository.BookRepository;
import ru.itgirls.library_project.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return convertToDto(book);
    }

    private BookResponseDTO convertToDto(Book book) {
        List<AuthorNoBooksResponseDTO> authorNoBookResponseDtoList = book.getAuthors()
                .stream()
                .map(author -> AuthorNoBooksResponseDTO.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .surname(author.getSurname())
                        .build()
                ).toList();
        return BookResponseDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .authors(authorNoBookResponseDtoList)
                .build();
    }

    @Override
    public BookDTO getBookByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDTO getBookByNameV2(String name) {
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertEntityToDto(book);
    }

    private BookDTO convertEntityToDto(Book book) {
        List<AuthorNoBooksResponseDTO> authorNoBooksResponseDTO = book.getAuthors()
                .stream()
                .map(author -> AuthorNoBooksResponseDTO.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .surname(author.getSurname())
                        .build()).toList();
        return BookDTO.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
//                .authors(authorNoBooksResponseDTO)
                .build();
    }

    @Override
    public BookDTO getBookByNameV3(String name) {
        Specification<Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });

        Book book = bookRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(book);
    }
}