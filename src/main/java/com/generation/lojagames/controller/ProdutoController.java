package com.generation.lojagames.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	//Mostrar todos os produtos
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<Produto> produtos = produtoRepository.findAll();
			
		if (produtos.isEmpty()) {
		    Map<String, String> response = new HashMap<>();
		    response.put("mensagem", "Nenhum produto encontrado.");
		    return ResponseEntity.status(200).body(response);
		}
		//SELECT * FROM tb_postagens;
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	//Buscar por id
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//Buscar por titulo
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Produto>> getAllByTitulo(@PathVariable String titulo) {
		
		return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	//Create
	/*
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		
		if (temaRepository.existsById(postagem.getTema().getId())) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Tema não existe!", null);
	}
	*/
	
	//Update
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
 
		if (produto.getId() == null)
			return ResponseEntity.badRequest().build();
 
		/*if (produtoRepository.existsById(produto.getId())) {
			
			if (temaRepository.existsById(produto.getTema().getId()))
				
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Tema não existe!", null);
		}*/
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//Delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Optional<Produto> postagem = produtoRepository.findById(id);
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
		produtoRepository.deleteById(id);
		
		//DELETE FROM tb_postagens WHERE id=?;
	}
}
