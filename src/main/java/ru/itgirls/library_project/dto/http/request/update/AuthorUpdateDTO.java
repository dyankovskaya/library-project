package ru.itgirls.library_project.dto.http.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorUpdateDTO {
    private Long id;
    private String name;
    private String surname;
}
