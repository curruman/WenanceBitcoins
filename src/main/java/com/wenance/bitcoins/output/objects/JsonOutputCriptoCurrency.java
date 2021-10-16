package com.wenance.bitcoins.output.objects;

import com.wenance.bitcoins.domain.dto.CriptoCurrency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonOutputCriptoCurrency implements Serializable {
    private static final long serialVersionUID = -6334581547411113963L;

    @Valid
    private CriptoCurrency criptoCurrency;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    private BigDecimal averagePrice;

    private BigDecimal percentageDifference;

    @NotNull
    private Response response;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response implements Serializable {
        private static final long serialVersionUID = 3538641147032958369L;

        @Min(value=0)
        @Digits(integer = 2, fraction =0)
        private Integer code;

        private String message;

        private String strCode;

        private String description;
    }
}
