package ru.itgirls.library_project.dto.http.request.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreateDTO {
    private String name;
    private String genre;
}
