package com.salac.petstore.model;

import java.math.BigDecimal;

public class FilterCriteria {

    private String search;
    private String type;
    private String breed;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minAge;
    private Integer maxAge;
    private Boolean available;

    // Constructors
    public FilterCriteria() {}

    public FilterCriteria(String search, String type, String breed, BigDecimal minPrice, BigDecimal maxPrice, Integer minAge, Integer maxAge, Boolean available) {
        this.search = search;
        this.type = type;
        this.breed = breed;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.available = available;
    }

    // Getters and Setters
    public String getSearch() { return search; }
    public void setSearch(String search) { this.search = search; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public BigDecimal getMinPrice() { return minPrice; }
    public void setMinPrice(BigDecimal minPrice) { this.minPrice = minPrice; }

    public BigDecimal getMaxPrice() { return maxPrice; }
    public void setMaxPrice(BigDecimal maxPrice) { this.maxPrice = maxPrice; }

    public Integer getMinAge() { return minAge; }
    public void setMinAge(Integer minAge) { this.minAge = minAge; }

    public Integer getMaxAge() { return maxAge; }
    public void setMaxAge(Integer maxAge) { this.maxAge = maxAge; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}