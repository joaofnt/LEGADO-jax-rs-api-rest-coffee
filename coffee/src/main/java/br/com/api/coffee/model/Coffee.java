package br.com.api.coffee.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public class Coffee {
	private Long id;
	
	@NotBlank(message = "Nome não pode estar em branco")
	private String nome;
	@NotNull(message = "Preço não pode ser nulo ")
	@PositiveOrZero(message = "O preço não pode ser menor negativo")
	private Double preco;
	
	@PastOrPresent(message = "A data tem que ser igual a hoje")
	private LocalDate dataFabricacao;
	
	@FutureOrPresent(message = "A data não pode ser menor ou igual a hoje")
	private LocalDate dataValidade;
	
	public Coffee(Long id, @NotBlank(message = "Nome não pode estar em branco") String nome,
			@NotNull(message = "Preço não pode ser nulo ") @PositiveOrZero(message = "O preço não pode ser menor negativo") Double preco,
			@PastOrPresent(message = "A data tem que ser igual a hoje") LocalDate dataFabricacao,
			@FutureOrPresent(message = "A data não pode ser menor ou igual a hoje") LocalDate dataValidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.dataFabricacao = dataFabricacao;
		this.dataValidade = dataValidade;
	}
	
	public Coffee() {
		super();
	}
	
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
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataFabricacao, dataValidade, id, nome, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coffee other = (Coffee) obj;
		return Objects.equals(dataFabricacao, other.dataFabricacao) && Objects.equals(dataValidade, other.dataValidade)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco);
	}

	@Override
	public String toString() {
		return "Coffee [id=" + id + ", nome=" + nome + ", preco=" + preco + ", dataFabricacao=" + dataFabricacao
				+ ", dataValidade=" + dataValidade + "]";
	}
	
	

}
