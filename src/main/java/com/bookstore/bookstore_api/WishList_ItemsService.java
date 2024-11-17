package com.bookstore.bookstore_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishList_ItemsService {

    @Autowired
    private WishList_ItemsRepository wishListItemsRepository;

    // Create a new wishlist item
    public WishList_ItemsModel createWishListItem(WishList_ItemsModel wishListItem) {
        return wishListItemsRepository.save(wishListItem);
    }

    // Get all wishlist items
    public List<WishList_ItemsModel> getAllWishListItems() {
        return wishListItemsRepository.findAll();
    }

    // Get wishlist item by ID
    public Optional<WishList_ItemsModel> getWishListItemById(Long id) {
        return wishListItemsRepository.findById(id);
    }

    // Get wishlist items by wishlist ID
    public List<WishList_ItemsModel> getWishListItemsByWishListId(Long wishListId) {
        return wishListItemsRepository.findByWishListId(wishListId);
    }

    // Update a wishlist item
    public WishList_ItemsModel updateWishListItem(Long id, WishList_ItemsModel wishListItemDetails) {
        WishList_ItemsModel wishListItem = wishListItemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist item not found with id: " + id));

        wishListItem.setProductId(wishListItemDetails.getProductId());
        wishListItem.setWishListId(wishListItemDetails.getWishListId());
        return wishListItemsRepository.save(wishListItem);
    }

    // Delete a wishlist item
    public void deleteWishListItem(Long id) {
        WishList_ItemsModel wishListItem = wishListItemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist item not found with id: " + id));
        wishListItemsRepository.delete(wishListItem);
    }
}
