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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.pr.guaira.lambarius.controller.page.PageWrapper;
import br.gov.pr.guaira.lambarius.domain.Produtor;
import br.gov.pr.guaira.lambarius.domain.Sexo;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.ProdutorExistentException;
import br.gov.pr.guaira.lambarius.repository.ProdutorRepository;
import br.gov.pr.guaira.lambarius.repository.filter.ProdutorFilter;
import br.gov.pr.guaira.lambarius.service.ProdutorService;

@Controller
@RequestMapping("/produtores")
public class ProdudorController {

  @Autowired
  private ProdutorService produtorService;
  @Autowired
  private ProdutorRepository produtorRepository; 

  @GetMapping("/novo")
  public ModelAndView novo(Produtor produtor) {
    ModelAndView mv = new ModelAndView("pages/Produtores/ProdutorCadastro");

    return mv;
  }

  @GetMapping
  public ModelAndView pesquisar(ProdutorFilter filter, @PageableDefault(size = 10) Pageable pageable,
      HttpServletRequest httpServletRequest) {
    ModelAndView mv = new ModelAndView("pages/Produtores/ProdutorLista");
    PageWrapper<Produtor> paginaWrapper = new PageWrapper<>(this.produtorRepository.filtrar(filter, pageable),
        httpServletRequest);
    mv.addObject("pagina", paginaWrapper);    
    
    return mv;
  }

  @PostMapping(value = { "/novo", "/{codigo}" })
  public ModelAndView salvar(@Valid Produtor produtor, BindingResult result, RedirectAttributes attr) {
    try {

      if (result.hasErrors()) {
        attr.addFlashAttribute("error", "Erro");
        return novo(produtor);
      }

      produtorService.salvar(produtor);
      attr.addFlashAttribute("success", "Produtor cadastrado com sucesso.");

    } catch (ProdutorExistentException exception) {
      // attr.addFlashAttribute("error", exception.getMessage());
      result.rejectValue("cpfOuCnpj", exception.getMessage(), exception.getMessage());
      return novo(produtor);
    }
    return new ModelAndView("redirect:/produtores/novo");
  }

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("produtor", produtorService.buscarUm(codigo));
    return "pages/Produtores/ProdutorCadastro";
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    try {
      this.produtorService.excluir(codigo);
    } catch (ImpossivelExcluirEntidadeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok().build();
  }   

  @ModelAttribute("sexos")
  public Sexo[] getNomeSexos() {
    return Sexo.values();
  }  
  
}
