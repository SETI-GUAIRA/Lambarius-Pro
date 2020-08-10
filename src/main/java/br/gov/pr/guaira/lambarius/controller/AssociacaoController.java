package br.gov.pr.guaira.lambarius.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.pr.guaira.lambarius.controller.page.PageWrapper;
import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.exception.AssociacaoExistentException;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.repository.AssociacaoRepository;
import br.gov.pr.guaira.lambarius.repository.filter.AssociacaoFilter;
import br.gov.pr.guaira.lambarius.service.AssociacaoService;

@Controller
@RequestMapping("/associacoes")
public class AssociacaoController {

  @Autowired
  private AssociacaoService associacaoService;
  @Autowired
  private AssociacaoRepository associacaoRepository;

  @GetMapping("/novo")
  public ModelAndView novo(Associacao associacao) {
    ModelAndView mv = new ModelAndView("layout/pages/Associacoes/AssociacaoCadastro");

    return mv;
  }

  @PostMapping(value = {"/novo", "/codigo"})
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
	public ModelAndView pesquisar(AssociacaoFilter filter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("layout/pages/Associacoes/AssociacaoLista");
		
		PageWrapper<Associacao> paginaWrapper = new PageWrapper<>(this.associacaoRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("associacao", associacaoService.buscarUm(codigo));
    return "/layout/pages/Associacoes/AssociacaoCadastro";
  }
  
  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    
	  try {
			this.associacaoService.excluir(codigo);	
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
  }
}
