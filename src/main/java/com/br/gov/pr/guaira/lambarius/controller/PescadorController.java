package com.br.gov.pr.guaira.lambarius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.gov.pr.guaira.lambarius.domain.Pescador;
import com.br.gov.pr.guaira.lambarius.service.PescadorService;

@Controller
@RequestMapping("/pescadores")
public class PescadorController {

  @Autowired
  private PescadorService pescadorService;

  @GetMapping("/novo")
  public ModelAndView novo(Pescador pescador) {
    ModelAndView mv = new ModelAndView("pages/Pescadores/CadastroPescador");

    return mv;
  }

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    model.addAttribute("pescadores", pescadorService.buscarTodos());
    return "pages/Pescadores/PescadorLista";
  }

  @PostMapping("/salvar")
  public String salvar(Pescador pescador) {
    pescadorService.salvar(pescador);
    return "redirect:/pescadores/novo";
  }

  @PostMapping("/excluir/{codigo}")
  public String excluir(@PathVariable("codigo") Long codigo, ModelMap model) {
    pescadorService.excluir(codigo);

    return listar(model);
  }
}
