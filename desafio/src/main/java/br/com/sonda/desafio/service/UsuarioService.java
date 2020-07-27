package br.com.sonda.desafio.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sonda.desafio.entity.Usuario;
import br.com.sonda.desafio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public Usuario buscaUsuario(Integer usu) {
		Optional<Usuario> usuario = repo.findById(usu);
		return usuario.orElse(null);
	}

	public List<Usuario> ListarTodos(){
		return repo.findAll();
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		return repo.save(usuario);
	}

	public void deletarUsuario(Integer id) {
		repo.deleteById(id);
	}

	public Usuario alterarUsuario(Usuario usuario) throws IllegalAccessException, InvocationTargetException {
		Optional<Usuario> usu = repo.findById(usuario.getId());

		if (usu == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(usuario, usu);

		return repo.save(usuario);
	}
}
