package ru.itgirls.library_project.dto;

import lombok.*;
import java.util.List;

@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;

    private List<BookDto> books;
}
