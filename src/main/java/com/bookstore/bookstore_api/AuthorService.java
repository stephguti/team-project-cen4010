// added java file
package com.bookstore.bookstore_api;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final bookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, 
                         bookRepository bookRepository, 
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }
        @Transactional
        public void addAuthor(AuthorDTO authorDTO) {
            // Look for an existing author by first name and last name
            Author existingAuthor = authorRepository.findByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName());
            
            if (existingAuthor != null) {
                boolean biographyIsMissing = authorDTO.getBiography() != null && existingAuthor.getBiography() == null;
                boolean publisherIsMissing = authorDTO.getPublisher() != null && existingAuthor.getPublisher() == null;
    
                if (biographyIsMissing || publisherIsMissing) {
                    if (biographyIsMissing) {
                        existingAuthor.setBiography(authorDTO.getBiography());
                    }
                    
                    /* OG CHECK PUBLISHER
                    if (publisherIsMissing) {
                        Publisher publisher = publisherRepository.findByName(authorDTO.getPublisher().getName());
                        if (publisher == null) {
                            publisher = new Publisher();
                            publisher.setName(authorDTO.getPublisher().getName());
                            publisherRepository.save(publisher);
                        }
                        existingAuthor.setPublisher(publisher);
                    } */

                    // If publisher exsists, use that publisher, if not, create new one
                    if (publisherIsMissing) {
                        Optional<Publisher> optionalPublisher = publisherRepository.findByName(authorDTO.getPublisher().getName());
                        Publisher publisher;
                        if (optionalPublisher.isPresent()) {
                            publisher = optionalPublisher.get();
                        } else {
                            publisher = new Publisher();
                            publisher.setName(authorDTO.getPublisher().getName());
                            publisherRepository.save(publisher);
                        }
                        existingAuthor.setPublisher(publisher);
                    }
    
                    // Save the updated author information
                    authorRepository.save(existingAuthor);
                } else {
                    // If both biography and publisher are already set, throw an error
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author already exists with biography and publisher.");
                }
            } else {
                // If author does not exist, create new author with biography and publisher
                Author newAuthor = new Author();
                newAuthor.setFirstName(authorDTO.getFirstName());
                newAuthor.setLastName(authorDTO.getLastName());
                newAuthor.setBiography(authorDTO.getBiography());
                
                /*  OG CHECK IF PUBLISHER EXISTS
                if (authorDTO.getPublisher() != null) {
                    Publisher publisher = publisherRepository.findByName(authorDTO.getPublisher().getName());
                    if (publisher == null) {
                        publisher = new Publisher();
                        publisher.setName(authorDTO.getPublisher().getName());
                        publisherRepository.save(publisher);
                    }
                    newAuthor.setPublisher(publisher);
                }*/


                // If publisher exsists, use that publisher, if not, create new one
                if (authorDTO.getPublisher() != null) {
                    Optional<Publisher> optionalPublisher = publisherRepository.findByName(authorDTO.getPublisher().getName());
                    Publisher publisher;
                    
                    if (optionalPublisher.isPresent()) {
                        // Publisher exists, use it
                        publisher = optionalPublisher.get();
                    } else {
                        // Publisher doesn't exist, create and save a new one
                        publisher = new Publisher();
                        publisher.setName(authorDTO.getPublisher().getName());
                        publisherRepository.save(publisher);
                    }
                    
                    newAuthor.setPublisher(publisher);
                }
    
                authorRepository.save(newAuthor);
            }
        }
    
        @Transactional
        public List<BookTitleDTO> getBooksByAuthorName(String firstName, String lastName) {
            Author author = authorRepository.findByFirstNameAndLastName(firstName, lastName);
    
            if (author == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found: " + firstName + " " + lastName);
            }

            List<BookTitleDTO> books = bookRepository.findBookTitlesByAuthor(firstName, lastName);
    
            if (books.isEmpty()) {
                // Throw an exception if no books are found for the author
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found for author: " + firstName + " " + lastName);
            }
    
            return books;
        }


    /* OG METHOD
    public List<Book> getBooksByAuthorName(String firstName, String lastName) {
        Author author = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found.");
        }
        
        return bookRepository.findByAuthor(author);
    }
    */
}

