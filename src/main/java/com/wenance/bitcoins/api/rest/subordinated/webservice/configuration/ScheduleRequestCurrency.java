package com.wenance.bitcoins.api.rest.subordinated.webservice.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenance.bitcoins.api.rest.subordinated.webservice.BitcoinsService;
import com.wenance.bitcoins.domain.dto.CriptoCurrency;
import com.wenance.bitcoins.domain.repository.CriptoCurrencyRepository;
import com.wenance.bitcoins.input.objects.JsonInputCriptoCurrency;
import com.wenance.bitcoins.utils.LocalDateFormatter;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@AllArgsConstructor
public class ScheduleRequestCurrency {
    private static final Log log = LogFactory.getLog(ScheduleRequestCurrency.class);

    @Autowired
    private final BitcoinsService bitcoinsService;

    @Autowired
    private final CriptoCurrencyRepository criptoCurrencyRepository;

    @Scheduled(fixedDelay=10000)
    public CriptoCurrency scheduleFixedDelayTask() throws JsonProcessingException {
        Mono<String> monoInput = bitcoinsService.processCurrency();
        JsonInputCriptoCurrency jsonInputCriptoCurrency = new ObjectMapper().readValue(monoInput.block(), JsonInputCriptoCurrency.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        return criptoCurrencyRepository.save(CriptoCurrency.builder().timeCreated(Timestamp.valueOf(LocalDateFormatter.getDateToFind(LocalDateTime.now().format(formatter))))
                .price(BigDecimal.valueOf(jsonInputCriptoCurrency.getLprice()).setScale(1, RoundingMode.HALF_UP))
                .criptoCurrencyCode(jsonInputCriptoCurrency.getCurr1()).currencyCode(jsonInputCriptoCurrency.getCurr2()).build());

    }
}
