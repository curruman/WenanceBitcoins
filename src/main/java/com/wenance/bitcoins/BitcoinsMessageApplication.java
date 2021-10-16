package com.wenance.bitcoins;

import com.wenance.bitcoins.api.rest.subordinated.webservice.configuration.ScheduleRequestCurrency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Import(ScheduleRequestCurrency.class)
public class BitcoinsMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinsMessageApplication.class, args);
	}

}
