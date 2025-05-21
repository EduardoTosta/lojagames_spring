package com.generation.lojagames.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto") // CREATE TABLE tb_produto();
public class Produto {
	
	//Atributos
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	private Long id; 
	
	//Titulo
	@Column(length = 100)
	@NotBlank(message = "O atributo 'titulo' é obrigatório")
	@Size(min = 1, max = 100, message = "O atributo 'título' deve ter entre 1 e 100 caracteres")
	private String titulo;
	
	//Plataforma
	@Column(length = 1000)
	@NotBlank(message = "O atributo 'plataforma' é obrigatório")
	@Size(min = 1, max = 50, message = "O atributo 'plataforma' deve ter entre 1 e 50 caracteres")
	private String plataforma;
	
	//Imagem (Link)
	@Column(length = 1000)
	@NotBlank(message = "O atributo 'imagem' é obrigatório")
	@Size(min = 1, max = 1000, message = "O atributo 'imagem' deve ter entre 1 e 1000 caracteres")
	@URL(message = "O atributo 'imagem' deve conter uma URL válida")
	private String imagem;
	
	//Estoque
	@NotNull(message = "O estoque é obrigatório!")
    private Integer estoque;
	
	//Preço
	@NotNull(message = "O atributo 'preço' é obrigatório")
	@Positive(message = "O atributo 'preço' deve ser um número positivo")
	@Digits(integer = 6, fraction = 2, message = "O preço deve ter até 6 dígitos inteiros e 2 casas decimais")
	private BigDecimal preco;
	
	//Categoria
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	//Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}
