package br.com.zup.edu.marketplace.model;

import javax.persistence.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Produto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusProduto status = StatusProduto.PENDENTE;

    @Column(nullable = false)
    private BigDecimal preco;
    
    @JoinTable(name = "produto_notaFiscal",
    		joinColumns = @JoinColumn(name = "produto_id"),
    		inverseJoinColumns = @JoinColumn(name = "notaFiscal_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<NotaFiscal> notasFiscais = new ArrayList<>();

    public Produto(String titulo, String descricao, StatusProduto status, BigDecimal preco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.preco = preco;
    }
    
	public void atualizarDescricao(String novaDescricao) {

		if (this.getStatus().equals(StatusProduto.PENDENTE)) {
			this.setDescricao(novaDescricao);

		} else
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
