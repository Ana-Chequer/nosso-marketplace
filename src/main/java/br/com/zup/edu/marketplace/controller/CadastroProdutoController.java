package br.com.zup.edu.marketplace.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;

@RestController
public class CadastroProdutoController {
	
	private final ProdutoRepository produtoRepository;

	public CadastroProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@PostMapping("produtos")
	public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest, 
			UriComponentsBuilder uriComponentsBuilder) {
		
		Produto produto = produtoRequest.paraProduto();
		
		produtoRepository.save(produto);
		
		URI location = uriComponentsBuilder.path("produtos/{id}")
				.buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
