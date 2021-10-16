package com.wenance.bitcoins.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wenance.bitcoins.domain.services.CriptoCurrencyServices;
import com.wenance.bitcoins.enums.ApplicationMessage;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency;
import com.wenance.bitcoins.validators.anotation.ConsistentDateParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.wenance.bitcoins.utils.ConstantsUtils.*;
import static java.util.Optional.ofNullable;

@RestController
@Validated
@RequestMapping(PATH_SEPARATOR + CURRENCIES + PATH_SEPARATOR + API_VERSION)
@Api(value = "/api-cripto-currency")
public class BitcoinsController {
    private static final Log log = LogFactory.getLog(BitcoinsController.class);
    private final static Map<String, HttpStatus> STATUS_MAP = new HashMap<>();

    static {
        STATUS_MAP.put(ApplicationMessage.INVALIDATE_PARAMETERS.getStrCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        STATUS_MAP.put(ApplicationMessage.UNEXPECTED.getStrCode(), HttpStatus.BAD_REQUEST);
        STATUS_MAP.put(ApplicationMessage.SUCCESS.getStrCode(), HttpStatus.OK);
    }

    private final CriptoCurrencyServices criptoCurrencyServices;

    public BitcoinsController(CriptoCurrencyServices criptoCurrencyServices) {
        this.criptoCurrencyServices = criptoCurrencyServices;
    }

    @ApiOperation(value = "Find price by date valid, productId and brandId in the system")
    @ApiResponses( {@ApiResponse(code = 200, message = "Create price valid in the system")})
    @GetMapping(path = PATH_SEPARATOR + FIND_CURRENCY,
           produces = MediaType.APPLICATION_JSON_VALUE)
    @ConsistentDateParameters
    public ResponseEntity<JsonOutputCriptoCurrency> findCurrency(@Valid @RequestParam(name = "dateFound", required = true) LocalDateTime dateFound) throws JsonProcessingException {
        log.info("Find cripto currency valid in the time: "+ dateFound);
        JsonOutputCriptoCurrency jsonCriptoCurrency=criptoCurrencyServices.searchCurrency(dateFound);
        criptoCurrencyServices.setLink(jsonCriptoCurrency, dateFound);
        return ResponseEntity.status(getHttpStatusFromResponseCode(jsonCriptoCurrency.getResponse().getStrCode())).body(jsonCriptoCurrency);
    }

    @ApiOperation(value = "Find currencies list in the system for range of dates")
    @ApiResponses( {@ApiResponse(code = 200, message = "Find currencies valid in the system")})
    @GetMapping(path = PATH_SEPARATOR + FIND_CURRENCIES,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ConsistentDateParameters
    public ResponseEntity<JsonOutputCriptoCurrency> findCurrencyByRange(@Valid @RequestParam(name = "startDate", required = true) LocalDateTime startDate,
                                                                        @Valid @RequestParam(name = "endDate", required = true) LocalDateTime endDate) throws JsonProcessingException {
        log.info("Find cripto currency valid in the range of time from: "+ startDate + " to: "+ endDate);
        JsonOutputCriptoCurrency jsonCriptoCurrency=criptoCurrencyServices.searchCurrencyList(startDate, endDate);
        return ResponseEntity.status(getHttpStatusFromResponseCode(jsonCriptoCurrency.getResponse().getStrCode())).body(jsonCriptoCurrency);
    }

    private HttpStatus getHttpStatusFromResponseCode(String responseCode){
        log.info("Get HttpStatus of response in BitcoinsController, responseCode: "+responseCode);
        return ofNullable(STATUS_MAP.get(responseCode)).orElse(HttpStatus.OK);
    }
}
