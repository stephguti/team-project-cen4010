package com.bookstore.bookstore_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class bookService {

    private final bookRepository bookRepository;

    @Autowired
    public bookService(bookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookDetailsDTO> getBookDetailsByGenre(String genreName) {
        return bookRepository.findBookDetailsByGenre(genreName);
    }

    public List<BookDetailsDTO> getTop10BestSellingBooks(){
        Pageable pageable = PageRequest.of(0, 10);
        return bookRepository.findTop10BestSellingBooks(pageable);
    }
}
