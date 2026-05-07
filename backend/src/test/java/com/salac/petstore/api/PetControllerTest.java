package com.salac.petstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salac.petstore.model.Pet;
import com.salac.petstore.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPets() throws Exception {
        Pet pet1 = new Pet("Buddy", "Dog", "Golden Retriever", 2, new BigDecimal("500.00"), "Friendly dog", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80");
        pet1.setId(1L);
        Pet pet2 = new Pet("Whiskers", "Cat", "Siamese", 1, new BigDecimal("300.00"), "Playful cat", "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?auto=format&fit=crop&w=800&q=80");
        pet2.setId(2L);
        List<Pet> pets = Arrays.asList(pet1, pet2);

        when(petService.searchPets(any())).thenReturn(pets);

        mockMvc.perform(get("/salac/pets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Buddy"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Whiskers"));
    }

    @Test
    public void testGetPetById() throws Exception {
        Pet pet = new Pet("Buddy", "Dog", "Golden Retriever", 2, new BigDecimal("500.00"), "Friendly dog", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80");
        pet.setId(1L);

        when(petService.getPetById(1L)).thenReturn(pet);

        mockMvc.perform(get("/salac/pets/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    public void testGetPetByIdNotFound() throws Exception {
        when(petService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/salac/pets/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePet() throws Exception {
        Pet pet = new Pet("Buddy", "Dog", "Golden Retriever", 2, new BigDecimal("500.00"), "Friendly dog", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80");
        pet.setId(1L);

        when(petService.createPet(any())).thenReturn(pet);

        mockMvc.perform(post("/salac/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/salac/pets/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    public void testUpdatePet() throws Exception {
        Pet pet = new Pet("Buddy", "Dog", "Golden Retriever", 2, new BigDecimal("500.00"), "Friendly dog", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80");
        pet.setId(1L);

        when(petService.updatePet(eq(1L), any())).thenReturn(pet);

        mockMvc.perform(put("/salac/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    public void testUpdatePetNotFound() throws Exception {
        Pet pet = new Pet("Buddy", "Dog", "Golden Retriever", 2, new BigDecimal("500.00"), "Friendly dog", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=800&q=80");

        when(petService.updatePet(eq(999L), any())).thenReturn(null);

        mockMvc.perform(put("/salac/pets/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePet() throws Exception {
        when(petService.deletePet(1L)).thenReturn(true);

        mockMvc.perform(delete("/salac/pets/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePetNotFound() throws Exception {
        when(petService.deletePet(999L)).thenReturn(false);

        mockMvc.perform(delete("/salac/pets/999"))
                .andExpect(status().isNotFound());
    }
}