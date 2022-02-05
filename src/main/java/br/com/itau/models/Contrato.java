package br.com.itau.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contrato")
@Data
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Contrato;

	private Long cd_Produto;

	@ManyToOne
	@JoinColumn(name = "cd_Cli")
	private Cliente cliente;

	private Double vlr_Contrato;
	private Integer qtd_Parcelas;
	private String status_Cobranca;
	private String sit_Serasa;

}