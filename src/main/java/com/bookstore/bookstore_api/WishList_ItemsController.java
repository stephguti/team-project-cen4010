package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist-items")
public class WishList_ItemsController {

    private final WishList_ItemsService wishListItemsService;

    @Autowired
    public WishList_ItemsController(WishList_ItemsService wishListItemsService) {
        this.wishListItemsService = wishListItemsService;
    }

    // Get all wishlist items
    @GetMapping
    public List<WishList_ItemsModel> getAllWishListItems() {
        return wishListItemsService.getAllWishListItems();
    }

    // Get wishlist item by ID
    @GetMapping("/{id}")
    public ResponseEntity<WishList_ItemsModel> getWishListItemById(@PathVariable Long id) {
        return wishListItemsService.getWishListItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get wishlist items by wishlist ID
    @GetMapping("/wishlist/{wishListId}")
    public List<WishList_ItemsModel> getWishListItemsByWishListId(@PathVariable Long wishListId) {
        return wishListItemsService.getWishListItemsByWishListId(wishListId);
    }

    // Create a new wishlist item
    @PostMapping
    public WishList_ItemsModel createWishListItem(@RequestBody WishList_ItemsModel wishListItem) {
        return wishListItemsService.createWishListItem(wishListItem);
    }

    // Update a wishlist item
    @PutMapping("/{id}")
    public ResponseEntity<WishList_ItemsModel> updateWishListItem(@PathVariable Long id, @RequestBody WishList_ItemsModel wishListItemDetails) {
        try {
            WishList_ItemsModel updatedItem = wishListItemsService.updateWishListItem(id, wishListItemDetails);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a wishlist item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWishListItem(@PathVariable Long id) {
        try {
            wishListItemsService.deleteWishListItem(id);
            return ResponseEntity.ok("Wishlist item deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
