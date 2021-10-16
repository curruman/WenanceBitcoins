package com.wenance.bitcoins.api.rest.subordinated.webservice.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.wenance.bitcoins.utils.ConstantsUtils.PATH_SEPARATOR;
import static com.wenance.bitcoins.utils.ConstantsUtils.WENANCE_SERVICE_PATH_TIMEOUT;

@Component
@Getter
@Setter
@NoArgsConstructor
public class BitcoinsServiceConfiguration {

    @Value("${wenance.webservice.external.host}")
    private String serviceHost;

    @Value("${wenance.webservice.external.cripto}")
    private String serviceCripto;

    public String getUrl(){
        return this.getServiceHost() + PATH_SEPARATOR + this.getServiceCripto();
    }

    public String getUrlTimeout() {
        return this.getUrl() + PATH_SEPARATOR + WENANCE_SERVICE_PATH_TIMEOUT;
    }
}
