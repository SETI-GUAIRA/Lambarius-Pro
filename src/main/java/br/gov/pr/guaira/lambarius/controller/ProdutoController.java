package br.gov.pr.guaira.lambarius.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.pr.guaira.lambarius.controller.page.PageWrapper;
import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.domain.Produto;
import br.gov.pr.guaira.lambarius.domain.Sexo;
import br.gov.pr.guaira.lambarius.domain.TipoProduto;
import br.gov.pr.guaira.lambarius.domain.UnidadesMedidas;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.ProdutoExistentException;

import br.gov.pr.guaira.lambarius.repository.ProdutoRepository;
import br.gov.pr.guaira.lambarius.repository.filter.ProdutoFilter;
import br.gov.pr.guaira.lambarius.service.AssociacaoService;
import br.gov.pr.guaira.lambarius.service.ProdutoService;
import br.gov.pr.guaira.lambarius.service.TipoProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;
  @Autowired
  private ProdutoRepository ProdutoRepository;
  
  @Autowired
  private TipoProdutoService tipoProdutosService;


  @GetMapping("/novo")
  public ModelAndView novo(Produto produto) {
    ModelAndView mv = new ModelAndView("pages/Produtos/ProdutoCadastro");

    return mv;
  }

  @PostMapping(value = {"/novo", "/codigo"})
  public String salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {
    try {
      if (result.hasErrors()) {
        attr.addFlashAttribute("error", "Preencha todos os campos");
        return "redirect:/produtos/novo";
      }

      this.produtoService.salvar(produto);
      attr.addFlashAttribute("success", "Produto cadastrado com sucesso.");

      return "redirect:/produtos/novo";

    } catch (ProdutoExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/produtos/novo";
    }
  }

  @GetMapping
	public ModelAndView pesquisar(ProdutoFilter filter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pages/Produtos/ProdutoLista");
		
		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(this.ProdutoRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("produto", produtoService.buscarUm(codigo));
    return "pages/Produtos/ProdutoCadastro";
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    
	  try {
			this.produtoService.excluir(codigo);	
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
  }
  
  @ModelAttribute("tipoProdutos")
  public List<TipoProduto> listaDeTipoProdutos() {
    return tipoProdutosService.buscarTodos();
  }
  
  @ModelAttribute("unidadesMedidas")
  public UnidadesMedidas[] getNomeUnidadesMedidas() {
    return UnidadesMedidas.values();
  }
}
