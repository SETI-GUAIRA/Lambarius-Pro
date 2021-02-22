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
import br.gov.pr.guaira.lambarius.domain.TipoProduto;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;

import br.gov.pr.guaira.lambarius.exception.TipoProdutoExistentException;

import br.gov.pr.guaira.lambarius.repository.TipoProdutoRepository;
import br.gov.pr.guaira.lambarius.repository.filter.TipoProdutoFilter;
import br.gov.pr.guaira.lambarius.service.TipoProdutoService;

@Controller
@RequestMapping("/tipoProdutos")
public class TipoProdutoController {

  @Autowired
  private TipoProdutoService tipoProdutoService;
  @Autowired
  private TipoProdutoRepository tipoProdutoRepository;

  @GetMapping("/novo")
  public ModelAndView novo(TipoProduto tipoProduto) {
    ModelAndView mv = new ModelAndView("pages/TipoProduto/TipoProdutoCadastro");

    return mv;
  }

  @PostMapping(value = {"/novo", "/codigo"})
  public String salvar(@Valid TipoProduto tipoProduto, BindingResult result, RedirectAttributes attr) {
    try {
      if (result.hasErrors()) {
        attr.addFlashAttribute("error", "Preencha todos os campos");
        return "redirect:/tipoProdutos/novo";
      }

      this.tipoProdutoService.salvar(tipoProduto);
      attr.addFlashAttribute("success", "Tipo Produto cadastrado com sucesso.");

      return "redirect:/tipoProdutos/novo";

    } catch (TipoProdutoExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/tipoProdutos/novo";
    }
  }

  @GetMapping
	public ModelAndView pesquisar(TipoProdutoFilter filter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pages/TipoProduto/TipoProdutoLista");
		
		PageWrapper<TipoProduto> paginaWrapper = new PageWrapper<>(this.tipoProdutoRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("tipoProduto", tipoProdutoService.buscarUm(codigo));
    return "pages/TipoProduto/TipoProdutoCadastro";
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    
	  try {
			this.tipoProdutoService.excluir(codigo);	
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
  }
}
