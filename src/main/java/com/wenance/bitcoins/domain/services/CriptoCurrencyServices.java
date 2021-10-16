package com.wenance.bitcoins.domain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wenance.bitcoins.input.objects.JsonInputCriptoCurrency;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency;


import java.time.LocalDateTime;

public interface CriptoCurrencyServices {
    JsonOutputCriptoCurrency searchCurrency(LocalDateTime dateFound);
    JsonOutputCriptoCurrency searchCurrencyList(LocalDateTime startDate, LocalDateTime endDate);
    void setLink(JsonOutputCriptoCurrency jsonOutputPrices, LocalDateTime dateFound);
}
