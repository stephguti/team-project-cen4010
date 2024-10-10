package com.bookstore.bookstore_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class Controller {

    @Autowired
    private bookService bookService;

    @GetMapping("/genre/{genreName}")
    public List<BookDetailsDTO> getBookDetailsbyGenre(@PathVariable String genreName){
        return bookService.getBookDetailsByGenre(genreName);
    }

    }
    

