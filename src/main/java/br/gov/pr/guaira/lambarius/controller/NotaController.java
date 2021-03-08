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
import br.gov.pr.guaira.lambarius.domain.Nota;
import br.gov.pr.guaira.lambarius.domain.Pescador;
import br.gov.pr.guaira.lambarius.domain.Produto;
import br.gov.pr.guaira.lambarius.domain.Produtor;
import br.gov.pr.guaira.lambarius.domain.TipoNota;
import br.gov.pr.guaira.lambarius.domain.TipoProduto;
import br.gov.pr.guaira.lambarius.domain.UnidadesMedidas;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.NotaExistentException;
import br.gov.pr.guaira.lambarius.repository.NotaRepository;
import br.gov.pr.guaira.lambarius.repository.filter.NotaFilter;
import br.gov.pr.guaira.lambarius.service.NotaService;
import br.gov.pr.guaira.lambarius.service.PescadorService;
import br.gov.pr.guaira.lambarius.service.ProdutoService;
import br.gov.pr.guaira.lambarius.service.ProdutorService;

@Controller
@RequestMapping("/notafiscal")
public class NotaController {

  @Autowired
  private NotaService notaService;
  @Autowired
  private NotaRepository notaRepository;
  @Autowired 
  private PescadorService pescadorService;
  @Autowired 
  private ProdutorService produtorService;
  @Autowired 
  private ProdutoService produtoService;

  @GetMapping("/novo")
  public ModelAndView novo(Nota nota) {
    ModelAndView mv = new ModelAndView("pages/NotaFiscal/NotaCadastro");
    return mv;
  }

  @PostMapping(value = {"/novo", "/codigo"})
  public ModelAndView salvar(@Valid Nota nota, BindingResult result, RedirectAttributes attr) {
    try {
     // if (result.hasErrors()) {
     //   attr.addFlashAttribute("error", "Preencha todos os campos");
      //  return "redirect:/notas/novo";
     // }
    	if (result.hasErrors()) {    
    		  return novo(nota);
    	       // return "notafiscal/novo";
    	  }

      this.notaService.salvar(nota);
      attr.addFlashAttribute("success", "Nota cadastrado com sucesso.");
      return new ModelAndView("redirect:/notas/novo");
     // return "redirect:/notas/novo";

    } catch (NotaExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return new ModelAndView("redirect:/notas/novo");
      //return "redirect:/notas/novo";
    }
  }

  @GetMapping
	public ModelAndView pesquisar(NotaFilter filter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pages/NotaFiscal/NotaLista");
		
		PageWrapper<Nota> paginaWrapper = new PageWrapper<>(this.notaRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("nota", notaService.buscarUm(codigo));
    return "pages/NotaFical/NotaCadastro";
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    
	  try {
			this.notaService.excluir(codigo);	
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
  }
  
  @ModelAttribute("pescador")
  public List<Pescador> listaPescadores() {
    return pescadorService.buscarTodos();
  }
  @ModelAttribute("produtor")
  public List<Produtor> listaProdutores() {
    return produtorService.buscarTodos();
  }
  @ModelAttribute("produto")
  public List<Produto> listaProduto() {
    return produtoService.buscarTodos();
  }
  @ModelAttribute("tipoNota")
  public TipoNota[] getNomeTipoNota() {
    return TipoNota.values();
  }
}
