package br.gov.pr.guaira.lambarius.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.pr.guaira.lambarius.service.JasperService;

@Controller
@RequestMapping("/relatorios")
public class JasperController {

	@Autowired
	private JasperService service;

	@GetMapping("/pdf/jr1")
	public void exibirRelatorio01(@RequestParam("code") String code, @RequestParam("acao") String acao,
			HttpServletResponse response) throws IOException {
		byte[] bytes = service.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if (acao.equals("V")) {
			response.setHeader("Content-disposition", "inline;filename=relatorio-" + code + ".pdf");
		} else {
			response.setHeader("Content-disposition", "attachment;filename=relatorio-" + code + ".pdf");
		}
		response.getOutputStream().write(bytes);
	}

	@GetMapping("/pdf/jr2/{code}")
	public void exibirRelatorio02(@PathVariable("code") String code,
			@RequestParam(name = "associacao", required = false) Integer associacao, HttpServletResponse response)
			throws IOException {
		service.addParams("Cod_Associacao", associacao == 0 ? null : associacao);

		byte[] bytes = service.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "inline;filename=relatorio-" + code + ".pdf");
		response.getOutputStream().write(bytes);
	}

	@GetMapping("/pdf/jr3/{code}/{pescador}")
	public void exibirRelatorio03(@PathVariable("code") String code, @PathVariable("pescador") Integer pescador,
			HttpServletResponse response) throws IOException {
		service.addParams("Cod_Pescador", pescador == 0 ? null : pescador);

		byte[] bytes = service.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "inline;filename=relatorio-" + code + ".pdf");
		response.getOutputStream().write(bytes);
	}

}
