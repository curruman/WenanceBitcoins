package com.wenance.bitcoins.domain.repository;

import com.wenance.bitcoins.domain.dto.CriptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface CriptoCurrencyRepository extends JpaRepository<CriptoCurrency, Serializable> {
    CriptoCurrency findCurrencyByTimeCreated(Timestamp timeCreated);

    @Query(value= "SELECT * FROM CRIPTO_CURRENCIES WHERE (TIME_CREATED BETWEEN :startDate AND :endDate)", nativeQuery = true)
    List<CriptoCurrency> findAllBetweenStartDateAndEndDate(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

}

