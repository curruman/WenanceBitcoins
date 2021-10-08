package com.capitole.prices.domain.services;

import com.capitole.prices.output.objects.JsonOutputPrices;


import java.time.LocalDateTime;

public interface PricesServices {
    JsonOutputPrices searchPrice(LocalDateTime dateFound, Long productId, Long brandId);
    void setLink(JsonOutputPrices jsonOutputPrices, Long brandId, Long productId, LocalDateTime dateFound);
    LocalDateTime getDateToFind(String date);
}
