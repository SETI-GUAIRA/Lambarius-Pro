package com.br.gov.pr.guaira.lambarius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/associacoes")
public class AssociacaoController {

  @GetMapping("/novo")
  public ModelAndView novo() {
    ModelAndView mv = new ModelAndView("pages/Associacoes/cadastro");

    return mv;
  }

  @GetMapping("/lista")
  public ModelAndView listar() {
    ModelAndView mv = new ModelAndView("pages/Associacoes/lista");

    return mv;
  }
}
