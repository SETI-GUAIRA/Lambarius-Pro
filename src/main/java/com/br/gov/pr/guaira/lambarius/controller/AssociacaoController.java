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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/associacoes")
public class AssociacaoController {

  @Autowired
  private AssociacaoService associacaoService;

  @GetMapping("/novo")
  public ModelAndView novo(Associacao associacao) {
    ModelAndView mv = new ModelAndView("layout/pages/Associacoes/AssociacaoCadastro");

    return mv;
  }

  @PostMapping("/salvar")
  public String salvar(Associacao associacao, RedirectAttributes attr) {
    associacaoService.salvar(associacao);
    attr.addFlashAttribute("success", "");

    return "redirect:/associacoes/novo";
  }

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    model.addAttribute("associacoes", associacaoService.buscarTodos());
    return "layout/pages/Associacoes/AssociacaoLista";
  }

  @GetMapping("/editar/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("associacao", associacaoService.buscarUm(codigo));
    return "/layout/pages/Associacoes/AssociacaoCadastro";
  }

  @PostMapping("/excluir/{codigo}")
  public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    associacaoService.excluir(codigo);
    attr.addFlashAttribute("success", "");

    return "redirect:/associacoes/lista";
  }
}
