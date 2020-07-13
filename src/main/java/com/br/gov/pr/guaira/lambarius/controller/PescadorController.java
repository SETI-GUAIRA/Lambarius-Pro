package com.br.gov.pr.guaira.lambarius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.gov.pr.guaira.lambarius.domain.Pescador;

@Controller
@RequestMapping("/pescadores")
public class PescadorController {

  @GetMapping("/novo")
  public ModelAndView novo(Pescador pescador) {
    ModelAndView mv = new ModelAndView("pages/Pescadores/CadastroPescador");

    return mv;
  }

  @GetMapping("/lista")
  public ModelAndView listar() {
    ModelAndView mv = new ModelAndView("pages/Pescadores/lista");

    return mv;
  }
}
