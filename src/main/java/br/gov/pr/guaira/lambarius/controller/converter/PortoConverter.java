package br.gov.pr.guaira.lambarius.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.gov.pr.guaira.lambarius.domain.Porto;

@Component
public class PortoConverter implements Converter<String, Porto> {

	@Override
	public Porto convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Porto porto = new Porto();
			porto.setCodigo(Long.valueOf(codigo));
			return porto;
		}
		return null;
	}

}
