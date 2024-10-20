package com.bookstore.bookstore_api;

public class GenreDTO {
    private String genreName;

    // Constructors
    public GenreDTO() {}

    public GenreDTO(String genreName) {
        this.genreName = genreName;
    }

    // Getters and Setters
    public String getName() {
        return genreName;
    }

    public void setName(String genreName) {
        this.genreName = genreName;
    }
}
