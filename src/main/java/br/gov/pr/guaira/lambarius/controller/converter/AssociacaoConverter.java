package br.gov.pr.guaira.lambarius.controller.converter;

import br.gov.pr.guaira.lambarius.domain.Associacao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AssociacaoConverter implements Converter<String, Associacao> {

  @Override
  public Associacao convert(String codigo) {
    if (!StringUtils.isEmpty(codigo)) {
      Associacao associacao = new Associacao();
      associacao.setCodigo(Long.valueOf(codigo));
      return associacao;
    }
    return null;
  }

}
