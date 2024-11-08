// added java file
package com.bookstore.bookstore_api;
import java.util.List;

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

        // Add or update author with biography and publisher
        @Transactional
        public void addAuthor(AuthorDTO authorDTO) {
            // Look for an existing author by first name and last name
            Author existingAuthor = authorRepository.findByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName());
            
            if (existingAuthor != null) {
                // Author exists, so we check if we need to update the biography or publisher
                boolean biographyIsMissing = authorDTO.getBiography() != null && existingAuthor.getBiography() == null;
                boolean publisherIsMissing = authorDTO.getPublisher() != null && existingAuthor.getPublisher() == null;
    
                if (biographyIsMissing || publisherIsMissing) {
                    // Update missing biography and/or publisher
                    if (biographyIsMissing) {
                        existingAuthor.setBiography(authorDTO.getBiography());
                    }
                    
                    if (publisherIsMissing) {
                        Publisher publisher = publisherRepository.findByName(authorDTO.getPublisher().getName());
                        if (publisher == null) {
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
    
                if (authorDTO.getPublisher() != null) {
                    Publisher publisher = publisherRepository.findByName(authorDTO.getPublisher().getName());
                    if (publisher == null) {
                        publisher = new Publisher();
                        publisher.setName(authorDTO.getPublisher().getName());
                        publisherRepository.save(publisher);
                    }
                    newAuthor.setPublisher(publisher);
                }
    
                authorRepository.save(newAuthor);
            }
        }
    


    // Get a list of books by author's full name (first and last name)
    public List<Book> getBooksByAuthorName(String firstName, String lastName) {
        // Find the author by first name and last name
        Author author = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found.");
        }
        
        // Retrieve and return the list of books associated with this author
        return bookRepository.findByAuthor(author);
    }
}

