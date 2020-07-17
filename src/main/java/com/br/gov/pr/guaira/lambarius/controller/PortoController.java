package com.br.gov.pr.guaira.lambarius.controller;

import com.br.gov.pr.guaira.lambarius.domain.Porto;
import com.br.gov.pr.guaira.lambarius.service.PortoService;

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
@RequestMapping("/portos")
public class PortoController {

  @Autowired
  private PortoService portoService;

  @GetMapping("/novo")
  public ModelAndView novo(Porto porto) {
    ModelAndView mv = new ModelAndView("layout/pages/Portos/PortoCadastro");

    return mv;
  }

  @PostMapping("/salvar")
  public String salvar(Porto porto, RedirectAttributes attr) {
    portoService.salvar(porto);
    attr.addFlashAttribute("success", "");

    return "redirect:/portos/novo";
  }

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    model.addAttribute("portos", portoService.buscarTodos());
    return "layout/pages/Portos/PortoLista";
  }

  @GetMapping("/editar/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("porto", portoService.buscarUm(codigo));
    return "layout/pages/Portos/PortoCadastro";
  }

  @PostMapping("/excluir/{codigo}")
  public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    portoService.excluir(codigo);
    attr.addFlashAttribute("success", "");

    return "redirect:/portos/lista";
  }
}
