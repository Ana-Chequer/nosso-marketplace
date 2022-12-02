package br.com.zup.edu.marketplace.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class NotaFiscal {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario destinatario;

    @ManyToMany(mappedBy = "notasFiscais")
    private List<Produto> itens = new ArrayList<>();

    public NotaFiscal(Usuario destinatario, List<Produto> itens) {
        this.destinatario = destinatario;
        this.itens = itens;
    }
  
}
