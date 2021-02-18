package br.gov.pr.guaira.lambarius.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.domain.TipoProduto;

@Component
public class TipoProdutoConverter implements Converter<String, TipoProduto> {

	@Override
	public TipoProduto convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			TipoProduto tipoProduto = new TipoProduto();
			tipoProduto.setCodigo(Long.valueOf(codigo));
			return tipoProduto;
		}
		return null;
	}

}
