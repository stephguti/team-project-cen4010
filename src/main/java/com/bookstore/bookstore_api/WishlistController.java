package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Get all wishlists with book details
    @GetMapping
    public List<WishlistModel> getAllWishlists() {
        return wishlistService.getAllWishlistsWithBookDetails(); 
    }

    // Get wishlist by ID with book details
    @GetMapping("/{id}")
    public ResponseEntity<WishlistModel> getWishlistById(@PathVariable Long id) {
        Optional<WishlistModel> wishlist = wishlistService.getWishlistByIdWithBookDetails(id); 
        return wishlist.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }    

    // Get wishlists by user_id with book details
    @GetMapping("/user/{userId}")
    public List<WishlistModel> getWishlistsByUserId(@PathVariable Long userId) {
        return wishlistService.getAllWishlistsWithBookDetails().stream() 
                .filter(wishlist -> wishlist.getUserId().equals(userId))
                .toList();
    }

    // Create a new wishlist
    @PostMapping
    public WishlistModel createWishlist(@RequestBody WishlistModel wishlist) {
        return wishlistService.createWishlist(wishlist);
    }

    // Update a wishlist by ID
    @PutMapping("/{id}")
    public ResponseEntity<WishlistModel> updateWishlist(@PathVariable Long id, @RequestBody WishlistModel wishlistDetails) {
        try {
            WishlistModel updatedWishlist = wishlistService.updateWishlist(id, wishlistDetails);
            return ResponseEntity.ok(updatedWishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a wishlist by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        try {
            wishlistService.deleteWishlist(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
