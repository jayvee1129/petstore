package com.salac.petstore.repository;

import com.salac.petstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByPetId(Long petId);

    void deleteByPetId(Long petId);
}