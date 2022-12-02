package br.com.zup.edu.marketplace.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.marketplace.model.Usuario;
import br.com.zup.edu.marketplace.repository.UsuarioRepository;

@RestController
public class CadastroUsuarioController {
	
	private final UsuarioRepository usuarioRepository;

	public CadastroUsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@PostMapping("usuarios")
	public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest, 
			UriComponentsBuilder uriComponentsBuilder) {
		
		Usuario destinatario = usuarioRequest.paraUsuario();
		
		usuarioRepository.save(destinatario);
		
		URI location = uriComponentsBuilder.path("usuarios/{id}")
				.buildAndExpand(destinatario.getId()).toUri();
		
		return ResponseEntity.created(location).build();	
	}
	
}
