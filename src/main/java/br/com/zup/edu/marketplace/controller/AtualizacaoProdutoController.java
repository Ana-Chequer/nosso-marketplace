package br.com.zup.edu.marketplace.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;

@RestController
public class AtualizacaoProdutoController {
	
	private final ProdutoRepository produtoRepository;

	public AtualizacaoProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@PatchMapping("produtos/{id}")
	public ResponseEntity<?> atualizarProduto(@PathVariable Long id,
			@RequestBody @Valid ProdutoRequest produtoRequest) {

		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		produto.atualizarDescricao(produtoRequest.getDescricao());

		produtoRepository.save(produto);
		
		return ResponseEntity.noContent().build();

	}

}
