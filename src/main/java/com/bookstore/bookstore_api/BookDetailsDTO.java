package com.bookstore.bookstore_api;


public class BookDetailsDTO {
    private String title;
    private String genreName;
    private String authorFirstName;
    private String authorLastName;
    private String publisherName;
    private Integer copiesSold;
    private Float rating;

    private String description;
    private Float price;
    private Integer yearPublished;

    public BookDetailsDTO(String title, String genreName, String authorFirstName, String authorLastName,
            String publisherName, Integer copiesSold, Float rating, String Description, Float price, Integer yearPublished) {
        this.title = title;
        this.genreName = genreName;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publisherName = publisherName;
        this.copiesSold = copiesSold;
        this.rating = rating;
        this.description = description;
        this.price = price;
        this.yearPublished = yearPublished;
    }

    public BookDetailsDTO(String title, String genreName, String authorFirstName, String authorLastName,
            String publisherName, Integer copiesSold, Float rating) {
        this.title = title;
        this.genreName = genreName;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publisherName = publisherName;
        this.copiesSold = copiesSold;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

}