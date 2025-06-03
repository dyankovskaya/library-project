package ru.itgirls.library_project.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.BookDTO;
import ru.itgirls.library_project.dto.http.request.create.BookCreateDTO;
import ru.itgirls.library_project.dto.http.request.update.BookUpdateDTO;
import ru.itgirls.library_project.dto.http.response.AuthorNoBooksResponseDTO;
import ru.itgirls.library_project.dto.http.response.BookResponseDTO;
import ru.itgirls.library_project.entity.Book;
import ru.itgirls.library_project.entity.Genre;
import ru.itgirls.library_project.repository.BookRepository;
import ru.itgirls.library_project.repository.GenreRepository;
import ru.itgirls.library_project.service.BookService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

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
        List<AuthorNoBooksResponseDTO> authorNoBooksResponseDTO = book.getAuthors().stream()
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

    @Override
    public BookDTO createBook(BookCreateDTO bookCreateDTO) {
        Book book = bookRepository.save(convertDtoToBook(bookCreateDTO));
        BookDTO bookDto = convertEntityToDto(book);
        return bookDto;
    }

    @Override
    public BookDTO updateBook(BookUpdateDTO bookUpdateDTO) {
        Genre genre = genreRepository.findGenreByName(bookUpdateDTO.getGenre()).orElseThrow();
        Book book = bookRepository.findById(bookUpdateDTO.getId()).orElseThrow();
        book.setName(bookUpdateDTO.getName());
        book.setGenre(genre);
        Book savedBook = bookRepository.save(book);
        BookDTO bookDto = convertEntityToDto(savedBook);
        return bookDto;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    private Book convertDtoToBook(BookCreateDTO bookCreateDTO) {
        Genre genre = genreRepository.findGenreByName(bookCreateDTO.getGenre()).orElseThrow();

    return Book.builder()
                .name(bookCreateDTO.getName())
                .genre(genre)
                .authors(Collections.emptyList())
                .build();
    }

    // для 21 недели фронт
    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}