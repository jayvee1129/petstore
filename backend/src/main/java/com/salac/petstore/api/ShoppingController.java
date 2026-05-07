package com.salac.petstore.api;

import com.salac.petstore.model.CartItem;
import com.salac.petstore.model.WishlistItem;
import com.salac.petstore.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salac")
@CrossOrigin(origins = "*")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/cart")
    public ResponseEntity<List<CartItem>> getCart() {
        return ResponseEntity.ok(shoppingService.getCartItems());
    }

    @PostMapping("/cart")
    public ResponseEntity<CartItem> addToCart(@RequestBody PetActionRequest request) {
        if (request == null || request.getPetId() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            CartItem cartItem = shoppingService.addToCart(request.getPetId());
            return ResponseEntity.ok(cartItem);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cart/{petId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long petId) {
        shoppingService.removeFromCart(petId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<WishlistItem>> getWishlist() {
        return ResponseEntity.ok(shoppingService.getWishlistItems());
    }

    @PostMapping("/wishlist")
    public ResponseEntity<WishlistItem> addToWishlist(@RequestBody PetActionRequest request) {
        if (request == null || request.getPetId() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            WishlistItem wishlistItem = shoppingService.addToWishlist(request.getPetId());
            return ResponseEntity.ok(wishlistItem);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/wishlist/{petId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long petId) {
        shoppingService.removeFromWishlist(petId);
        return ResponseEntity.noContent().build();
    }

    public static class PetActionRequest {
        private Long petId;

        public Long getPetId() {
            return petId;
        }

        public void setPetId(Long petId) {
            this.petId = petId;
        }
    }
}
