package com.salac.petstore.service;

import com.salac.petstore.model.CartItem;
import com.salac.petstore.model.Pet;
import com.salac.petstore.model.WishlistItem;
import com.salac.petstore.repository.CartRepository;
import com.salac.petstore.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private PetService petService;

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public CartItem addToCart(Long petId) {
        Pet pet = petService.getPetById(petId);
        if (pet == null) {
            throw new IllegalArgumentException("Pet not found");
        }

        Optional<CartItem> existingCartItem = cartRepository.findByPetId(petId);
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return cartRepository.save(cartItem);
        }

        return cartRepository.save(new CartItem(pet));
    }

    public void removeFromCart(Long petId) {
        cartRepository.deleteByPetId(petId);
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistRepository.findAll();
    }

    public WishlistItem addToWishlist(Long petId) {
        Pet pet = petService.getPetById(petId);
        if (pet == null) {
            throw new IllegalArgumentException("Pet not found");
        }

        Optional<WishlistItem> existingWishlistItem = wishlistRepository.findByPetId(petId);
        if (existingWishlistItem.isPresent()) {
            return existingWishlistItem.get();
        }

        return wishlistRepository.save(new WishlistItem(pet));
    }

    public void removeFromWishlist(Long petId) {
        wishlistRepository.deleteByPetId(petId);
    }
}
