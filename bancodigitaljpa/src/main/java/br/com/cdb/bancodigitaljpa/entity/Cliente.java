package br.com.cdb.bancodigitaljpa.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.cdb.bancodigitaljpa.enums.CategoriaCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
	
	//atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min=2, max=100, message="Nome deve ter entre 2 e 100 caracteres")
	@Pattern(regexp = "^[\\p{L} ]+$", message = "Nome deve conter apenas letras e espaços")
	@Column(nullable = false, length = 100)
	private String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	@CPF(message = "CPF inválido")
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CategoriaCliente categoria;
	
	@Past(message = "Data de nascimento dever ser no passado")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private LocalDate dataNascimento;
	
	@Embedded
	private EnderecoCliente endereco;
	
	@OneToOne 
	@JoinColumn(name = "usuario_id", nullable = false, unique = true)
	private Usuario usuario;

	
	//getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public CategoriaCliente getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaCliente categoria) {
		this.categoria = categoria;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public EnderecoCliente getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoCliente endereco) {
		this.endereco = endereco;
	}
	public Usuario getUsuario() {
	    return usuario;
	}
	public void setUsuario(Usuario usuario) {
	    this.usuario = usuario;
	}
	
	//metodos
	@PrePersist @PreUpdate
	private void formatarCampos() {
		this.cpf = this.cpf.replaceAll("[^0-9]", "");
	}
	
	public boolean isMaiorDeIdade() {
		if(this.dataNascimento == null) return false;
		return this.dataNascimento.plusYears(18).isBefore(LocalDate.now());
	}
	
	@Override
	public String toString() {
		return "Cliente [id= " + id + " , nome= " + nome + " , categoria= " + categoria + " ]";
	}
	
	//constructor
	public Cliente() {
		this.categoria = CategoriaCliente.COMUM; //default de criação de cadastro
	}
	public Cliente(String nome, String cpf, LocalDate dataNascimento, EnderecoCliente endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.categoria = CategoriaCliente.COMUM;
	}

}
