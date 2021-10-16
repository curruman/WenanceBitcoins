package com.wenance.bitcoins.input.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.wenance.bitcoins.domain.dto.CriptoCurrency;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency;
import com.wenance.bitcoins.validators.anotation.ConsistentDateParameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Validated
@Builder
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonInputCriptoCurrency implements Serializable {
    private static final long serialVersionUID = 4338109423942956101L;

    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    private Timestamp dateToFound;

    @NotNull
    private double lprice;

    @NotNull
    private String curr1;

    @NotNull
    private String curr2;

    @ConsistentDateParameters
    public JsonInputCriptoCurrency(Timestamp dateToFound,  double lprice, String curr1,  String curr2) {
        this.dateToFound = dateToFound;
        this.lprice=lprice;
        this.curr1 = curr1;
        this.curr2 = curr2;
    }
}

