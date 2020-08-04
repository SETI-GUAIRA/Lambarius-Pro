package br.gov.pr.guaira.lambarius.controller.converter;

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
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate formattedDate = LocalDate.parse(date, format);

      return formattedDate;
    }

    return null;
  }
}
