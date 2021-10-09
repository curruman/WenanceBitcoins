package com.capitole.prices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class PricesMessageApplicationTests {

	@Test
	void startsApplicationAndLoadsContext() {
		assertDoesNotThrow(()-> PricesMessageApplication.main(new String[]{}));
	}
}
