package com.generation.lojagames.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto") 
public class Produto {
	
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	private Long id; 
	
	@Column(length = 1000)
	@NotBlank(message = "O atributo 'titulo' é obrigatório")
	@Size(min = 1, max = 1000, message = "O atributo 'título' deve ter entre 1 e 50 caracteres")
	private String titulo;
	
	@Column(length = 1000)
	@NotBlank(message = "O atributo 'plataforma' é obrigatório")
	@Size(min = 1, max = 1000, message = "O atributo 'plataforma' deve ter entre 1 e 20 caracteres")
	private String plataforma;
	
	@NotNull(message = "O atributo 'preço' é obrigatório")
	@Positive(message = "O atributo 'preço' deve ser um número positivo")
	@Digits(integer = 6, fraction = 2, message = "O preço deve ter até 6 dígitos inteiros e 2 casas decimais")
	private Double preco;
	
	
	
	
	
}
