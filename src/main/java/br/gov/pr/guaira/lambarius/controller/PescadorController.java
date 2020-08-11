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
import br.gov.pr.guaira.lambarius.domain.Ilha;
import br.gov.pr.guaira.lambarius.domain.LocalPesca;
import br.gov.pr.guaira.lambarius.domain.Pescador;
import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.PescadorExistentException;
import br.gov.pr.guaira.lambarius.repository.PescadorRepository;
import br.gov.pr.guaira.lambarius.repository.filter.PescadorFilter;
import br.gov.pr.guaira.lambarius.service.AssociacaoService;
import br.gov.pr.guaira.lambarius.service.IlhaService;
import br.gov.pr.guaira.lambarius.service.PescadorService;
import br.gov.pr.guaira.lambarius.service.PortoService;

@Controller
@RequestMapping("/pescadores")
public class PescadorController {

  @Autowired
  private PescadorService pescadorService;
  @Autowired
  private PescadorRepository pescadorRepository;
  @Autowired
  private AssociacaoService associacaoService;
  @Autowired
  private PortoService portoService;
  @Autowired
  private IlhaService ilhaService;

  @GetMapping("/novo")
  public ModelAndView novo(Pescador pescador) {
    ModelAndView mv = new ModelAndView("pages/Pescadores/PescadorCadastro");

    return mv;
  }

  @GetMapping
 	public ModelAndView pesquisar(PescadorFilter filter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
 		ModelAndView mv = new ModelAndView("pages/Pescadores/PescadorLista");
 		
 		PageWrapper<Pescador> paginaWrapper = new PageWrapper<>(this.pescadorRepository.filtrar(filter, pageable),
 				httpServletRequest);
 		mv.addObject("pagina", paginaWrapper);
 		return mv;
 	}

  @PostMapping(value = {"/novo", "/{codigo}"})
  public ModelAndView salvar(@Valid Pescador pescador, BindingResult result, RedirectAttributes attr) {
    
	  if (result.hasErrors()) {
	        attr.addFlashAttribute("error", "Erro");
	        return novo(pescador);
	   }
	  
	try {

      pescadorService.salvar(pescador);
      attr.addFlashAttribute("success", "Pescador cadastrado com sucesso.");

    } catch (PescadorExistentException exception) {

      attr.addFlashAttribute("error", exception.getMessage());
      return novo(pescador);
    }
    return new ModelAndView("redirect:/pescadores/novo");
  }

  @GetMapping("/{codigo}")
  public String editar(@PathVariable("codigo") Long codigo, ModelMap model) {
    model.addAttribute("pescador", pescadorService.buscarUm(codigo));
    return "pages/Pescadores/PescadorCadastro";
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attr) {
    
	  try {
			this.pescadorService.excluir(codigo);	
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
  }

  @ModelAttribute("associacoes")
  public List<Associacao> listaDeAssociacoes() {
    return associacaoService.buscarTodos();
  }

  @ModelAttribute("locais")
  public LocalPesca[] getLocaisPesca() {
    return LocalPesca.values();
  }

  @ModelAttribute("portos")
  public List<Porto> listaDePortos() {
    return portoService.buscarTodos();
  }

  @ModelAttribute("ilhas")
  public List<Ilha> listaDeIlhas() {
    return ilhaService.buscarTodos();
  }
}
