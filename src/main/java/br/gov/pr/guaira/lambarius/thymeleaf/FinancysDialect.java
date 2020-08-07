package br.gov.pr.guaira.lambarius.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.gov.pr.guaira.lambarius.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.gov.pr.guaira.lambarius.thymeleaf.processor.OrderElementTagProcessor;
import br.gov.pr.guaira.lambarius.thymeleaf.processor.PaginationElementTagProcessor;

@Component
public class FinancysDialect extends AbstractProcessorDialect{

	public FinancysDialect() {
		super("SETI LambariusPro", "lambarius", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		return processadores;
	}
}
