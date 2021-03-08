package br.gov.pr.guaira.lambarius.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.gov.pr.guaira.lambarius.domain.Pescador;


@Component
public class PescadorConverter implements Converter<String, Pescador> {
	@Override
	public Pescador convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Pescador pescador = new Pescador();
			pescador.setCodigo(Long.valueOf(codigo));
			return pescador;
		}
		return null;
	}

}
