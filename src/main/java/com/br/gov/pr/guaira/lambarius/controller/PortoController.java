package com.br.gov.pr.guaira.lambarius.controller;

import com.br.gov.pr.guaira.lambarius.domain.Porto;
import com.br.gov.pr.guaira.lambarius.service.PortoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/portos")
public class PortoController {

  @Autowired
  private PortoService portoService;

  @GetMapping("/novo")
  public ModelAndView novo() {
    ModelAndView mv = new ModelAndView("pages/Portos/cadastro");

    return mv;
  }

  @GetMapping("/lista")
  public ModelAndView listar() {
    ModelAndView mv = new ModelAndView("pages/Portos/lista");

    return mv;
  }

  @PostMapping("/salvar")
  public String salvar(Porto porto) {
    portoService.salvar(porto);
    return "redirect:/portos/novo";
  }
}
