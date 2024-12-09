package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.bookstore_api.WishlistModel;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishList_ItemsRepository wishListItemsRepository; // Updated to use the actual repository name

    @Autowired
    private bookRepository bookRepository;

    // Create a new wishlist
    public WishlistModel createWishlist(WishlistModel wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Get all wishlists with book details
    public Optional<WishlistModel> getWishlistByIdWithBookDetails(Long id) {
        Optional<WishlistModel> wishlistOpt = wishlistRepository.findById(id);
        if (wishlistOpt.isPresent()) {
            WishlistModel wishlist = wishlistOpt.get();
            List<BookDetailsDTO> bookDetails = bookRepository.findBookDetailsByWishlistId(wishlist.getId());
            wishlist.setBookDetails(bookDetails);
        }
        return wishlistOpt;
    }
    

    // Get a wishlist by ID with book details
    public List<WishlistModel> getAllWishlistsWithBookDetails() {
        List<WishlistModel> wishlists = wishlistRepository.findAll();
        for (WishlistModel wishlist : wishlists) {
            List<BookDetailsDTO> bookDetails = bookRepository.findBookDetailsByWishlistId(wishlist.getId());
            wishlist.setBookDetails(bookDetails); // Assuming WishlistModel has a bookDetails field
        }
        return wishlists;
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
        if (!wishlistRepository.existsById(id)) {
            throw new IllegalArgumentException("Wish List  not found with ID: " + id);
        }
        wishlistRepository.deleteById(id);
    }
}
