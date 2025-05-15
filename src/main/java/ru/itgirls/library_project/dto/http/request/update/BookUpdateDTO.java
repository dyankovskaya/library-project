package ru.itgirls.library_project.dto.http.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirls.library_project.entity.Genre;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdateDTO {
    private Long id;
    private String name;
    private String genre;
}
