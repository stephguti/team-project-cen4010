package com.bookstore.bookstore_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class bookService {

    private final bookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    private final AuthorService authorService;
    
    @Autowired
    public bookService(bookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService, PublisherRepository publisherRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
    }
    
    

    @Transactional
    public void addBook(AddBookDTO addBookDTO) {
        // Manual validation
        validateAddBookDTO(addBookDTO);

        // Handle Author
        Author author = authorRepository.findByFirstNameAndLastName(
            addBookDTO.getAuthor().getFirstName(), 
            addBookDTO.getAuthor().getLastName()
        );

        if (author == null) {
            author = new Author();
            author.setFirstName(addBookDTO.getAuthor().getFirstName());
            author.setLastName(addBookDTO.getAuthor().getLastName());
            authorRepository.save(author);
        }

        /*  OG Handle Publisher
        Publisher publisher = publisherRepository.findByName(addBookDTO.getPublisher().getName());
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setName(addBookDTO.getPublisher().getName());
            publisherRepository.save(publisher);
        } */

        // New publisher handler 
        Optional<Publisher> optionalPublisher = publisherRepository.findByName(addBookDTO.getPublisher().getName());
        Publisher publisher;
        if (optionalPublisher.isPresent()) {
            publisher = optionalPublisher.get();
        } else {
            publisher = new Publisher();
            publisher.setName(addBookDTO.getPublisher().getName());
            publisherRepository.save(publisher);
        }

        // Handle Genre
        Genre genre = genreRepository.findByName(addBookDTO.getGenre().getName());
        if (genre == null) {
            genre = new Genre();
            genre.setName(addBookDTO.getGenre().getName());
            genreRepository.save(genre);
        }

        // Map DTO to Book entity
        Book book = new Book();
        book.setIsbn(addBookDTO.getIsbn());
        book.setTitle(addBookDTO.getTitle());
        book.setDescription(addBookDTO.getDescription());
        book.setPrice(addBookDTO.getPrice());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(genre);
        book.setRating(addBookDTO.getRating());
        book.setYearPublished(addBookDTO.getYearPublished());
        book.setCopiesSold(addBookDTO.getCopiesSold());

        bookRepository.save(book);
    }

    private void validateAddBookDTO(AddBookDTO addBookDTO) {
        if (addBookDTO.getIsbn() == null || addBookDTO.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        
        if (bookRepository.existsByIsbn(addBookDTO.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A book with this ISBN already exists");
        }

        if (addBookDTO.getTitle() == null || addBookDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (addBookDTO.getDescription() == null || addBookDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (addBookDTO.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (addBookDTO.getYearPublished() <= 868) {
            throw new IllegalArgumentException("Year published must be greater than 868");
        }
        if (addBookDTO.getCopiesSold() < 0) {
            throw new IllegalArgumentException("Copies sold must be a positive number");
        }
    }

    // find a book by its ISBN
    public BookDetailsDTO findBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return new BookDetailsDTO(
            book.getTitle(), 
            book.getGenre().getName(), 
            book.getAuthor().getFirstName(), 
            book.getAuthor().getLastName(), 
            book.getPublisher().getName(), 
            book.getCopiesSold(),
            book.getRating(),
            book.getDescription(),
            book.getPrice(),
            book.getYearPublished()
            );

    }


    public List<BookDetailsDTO> getBookDetailsByGenre(String genreName) {
        return bookRepository.findBookDetailsByGenre(genreName);
    }

    public List<BookDetailsDTO> getTop10BestSellingBooks(){
        Pageable pageable = PageRequest.of(0, 10);
        return bookRepository.findTop10BestSellingBooks(pageable);
    }

    public List<BookDetailsDTO> getBooksByRating(float rating){
        return bookRepository.findBooksByRating(rating);
    }

    @Transactional
    public int applyDiscountToBooksByPublisherName(String publisherName, Float discount){
        Optional<Publisher> publisher = publisherRepository.findByName(publisherName);
        if (publisher.isPresent()) {
            return bookRepository.applyDiscountToBooksByPublisher(publisherName, discount);
        }
        else {
             throw new EntityNotFoundException("Publisher with name " + publisherName + " not found.");
        }
    }
}
