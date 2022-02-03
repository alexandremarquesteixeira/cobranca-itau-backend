package br.com.itau.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private Date dataCadastro;
	private Boolean ativo;
	private String cgccpf;
	private String email;

}