package com.wenance.bitcoins.api.rest.subordinated.webservice;

import com.wenance.bitcoins.api.rest.subordinated.webservice.configuration.BitcoinsServiceConfiguration;
import com.wenance.bitcoins.input.objects.JsonInputCriptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BitcoinsService {
    private final WebClient webClient;
    private final BitcoinsServiceConfiguration bitcoinsServiceConfiguration;

    @Autowired
    public BitcoinsService(WebClient webClient, BitcoinsServiceConfiguration bitcoinsServiceConfiguration) {
        this.webClient = webClient;
        this.bitcoinsServiceConfiguration = bitcoinsServiceConfiguration;
    }

    public Mono<String> processCurrency(){
        return webClient.get().uri(bitcoinsServiceConfiguration.getUrl()).accept(MediaType.TEXT_PLAIN).retrieve().bodyToMono(String.class);
    }

    public String processTimeOut(){
        return webClient.get().uri(bitcoinsServiceConfiguration.getUrlTimeout()).retrieve().bodyToMono(String.class).block();
    }
}
