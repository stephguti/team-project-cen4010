package com.bookstore.bookstore_api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book", schema="dbo")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer bookId;

    private String isbn;
    private String title;
    private String description;
    private int yearPublished;
    private float price;
    
    @Column(name = "Rating")
    private float rating;


    @ManyToOne
    @JoinColumn(name = "Genre_ID", referencedColumnName="genreId")
    private Genre Genre;

    @ManyToOne
    @JoinColumn(name = "Author_ID", referencedColumnName="authorId")
    private Author Author;

    @ManyToOne
    @JoinColumn(name = "Publisher_ID", referencedColumnName="publisherId")
    private Publisher Publisher;

    
    @Column(name = "Copies_Sold")
    private Integer copiesSold;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return Genre;
    }

    public void setGenre(Genre Genre) {
        this.Genre = Genre;
    }

    public Author getAuthor() {
        return Author;
    }

    public void setAuthor(Author Author) {
        this.Author = Author;
    }

    public Publisher getPublisher() {
        return Publisher;
    }

    public void setPublisher(Publisher Publisher) {
        this.Publisher = Publisher;
    }


    


}