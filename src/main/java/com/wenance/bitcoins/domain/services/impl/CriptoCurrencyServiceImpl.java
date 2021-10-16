package com.wenance.bitcoins.domain.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenance.bitcoins.api.rest.BitcoinsController;
import com.wenance.bitcoins.api.rest.subordinated.webservice.configuration.ScheduleRequestCurrency;
import com.wenance.bitcoins.config.SelfConfiguration;
import com.wenance.bitcoins.domain.dto.CriptoCurrency;
import com.wenance.bitcoins.domain.repository.CriptoCurrencyRepository;
import com.wenance.bitcoins.domain.services.CriptoCurrencyServices;
import com.wenance.bitcoins.enums.ApplicationMessage;
import com.wenance.bitcoins.exceptions.DateException;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency;
import com.wenance.bitcoins.utils.BigDecimalUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.wenance.bitcoins.utils.ConstantsUtils.FAILED_QUERY;
import static com.wenance.bitcoins.utils.ConstantsUtils.FORMAT_INVALIDATE;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class CriptoCurrencyServiceImpl implements CriptoCurrencyServices {
    private static final Log log = LogFactory.getLog(CriptoCurrencyServiceImpl.class);

    @Autowired
    private final CriptoCurrencyRepository criptoCurrencyRepository;

    @Autowired
    private final SelfConfiguration selfConfiguration;

    @Autowired
    private final ScheduleRequestCurrency scheduleRequestCurrency;

    public JsonOutputCriptoCurrency getBadResponse() {
        JsonOutputCriptoCurrency.Response response = JsonOutputCriptoCurrency.Response.builder().code(ApplicationMessage.UNEXPECTED.getCode()).message(ApplicationMessage.UNEXPECTED.getMessage()).strCode(ApplicationMessage.UNEXPECTED.getStrCode()).build();
        return JsonOutputCriptoCurrency.builder().response(response).build();
    }

    @Override
    public JsonOutputCriptoCurrency searchCurrency(LocalDateTime dateFound) {
        log.info("Found currency to apply in the date: "+dateFound);
        if (isNull(dateFound)) {
            return getBadResponse();
        }
        Timestamp timeDB= Optional.of(Timestamp.valueOf(dateFound)).orElseThrow(()-> new DateException(FORMAT_INVALIDATE));
        CriptoCurrency criptoCurrency= criptoCurrencyRepository.findCurrencyByTimeCreated(timeDB);
        return ofNullable(criptoCurrency).map(c -> JsonOutputCriptoCurrency.builder().criptoCurrency(c)
                        .response(setResponsePrice(ApplicationMessage.SUCCESS, "")).build())
                .orElse(JsonOutputCriptoCurrency.builder().response(setResponsePrice(ApplicationMessage.UNEXPECTED, FAILED_QUERY)).build());
    }

    @Override
    public JsonOutputCriptoCurrency searchCurrencyList(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Found currency to apply in the rango: ["+startDate+" , "+endDate+" ]");
        if (isNull(startDate) || isNull(endDate)) {
            return getBadResponse();
        }
        Timestamp startDateDB= Optional.of(Timestamp.valueOf(startDate)).orElseThrow(()-> new DateException(FORMAT_INVALIDATE));
        Timestamp endDateDB= Optional.of(Timestamp.valueOf(endDate)).orElseThrow(()-> new DateException(FORMAT_INVALIDATE));
        List<CriptoCurrency> criptoCurrencies= criptoCurrencyRepository.findAllBetweenStartDateAndEndDate(startDateDB, endDateDB);

        return ofNullable(criptoCurrencies).map(cc->{
                    List<BigDecimal> listPrice= new ArrayList<>();
                    BigDecimalUtils bigDecimalUtils = new BigDecimalUtils();
                        cc.forEach(currency->listPrice.add(currency.getPrice()));
                        return JsonOutputCriptoCurrency.builder().startDate(startDate).endDate(endDate)
                        .averagePrice(bigDecimalUtils.getAverage(listPrice))
                        .percentageDifference(bigDecimalUtils.getPercentageDifference(listPrice)).response(setResponsePrice(ApplicationMessage.SUCCESS, "")).build();})
                .orElse(JsonOutputCriptoCurrency.builder().response(setResponsePrice(ApplicationMessage.UNEXPECTED, FAILED_QUERY)).build());
    }





   @Override
   @SneakyThrows(JsonProcessingException.class)
    public void setLink(JsonOutputCriptoCurrency jsonOutputPrices, LocalDateTime dateFound){
        if(isNull(jsonOutputPrices) || isNull(jsonOutputPrices.getCriptoCurrency())){
            return;
        }
        jsonOutputPrices.getCriptoCurrency().add(WebMvcLinkBuilder.linkTo(methodOn(BitcoinsController.class).findCurrency(dateFound)).withSelfRel());
    }

    private JsonOutputCriptoCurrency.Response setResponsePrice(ApplicationMessage aplication, String description){
        return JsonOutputCriptoCurrency.Response.builder().
                        code(aplication.getCode())
                        .message(aplication.getMessage())
                        .strCode(aplication.getStrCode())
                        .description(description).build();
    }
}
