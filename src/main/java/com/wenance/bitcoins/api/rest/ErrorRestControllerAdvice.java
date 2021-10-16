package com.wenance.bitcoins.api.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;

import com.wenance.bitcoins.enums.ApplicationMessage;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency;
import com.wenance.bitcoins.output.objects.JsonOutputCriptoCurrency.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations=RestController.class)
public class ErrorRestControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorRestControllerAdvice.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonOutputCriptoCurrency handleException(Exception ex, HttpServletRequest request) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        logger.info("executing exception handler (REST): ");
        logger.error(sw.toString());
        return JsonOutputCriptoCurrency.builder().response(Response.builder().code(ApplicationMessage.UNEXPECTED.getCode())
                .message(ApplicationMessage.UNEXPECTED.getMessage()).description(ex.getMessage()).build()).build();

    }

}