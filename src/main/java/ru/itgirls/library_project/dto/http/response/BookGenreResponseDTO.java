package ru.itgirls.library_project.dto.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookGenreResponseDTO {
        private Long id;
        private String name;
        private String genre;
        private Set<AuthorNoBooksResponseDTO> authors;
}