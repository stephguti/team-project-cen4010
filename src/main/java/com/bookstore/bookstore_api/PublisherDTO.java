package com.bookstore.bookstore_api;

public class PublisherDTO {
    private Integer publisherId;
    private String name;

    // Constructors
    public PublisherDTO() {}

    public PublisherDTO(Integer publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }

    // Getters and Setters
    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
