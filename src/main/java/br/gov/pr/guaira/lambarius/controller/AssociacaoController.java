package br.gov.pr.guaira.lambarius.controller;

import javax.validation.Valid;

import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.exception.AssociacaoExistentException;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.service.AssociacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
  public String salvar(@Valid Associacao associacao, BindingResult result, RedirectAttributes attr) {
    try {

      if (result.hasErrors()) {

        attr.addFlashAttribute("error", "Preencha todos os campos");
        return "redirect:/associacoes/novo";
      }

      associacaoService.salvar(associacao);
      attr.addFlashAttribute("success", "Associação cadastrada com sucesso");

      return "redirect:/associacoes/novo";

    } catch (AssociacaoExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/associacoes/novo";
    }

  }

  @GetMapping
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
    try {
      associacaoService.excluir(codigo);
      attr.addFlashAttribute("success", "Associação excluída com sucesso.");

      return "redirect:/associacoes";

    } catch (ImpossivelExcluirEntidadeException exception) {
      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/associacoes";
    }
  }
}
