package br.com.zup.edu.marketplace.controller;

import javax.validation.constraints.NotBlank;

import br.com.zup.edu.marketplace.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String telefone;

	public UsuarioRequest(String nome, String cpf, String endereco, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Usuario paraUsuario() {
		return new Usuario(nome, cpf, endereco, telefone);
		
	}
 	
	

}
