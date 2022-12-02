package br.com.zup.edu.marketplace.controller;

import java.util.List;

import br.com.zup.edu.marketplace.model.NotaFiscal;
import br.com.zup.edu.marketplace.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotaFiscalRequest {
	
	private Long idUsuario;
	private List<Long> idProdutos;

	public NotaFiscalRequest(Long idUsuario, List<Long> idProdutos) {
		this.idUsuario = idUsuario;
		this.idProdutos = idProdutos;
	}

}
