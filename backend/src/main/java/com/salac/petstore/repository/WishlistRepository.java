package com.salac.petstore.repository;

import com.salac.petstore.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {

    Optional<WishlistItem> findByPetId(Long petId);

    void deleteByPetId(Long petId);
}