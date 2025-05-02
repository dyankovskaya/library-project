package ru.itgirls.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library_project.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}