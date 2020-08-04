package br.gov.pr.guaira.lambarius.controller.converter;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class LocalDateToStringConverter implements Converter<LocalDate, String> {

  @Override
  public String convert(LocalDate date) {
    if (!StringUtils.isEmpty(date)) {
      String stringDate = String.valueOf(date);
      return stringDate;
    }

    return null;
  }
}
