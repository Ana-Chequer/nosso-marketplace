package br.com.zup.edu.marketplace.controller;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.model.StatusProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProdutoRequest {
	
	@NotBlank
    private String titulo;
	
	@NotBlank
    private String descricao;
    
	@NotNull
    private StatusProduto status;
	
	@NotNull
    private BigDecimal preco;

	public ProdutoRequest(String titulo, String descricao, BigDecimal preco) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
	}
    
    public Produto paraProduto() {
    	return new Produto(titulo, descricao, status, preco);
    }

}
