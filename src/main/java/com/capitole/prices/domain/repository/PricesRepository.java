package com.capitole.prices.domain.repository;

import com.capitole.prices.domain.dto.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;

public interface PricesRepository extends JpaRepository<Price, Serializable> {
    Optional<Price> findByPriceListId(Long priceListId);

    @Query(value= "SELECT Top 1 * FROM Price WHERE ((:dateToFound BETWEEN start_date AND end_date) AND (PRODUCT_ID=:productId) AND (BRAND_ID=:brandId)) order by priority DESC", nativeQuery = true)
    Price findByProductIdAndBrandIdAndDateBetweenStartDateAndEndDate(@Param("dateToFound") Timestamp dateToFound, @Param("productId") Long productId, @Param("brandId") Long brandId);
}

