package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Create a new wishlist
    public WishlistModel createWishlist(WishlistModel wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Get all wishlists
    public List<WishlistModel> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    // Get a wishlist by ID
    public Optional<WishlistModel> getWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    // Update a wishlist
    public WishlistModel updateWishlist(Long id, WishlistModel wishlistDetails) {
        WishlistModel wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found with id: " + id));
        
        wishlist.setWishListName(wishlistDetails.getWishListName());
        wishlist.setUserId(wishlistDetails.getUserId());
        return wishlistRepository.save(wishlist);
    }

    // Delete a wishlist
    public void deleteWishlist(Long id) {
        WishlistModel wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found with id: " + id));
        wishlistRepository.delete(wishlist);
    }
}
