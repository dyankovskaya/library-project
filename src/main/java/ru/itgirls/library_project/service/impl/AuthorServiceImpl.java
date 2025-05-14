package ru.itgirls.library_project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirls.library_project.dto.AuthorDTO;
import ru.itgirls.library_project.dto.BookDTO;
import ru.itgirls.library_project.dto.http.request.create.AuthorCreateDTO;
import ru.itgirls.library_project.dto.http.request.update.AuthorUpdateDTO;
import ru.itgirls.library_project.entity.Author;
import ru.itgirls.library_project.repository.AuthorRepository;
import ru.itgirls.library_project.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto(author);
    }

    // 1 способ с помощью автогенерации запроса
    @Override
    public List<AuthorDTO> getAuthorsByNameV1(String name) {
        List<Author> authors = authorRepository.findAuthorsByName(name);
        if (authors.isEmpty()) {
            throw new EntityNotFoundException("Автор с таким именем не найден.");
        }
        return authors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 2 способ с помощью аннотации @Query
    @Override
    public List<AuthorDTO> getAuthorsByNameV2(String name) {
        List<Author> authors = authorRepository.findAuthorsByName(name);
        if (authors.isEmpty()) {
            throw new EntityNotFoundException("Автор с таким именем не найден.");
        }
        return authors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 3 способ с помощью объекта Спецификации
    @Override
    public List<AuthorDTO> getAuthorsByNameV3(String name) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });
        List<Author> authors = authorRepository.findAll(specification);
        if (authors.isEmpty()) {
            throw new EntityNotFoundException("Автор с таким имененм не найден.");
        }
        return authors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //POST - CREATE метод для создания автора
    @Override
    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDto) {
        Author author = authorRepository.save(convertDtoToEntity(authorCreateDto));
        AuthorDTO authorDTO = convertToDto(author);
        return authorDTO;
    }

    private Author convertDtoToEntity(AuthorCreateDTO authorCreateDto) {
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    //Конвертация объекта Автора в объект АвторДТО с id, именем, фамилией и списком книг в формате: id, название, жанр
    private AuthorDTO convertToDto(Author author) {
        List<BookDTO> bookDtoList = null;
        if (author.getBooks() != null) {
            author.getBooks()
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
        }
        return AuthorDTO.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public AuthorDTO updateAuthor(AuthorUpdateDTO authorUpdateDto) {
        Author author = authorRepository.findById(authorUpdateDto.getId()).orElseThrow();
        author.setName(authorUpdateDto.getName());
        author.setSurname(authorUpdateDto.getSurname());
        Author savedAuthor = authorRepository.save(author);
        AuthorDTO authorDto = convertToDto(savedAuthor);
        return authorDto;
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}