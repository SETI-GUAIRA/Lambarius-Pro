package br.gov.pr.guaira.lambarius.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.gov.pr.guaira.lambarius.domain.Ilha;

@Component
public class IlhaConverter implements Converter<String, Ilha> {

  @Override
  public Ilha convert(String codigo) {
    if (!StringUtils.isEmpty(codigo)) {
      Ilha ilha = new Ilha();
      ilha.setCodigo(Long.valueOf(codigo));
      return ilha;
    }
    return null;
  }

}
