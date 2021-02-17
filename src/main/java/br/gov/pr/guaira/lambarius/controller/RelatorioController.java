package br.gov.pr.guaira.lambarius.controller;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.domain.Pescador;
import br.gov.pr.guaira.lambarius.service.AssociacaoService;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController { 
	
@Autowired
private AssociacaoService associacaoService;

@Autowired
private Connection connection;

  @GetMapping
  public ModelAndView relatorio(Pescador pescador) {
    ModelAndView mv = new ModelAndView("pages/Relatorios/Relatorio");
    return mv;
  }
  
  @GetMapping("/conn")
  public String myConn(Model model) {
  	model.addAttribute("conn", connection != null ? "Conexão ok!" : "Ops... sem conexão");
      return "pages/Relatorios/Relatorio";
  }
  @ModelAttribute("associacoes")
  public List<Associacao> listaDeAssociacoes() {
    return associacaoService.buscarTodos();
  }
 
}
