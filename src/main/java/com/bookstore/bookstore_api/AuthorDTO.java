package com.bookstore.bookstore_api;

public class AuthorDTO {
    private Integer authorId;
    private String firstName;
    private String lastName;
    // added
    private String biography;
    private PublisherDTO publisher; 

    public AuthorDTO() {}

    public AuthorDTO(Integer authorId, String firstName, String lastName) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // added
    public AuthorDTO(Integer authorId, String firstName, String lastName, 
        String biography, PublisherDTO publisher) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.publisher = publisher;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // added four methods below
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }
}
