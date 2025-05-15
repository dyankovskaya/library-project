package ru.itgirls.library_project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library_project.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findGenreByName(String name);
}