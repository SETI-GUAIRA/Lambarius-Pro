package br.gov.pr.guaira.lambarius.repository.helper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Usuario;
import br.gov.pr.guaira.lambarius.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	public List<String> permissoes(Usuario usuario);	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	public Usuario buscarComGrupos(Long codigo);
}
