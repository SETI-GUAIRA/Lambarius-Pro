package com.br.gov.pr.guaira.lambarius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.validation.Valid;

import com.br.gov.pr.guaira.lambarius.domain.Associacao;
import com.br.gov.pr.guaira.lambarius.domain.Pescador;
import com.br.gov.pr.guaira.lambarius.domain.Porto;
import com.br.gov.pr.guaira.lambarius.exception.PescadorExistentException;
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
    ModelAndView mv = new ModelAndView("layout/pages/Pescadores/PescadorCadastro");

    return mv;
  }

  @GetMapping("/lista")
  public String listar(ModelMap model) {
    model.addAttribute("pescadores", pescadorService.buscarTodos());

    return "layout/pages/Pescadores/PescadorLista";
  }

  @PostMapping("/salvar")
  public String salvar(@Valid Pescador pescador, BindingResult result, RedirectAttributes attr) {
    try {

      if (result.hasErrors()) {
        attr.addFlashAttribute("error", "Preencha todos os campos");
        return "redirect:/pescadores/novo";
      }

      pescadorService.salvar(pescador);
      attr.addFlashAttribute("success", "Pescador cadastrado com sucesso.");
      return "redirect:/pescadores/novo";

    } catch (PescadorExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/pescadores/novo";
    }
  }

  @GetMapping("/editar/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("pescador", pescadorService.buscarUm(codigo));
    return "layout/pages/Pescadores/CadastroPescador";
  }

  @PostMapping("/excluir/{codigo}")
  public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    pescadorService.excluir(codigo);
    attr.addFlashAttribute("success", "Pescador excluído com sucesso.");
    return "redirect:/pescadores/lista";
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
