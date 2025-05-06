package ru.itgirls.library_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@AllArgsConstructor
//@NoArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class AuthorGenreResponseDto {
        private Long id;
        private String name;
        private String surname;
}
