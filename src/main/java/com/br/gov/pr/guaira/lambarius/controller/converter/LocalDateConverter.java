package com.br.gov.pr.guaira.lambarius.controller.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

  @Override
  public LocalDate convert(String date) {
    if (!StringUtils.isEmpty(date)) {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/mm/yyyy");
      LocalDate FormattedDate = LocalDate.parse(date, format);

      return FormattedDate;
    }

    return null;
  }
}
