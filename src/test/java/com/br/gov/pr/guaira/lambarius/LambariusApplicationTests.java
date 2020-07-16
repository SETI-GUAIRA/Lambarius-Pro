package com.br.gov.pr.guaira.lambarius;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LambariusApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	private void testConverData() {
		
		String date = "1991-08-30";
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/mm/yyyy");
	      LocalDate formattedDate = LocalDate.parse(date, format);
	      
	      System.out.println("formatada " +formattedDate);
	      
	}
}
