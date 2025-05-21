package com.generation.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojagames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findAllByTituloContainingIgnoreCase(String titulo); 
	
	List<Produto> findAllByPlataformaContainingIgnoreCase(String plataforma);

}
