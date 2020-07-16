package com.br.gov.pr.guaira.lambarius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import com.br.gov.pr.guaira.lambarius.domain.Associacao;
import com.br.gov.pr.guaira.lambarius.domain.Pescador;
import com.br.gov.pr.guaira.lambarius.domain.Porto;
import com.br.gov.pr.guaira.lambarius.service.AssociacaoService;
import com.br.gov.pr.guaira.lambarius.service.PescadorService;
import com.br.gov.pr.guaira.lambarius.service.PortoService;

@Controller
@RequestMapping("/pescadores")
public class PescadorController {

  @Autowired
  private PescadorService pescadorService;

  @Autowired
  private AssociacaoService associacaoService;

  @Autowired
  private PortoService portoService;

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

  @GetMapping("/editar/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("pescador", pescadorService.buscarUm(codigo));
    return "pages/Pescadores/CadastroPescador";
  }

  @PostMapping("/excluir/{codigo}")
  public String excluir(@PathVariable("codigo") Long codigo, ModelMap model) {
    pescadorService.excluir(codigo);

    return listar(model);
  }

  @ModelAttribute("associacoes")
  public List<Associacao> listaDeAssociacoes() {
    return associacaoService.buscarTodos();
  }

  @ModelAttribute("portos")
  public List<Porto> listaDePortos() {
    return portoService.buscarTodos();
  }
}
