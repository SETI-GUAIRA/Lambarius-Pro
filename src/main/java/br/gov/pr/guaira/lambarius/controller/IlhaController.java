package br.gov.pr.guaira.lambarius.controller;

import javax.validation.Valid;

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

import br.gov.pr.guaira.lambarius.domain.Ilha;
import br.gov.pr.guaira.lambarius.exception.IlhaExistentException;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidade;
import br.gov.pr.guaira.lambarius.service.IlhaService;

@Controller
@RequestMapping("/ilhas")
public class IlhaController {

  @Autowired
  private IlhaService ilhaService;

  @GetMapping("/novo")
  public ModelAndView novo(Ilha ilha) {
    ModelAndView mv = new ModelAndView("layout/pages/Ilhas/IlhaCadastro");

    return mv;
  }

  @PostMapping("/salvar")
  public String salvar(@Valid Ilha ilha, BindingResult result, RedirectAttributes attr) {
    try {
      if (result.hasErrors()) {
        attr.addFlashAttribute("error", "Preencha todos os campos");
        return "redirect:/ilhas/novo";
      }

      ilhaService.salvar(ilha);
      attr.addFlashAttribute("success", "Ilha cadastrada com sucesso.");

      return "redirect:/ilhas/novo";

    } catch (IlhaExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/ilhas/novo";
    }
  }

  @GetMapping
  public String listar(ModelMap model) {
    model.addAttribute("ilhas", ilhaService.buscarTodos());
    return "layout/pages/Ilhas/IlhaLista";
  }

  @GetMapping("/editar/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("porto", ilhaService.buscarUm(codigo));
    return "layout/pages/Ilhas/IlhaCadastro";
  }

  @PostMapping("/excluir/{codigo}")
  public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    try {
      ilhaService.excluir(codigo);
      attr.addFlashAttribute("success", "Ilha excluída com sucesso.");

      return "redirect:/ilhas";

    } catch (ImpossivelExcluirEntidade exception) {
      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/ilhas";
    }
  }
}