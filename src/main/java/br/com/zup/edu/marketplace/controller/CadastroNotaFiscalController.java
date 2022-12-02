package br.com.zup.edu.marketplace.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.marketplace.model.NotaFiscal;
import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.model.Usuario;
import br.com.zup.edu.marketplace.repository.NotaFiscalRepository;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;
import br.com.zup.edu.marketplace.repository.UsuarioRepository;

@RestController
public class CadastroNotaFiscalController {
	
	private final ProdutoRepository produtoRepository;
	private final UsuarioRepository usuarioRepository;
	private final NotaFiscalRepository notaFiscalRepository;

	public CadastroNotaFiscalController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, 
			NotaFiscalRepository notaFiscalRepository) {
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
		this.notaFiscalRepository = notaFiscalRepository;
	}
	
	@PostMapping("notasfiscais")
	public ResponseEntity<?> cadastrarNotaFiscal(@RequestBody @Valid NotaFiscalRequest notaFiscalRequest, 
			UriComponentsBuilder uriComponentsBuilder) {
				
		Usuario destinatario = usuarioRepository.findById(notaFiscalRequest.getIdUsuario())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
				
		List<Produto> listaProdutos = new ArrayList<>();
		
		for (Long  idproduto : notaFiscalRequest.getIdProdutos()) {
			Produto produto = produtoRepository.findById(idproduto)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
			
			listaProdutos.add(produto);
		}
		
		NotaFiscal nota = new NotaFiscal(destinatario, listaProdutos);
		
		notaFiscalRepository.save(nota);
		
		URI location = uriComponentsBuilder.path("notasfiscais/{id}")
				.buildAndExpand(nota.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
