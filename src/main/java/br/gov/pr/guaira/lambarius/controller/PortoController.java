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
import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.PortoExistentException;
import br.gov.pr.guaira.lambarius.repository.PortoRepository;
import br.gov.pr.guaira.lambarius.repository.filter.PortoFilter;
import br.gov.pr.guaira.lambarius.service.PortoService;

@Controller
@RequestMapping("/portos")
public class PortoController {

  @Autowired
  private PortoService portoService;
  @Autowired
  private PortoRepository portoRepository;

  @GetMapping("/novo")
  public ModelAndView novo(Porto porto) {
    ModelAndView mv = new ModelAndView("layout/pages/Portos/PortoCadastro");

    return mv;
  }

  @PostMapping(value = {"/novo", "/codigo"})
  public String salvar(@Valid Porto porto, BindingResult result, RedirectAttributes attr) {
    try {
      if (result.hasErrors()) {
        attr.addFlashAttribute("error", "Preencha todos os campos");
        return "redirect:/portos/novo";
      }

      this.portoService.salvar(porto);
      attr.addFlashAttribute("success", "Porto cadastrado com sucesso.");

      return "redirect:/portos/novo";

    } catch (PortoExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return "redirect:/portos/novo";
    }
  }

  @GetMapping
	public ModelAndView pesquisar(PortoFilter filter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("layout/pages/Portos/PortoLista");
		
		PageWrapper<Porto> paginaWrapper = new PageWrapper<>(this.portoRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("porto", portoService.buscarUm(codigo));
    return "layout/pages/Portos/PortoCadastro";
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    
	  try {
			this.portoService.excluir(codigo);	
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
  }
}
