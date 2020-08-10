package br.gov.pr.guaira.lambarius.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.pr.guaira.lambarius.controller.page.PageWrapper;
import br.gov.pr.guaira.lambarius.domain.Ilha;
import br.gov.pr.guaira.lambarius.exception.IlhaExistentException;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.repository.IlhaRepository;
import br.gov.pr.guaira.lambarius.repository.filter.IlhaFilter;
import br.gov.pr.guaira.lambarius.service.IlhaService;

@Controller
@RequestMapping("/ilhas")
public class IlhaController {

  @Autowired
  private IlhaService ilhaService;
  @Autowired
  private IlhaRepository ilhaRepository;

  @GetMapping("/novo")
  public ModelAndView novo(Ilha ilha) {
    ModelAndView mv = new ModelAndView("layout/pages/Ilhas/IlhaCadastro");

    return mv;
  }

  @PostMapping(value = {"/novo", "/codigo"})
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
  
  @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvarRapido(@RequestBody @Valid Ilha ilha, BindingResult result, RedirectAttributes attr) {

	  if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
	  
		try {
			ilha = ilhaService.salvar(ilha);
			attr.addFlashAttribute("success", "Ilha cadastrada com sucesso.");
		}catch (IlhaExistentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok(ilha);
	}

  @GetMapping
	public ModelAndView pesquisar(IlhaFilter filter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("layout/pages/Ilhas/IlhaLista");
		
		PageWrapper<Ilha> paginaWrapper = new PageWrapper<>(this.ilhaRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("ilha", ilhaService.buscarUm(codigo));
    return "layout/pages/Ilhas/IlhaCadastro";
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    
	  try {
			this.ilhaService.excluir(codigo);	
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
  }
}
