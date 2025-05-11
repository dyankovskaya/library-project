package ru.itgirls.library_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirls.library_project.dto.http.response.AuthorNoBooksResponseDTO;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDTO {
    private Long id;
    private String name;
    private String genre;
}