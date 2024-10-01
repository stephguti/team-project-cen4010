package com.bookstore.bookstore_api;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepository extends JpaRepository<Book, Integer> {
    @Query ("SELECT new com.bookstore.bookstore_api.BookDetailsDTO(b.title, g.name, a.firstName, a.lastName, p.name) " +
    "FROM Book b " +
    "JOIN b.Genre g " +
    "JOIN b.Author a " +
    "JOIN b.Publisher p " + 
    "WHERE g.name = :genreName")
    List<BookDetailsDTO> findBookDetailsByGenre(@Param("genreName") String genreName);

}
