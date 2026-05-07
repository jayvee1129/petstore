package com.salac.petstore.api;

import com.salac.petstore.model.CartItem;
import com.salac.petstore.model.Pet;
import com.salac.petstore.model.WishlistItem;
import com.salac.petstore.service.ShoppingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShoppingController.class)
public class ShoppingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingService shoppingService;

    @Test
    public void testAddToCart() throws Exception {
        Pet pet = new Pet("Buddy", "Dog", "Golden Retriever", 2, new BigDecimal("500.00"), "Friendly dog", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80");
        pet.setId(1L);
        CartItem cartItem = new CartItem(pet);
        cartItem.setId(1L);

        when(shoppingService.addToCart(anyLong())).thenReturn(cartItem);

        mockMvc.perform(post("/salac/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"petId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pet.id").value(1));
    }

    @Test
    public void testGetCart() throws Exception {
        Pet pet = new Pet("Buddy", "Dog", "Golden Retriever", 2, new BigDecimal("500.00"), "Friendly dog", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80");
        pet.setId(1L);
        CartItem cartItem = new CartItem(pet);
        cartItem.setId(1L);

        when(shoppingService.getCartItems()).thenReturn(List.of(cartItem));

        mockMvc.perform(get("/salac/cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].pet.id").value(1));
    }

    @Test
    public void testAddToWishlist() throws Exception {
        Pet pet = new Pet("Whiskers", "Cat", "Siamese", 1, new BigDecimal("300.00"), "Playful cat", "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?auto=format&fit=crop&w=800&q=80");
        pet.setId(2L);
        WishlistItem wishlistItem = new WishlistItem(pet);
        wishlistItem.setId(1L);

        when(shoppingService.addToWishlist(anyLong())).thenReturn(wishlistItem);

        mockMvc.perform(post("/salac/wishlist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"petId\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pet.id").value(2));
    }

    @Test
    public void testGetWishlist() throws Exception {
        Pet pet = new Pet("Whiskers", "Cat", "Siamese", 1, new BigDecimal("300.00"), "Playful cat", "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?auto=format&fit=crop&w=800&q=80");
        pet.setId(2L);
        WishlistItem wishlistItem = new WishlistItem(pet);
        wishlistItem.setId(1L);

        when(shoppingService.getWishlistItems()).thenReturn(List.of(wishlistItem));

        mockMvc.perform(get("/salac/wishlist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].pet.id").value(2));
    }

    @Test
    public void testRemoveFromCart() throws Exception {
        mockMvc.perform(delete("/salac/cart/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testRemoveFromWishlist() throws Exception {
        mockMvc.perform(delete("/salac/wishlist/2"))
                .andExpect(status().isNoContent());
    }
}
