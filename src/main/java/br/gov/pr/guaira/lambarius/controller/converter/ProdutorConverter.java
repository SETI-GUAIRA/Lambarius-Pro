package br.gov.pr.guaira.lambarius.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import br.gov.pr.guaira.lambarius.domain.Produtor;


@Component
public class ProdutorConverter implements Converter<String, Produtor> {
	@Override
	public Produtor convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Produtor produtor = new Produtor();
			produtor.setCodigo(Long.valueOf(codigo));
			return produtor;
		}
		return null;
	}

}
