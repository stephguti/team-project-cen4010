package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/addauthor")
    public ResponseEntity<Void> createAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.addAuthor(authorDTO);
        return ResponseEntity.ok().build(); 
    }

    @GetMapping("/books/{firstName}/{lastName}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String firstName, @PathVariable String lastName) {
        List<Book> books = authorService.getBooksByAuthorName(firstName, lastName);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build(); // No books found for this author
        }
        return ResponseEntity.ok(books);
    }
}
