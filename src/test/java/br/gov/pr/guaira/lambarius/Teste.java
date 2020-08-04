package br.gov.pr.guaira.lambarius;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Teste {

	public static void main(String[] args) {

String date = "1991-08-30";

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      LocalDate formattedDate = LocalDate.parse(date, format);

	      System.out.println("formatada " +formattedDate);
	}

}
