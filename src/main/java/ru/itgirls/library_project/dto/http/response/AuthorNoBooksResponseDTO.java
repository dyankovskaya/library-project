package ru.itgirls.library_project.dto.http.response;

import lombok.*;

@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class AuthorNoBooksResponseDTO {
        private Long id;
        private String name;
        private String surname;
}