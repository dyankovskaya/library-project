package ru.itgirls.library_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenreDTO {
    private Long id;
    private String name;
    private List<BookDTO> book;
}
