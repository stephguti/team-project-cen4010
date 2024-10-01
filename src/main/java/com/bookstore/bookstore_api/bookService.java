package com.bookstore.bookstore_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bookService {

    @Autowired
    private bookRepository bookRepository;

    public List<BookDetailsDTO> getBookDetailsByGenre(String genreName) {
        return bookRepository.findBookDetailsByGenre(genreName);
    }
}
