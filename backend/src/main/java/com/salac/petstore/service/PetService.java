package com.salac.petstore.service;

import com.salac.petstore.model.FilterCriteria;
import com.salac.petstore.model.Pet;
import com.salac.petstore.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PetService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findByAvailableTrue();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public List<Pet> searchPets(FilterCriteria criteria) {
        if (criteria == null || (
                criteria.getSearch() == null &&
                criteria.getType() == null &&
                criteria.getBreed() == null &&
                criteria.getMinPrice() == null &&
                criteria.getMaxPrice() == null &&
                criteria.getMinAge() == null &&
                criteria.getMaxAge() == null
        )) {
            log.debug("No filter criteria provided, returning all available pets");
            return petRepository.findByAvailableTrue();
        }

        return petRepository.findPetsWithFilters(
                criteria.getSearch(),
                criteria.getType(),
                criteria.getBreed(),
                criteria.getMinPrice(),
                criteria.getMaxPrice(),
                criteria.getMinAge(),
                criteria.getMaxAge()
        );
    }

    public Pet createPet(Pet pet) {
        if (pet.getAvailable() == null) {
            pet.setAvailable(true);
        }
        if (pet.getCreatedAt() == null) {
            pet.setCreatedAt(LocalDateTime.now());
        }
        pet.setUpdatedAt(LocalDateTime.now());
        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, Pet pet) {
        return petRepository.findById(id).map(existing -> {
            existing.setName(pet.getName());
            existing.setType(pet.getType());
            existing.setBreed(pet.getBreed());
            existing.setAge(pet.getAge());
            existing.setPrice(pet.getPrice());
            existing.setDescription(pet.getDescription());
            existing.setImageUrl(pet.getImageUrl());
            existing.setAvailable(pet.getAvailable() != null ? pet.getAvailable() : existing.getAvailable());
            existing.setUpdatedAt(LocalDateTime.now());
            return petRepository.save(existing);
        }).orElse(null);
    }

    public boolean deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            return false;
        }
        petRepository.deleteById(id);
        return true;
    }
}