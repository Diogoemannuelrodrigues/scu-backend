package br.com.sonda.desafio.resource;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sonda.desafio.entity.Usuario;
import br.com.sonda.desafio.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> encontrarUsuario(@PathVariable Integer id) {
		Usuario usuarioId = usuService.buscaUsuario(id);
		return ResponseEntity.ok().body(usuarioId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario newCliente = usuService.salvarUsuario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
//		return ResponseEntity.created(uri).build();
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> listarUsuarios(Usuario usuario) {
		List<Usuario> usuarios = usuService.ListarTodos();
		return usuarios;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> alterarUsuario(@Valid @RequestBody Usuario usuario) throws IllegalAccessException, InvocationTargetException {
		Usuario Usuarionew = usuService.alterarUsuario(usuario);
		return ResponseEntity.ok().body(Usuarionew);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deletarProduto(@PathVariable Integer id) {
		usuService.deletarUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
