package com.wenance.bitcoins.services.test;

import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Profile("test")
public interface JsonOutputServiceTest {
    JsonOutputCriptoCurrency getJsonOutputCriptoCurrency(Long productId, Long brandId, String dateString, String startDate, String endDate, BigDecimal price);
    JsonOutputCriptoCurrency getJsonOutputCriptoCurrencyConstants(Long productId, Long brandId);

}
