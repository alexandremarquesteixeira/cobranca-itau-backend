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

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public String getCgccpf() {
		return cgccpf;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setCgccpf(String cgccpf) {
		this.cgccpf = cgccpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"nome='" + nome + '\'' +
				", cgccpf='" + cgccpf + '\'' +
				'}';
	}
}