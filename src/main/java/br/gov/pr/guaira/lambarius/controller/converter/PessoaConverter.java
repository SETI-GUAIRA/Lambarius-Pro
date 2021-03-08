package br.gov.pr.guaira.lambarius.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.gov.pr.guaira.lambarius.domain.Pessoa;

@Component
public class PessoaConverter implements Converter<String, Pessoa> {
	@Override
	public Pessoa convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Pessoa pessoa = new Pessoa();
			pessoa.setCodigo(Long.valueOf(codigo));
			return pessoa;
		}
		return null;
	}

}
