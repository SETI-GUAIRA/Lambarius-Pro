package com.br.gov.pr.guaira.lambarius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/localidades")
public class LocalidadeController {

  @GetMapping("/novo")
  public ModelAndView novo() {
    ModelAndView mv = new ModelAndView("pages/Localidades/cadastro");

    return mv;
  }

  @GetMapping("/lista")
  public ModelAndView listar() {
    ModelAndView mv = new ModelAndView("pages/Localidades/lista");

    return mv;
  }
}
