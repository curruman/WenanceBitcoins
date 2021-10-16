package com.wenance.bitcoins.domain.dto;


import com.wenance.bitcoins.validators.anotation.ConsistentDateParameters;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.SEQUENCE;

@Validated
@Builder
@Getter
@NoArgsConstructor
@Entity
@Table(name="CRIPTO_CURRENCIES")
public class CriptoCurrency extends RepresentationModel<CriptoCurrency> {
    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "Sequence", strategy = SEQUENCE)
    @SequenceGenerator(name = "Sequence", sequenceName = "SEQUENCE_CURR", allocationSize = 1)
    @Id
    private Long id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    @Column(name = "time_created")
    private Timestamp timeCreated;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 9, fraction = 1)
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "cripto")
    private String criptoCurrencyCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @ConsistentDateParameters
    public CriptoCurrency(Long id, Timestamp timeCreated, BigDecimal price, String criptoCurrencyCode, String currencyCode) {
        this.id =id;
        this.timeCreated = timeCreated;
        this.price=price;
        this.criptoCurrencyCode = criptoCurrencyCode;
        this.currencyCode=currencyCode;
    }



}

