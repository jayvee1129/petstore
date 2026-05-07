package com.salac.petstore.repository;

import com.salac.petstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByAvailableTrue();

    List<Pet> findByTypeAndAvailableTrue(String type);

    List<Pet> findByTypeAndBreedAndAvailableTrue(String type, String breed);

    @Query("SELECT p FROM Pet p WHERE p.available = true AND " +
           "(:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           " LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:type IS NULL OR p.type = :type) AND " +
           "(:breed IS NULL OR p.breed = :breed) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:minAge IS NULL OR p.age >= :minAge) AND " +
           "(:maxAge IS NULL OR p.age <= :maxAge)")
    List<Pet> findPetsWithFilters(@Param("search") String search,
                                  @Param("type") String type,
                                  @Param("breed") String breed,
                                  @Param("minPrice") BigDecimal minPrice,
                                  @Param("maxPrice") BigDecimal maxPrice,
                                  @Param("minAge") Integer minAge,
                                  @Param("maxAge") Integer maxAge);
}