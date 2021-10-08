package com.capitole.prices.services.test;

import com.capitole.prices.output.objects.JsonOutputPrices;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Profile("test")
public interface JsonOutputServiceTest {
    JsonOutputPrices getJsonOutputPrices(Long productId, Long brandId, String dateString, String startDate, String endDate, BigDecimal price);

}
