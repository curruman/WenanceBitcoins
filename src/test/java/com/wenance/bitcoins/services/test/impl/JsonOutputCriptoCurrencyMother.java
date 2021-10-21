package com.wenance.bitcoins.services.test.impl;

import com.wenance.bitcoins.domain.dto.CriptoCurrency;
import com.wenance.bitcoins.enums.ApplicationMessage;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency.Response;
import com.wenance.bitcoins.services.test.JsonOutputServiceTest;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class JsonOutputCriptoCurrencyMother implements JsonOutputServiceTest {

    @Override
    public JsonOutputCriptoCurrency getJsonOutputCriptoCurrency(Long productId, Long brandId, String dateString, String startDate, String endDate, BigDecimal price) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        double tax = 0.21;
        return  JsonOutputCriptoCurrency.builder().criptoCurrency(CriptoCurrency.builder().timeCreated(Timestamp.valueOf(startDate))
                .criptoCurrencyCode("BTC").currencyCode("EUR").build())
                .response(Response.builder().code(ApplicationMessage.SUCCESS.getCode()).message(ApplicationMessage.SUCCESS.getMessage()).strCode(ApplicationMessage.SUCCESS.getStrCode()).build()).build();

    }

    @Override
    public JsonOutputCriptoCurrency getJsonOutputCriptoCurrencyConstants(Long productId, Long brandId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14-10.00.00", formatter);
        double tax = 0.21;
        BigDecimal price=new BigDecimal("35.5");
        return  JsonOutputCriptoCurrency.builder().criptoCurrency(CriptoCurrency.builder().timeCreated(Timestamp.valueOf("2020-06-14 00:00:00.0"))
                        .price(price).criptoCurrencyCode("BTC").currencyCode("EUR").build())
                .response(Response.builder().code(ApplicationMessage.SUCCESS.getCode()).message(ApplicationMessage.SUCCESS.getMessage()).strCode(ApplicationMessage.SUCCESS.getStrCode()).build()).build();

    }
}
