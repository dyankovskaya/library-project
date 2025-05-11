package ru.itgirls.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.itgirls.library_project.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
   // 1 способ запроса к бд через автогенерацию запроса
   // Optional<Author> findAuthorByName(String name);
   // 2 способ запроса к бд через аннотацию @Query
    @Query(nativeQuery = true, value = "SELECT * FROM AUTHOR WHERE name = ?")
    Optional<Author> findAuthorByNameBySQL(String name);
    // 3 способ запроса к бд через JPA Criteria Query

}