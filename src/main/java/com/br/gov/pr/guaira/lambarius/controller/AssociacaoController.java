package com.br.gov.pr.guaira.lambarius.controller;

import com.br.gov.pr.guaira.lambarius.domain.Associacao;
import com.br.gov.pr.guaira.lambarius.service.AssociacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/associacoes")
public class AssociacaoController {

  @Autowired
  private AssociacaoService associacaoService;

  @GetMapping("/novo")
  public ModelAndView novo(Associacao associacao) {
    ModelAndView mv = new ModelAndView("pages/Associacoes/AssociacaoCadastro");

    return mv;
  }

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    model.addAttribute("associacoes", associacaoService.buscarTodos());
    return "/pages/Associacoes/AssociacaoLista";
  }

  @PostMapping("/salvar")
  public String salvar(Associacao associacao) {
    associacaoService.salvar(associacao);
    return "redirect:/associacoes/novo";
  }

  @PostMapping("/excluir/{codigo}")
  public String excluir(@PathVariable("codigo") Long codigo, ModelMap model) {
    associacaoService.excluir(codigo);

    return listar(model);
  }
}
