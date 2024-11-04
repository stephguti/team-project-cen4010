package com.bookstore.bookstore_api;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class Controller {

    private final bookService bookService;
    
    @Autowired
    public Controller(bookService bookService){
        this.bookService = bookService;
    }

    // adding book
    @PostMapping("/addbook")
    public void createBook(@RequestBody AddBookDTO addBookDTO) {
        bookService.addBook(addBookDTO);
    }

    // // searching for book with isbn
    // @GetMapping("/isbn/{isbn}")
    // public ResponseEntity<BookDetailsDTO> getBookByIsbn(@PathVariable String isbn) {
    //     BookDetailsDTO bookDetails = bookService.findBookByIsbn(isbn);
    //     return ResponseEntity.ok(bookDetails);
    // }

    @GetMapping("/genre/{genreName}")
    public List<BookDetailsDTO> getBookDetailsbyGenre(@PathVariable String genreName){
        return bookService.getBookDetailsByGenre(genreName);
    }


    @GetMapping("/top-sellers")
    public List<BookDetailsDTO> getTop10BestSellingBooks() {
        return bookService.getTop10BestSellingBooks();
    }


    @GetMapping("/by-rating")
    public List<BookDetailsDTO> getBooksByRating(@RequestParam("rating") float rating){
        return bookService.getBooksByRating(rating);
    }

    }
    

