package br.com.cdb.bancodigitaljpa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.cdb.bancodigitaljpa.enums.Status;
import br.com.cdb.bancodigitaljpa.enums.TipoCartao;

public class CartaoResponse {
	
	private Long id;
	private String numCartao;
	private TipoCartao tipoCartao;
	private Status status;
	private Long id_conta;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataVencimento;
	private BigDecimal limite;
	
	//G
	public Long getId() {
		return id;
	}
	public String getNumCartao() {
		return numCartao;
	}
	public TipoCartao getTipoCartao() {
		return tipoCartao;
	}
	public Status getStatus() {
		return status;
	}
	public Long getId_conta() {
		return id_conta;
	}
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
	public BigDecimal getLimite() {
		return limite;
	}

	//C
	public CartaoResponse(Long id, String numCartao, TipoCartao tipoCartao, Status status, Long id_conta,
			LocalDate dataVencimento, BigDecimal limite) {
		this.id = id;
		this.numCartao = numCartao;
		this.tipoCartao = tipoCartao;
		this.status = status;
		this.id_conta = id_conta;
		this.dataVencimento = dataVencimento;
		this.limite = limite;
	}
	
	
	
	
	
	
	

}
