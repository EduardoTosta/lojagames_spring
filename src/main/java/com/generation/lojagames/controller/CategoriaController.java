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

import com.generation.lojagames.model.Categoria;
import com.generation.lojagames.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Listar todos
	public ResponseEntity<?> getAll(){
		List<Categoria> produtos = categoriaRepository.findAll();
			
		if (produtos.isEmpty()) {
		    Map<String, String> response = new HashMap<>();
		    response.put("mensagem", "Nenhum produto encontrado.");
		    return ResponseEntity.status(200).body(response);
		}
		//SELECT * FROM tb_postagens;
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	//Busca por id
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//Busca por titulo
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Categoria>> getAllByDescricao(@PathVariable String titulo) {
		
		return ResponseEntity.ok(categoriaRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//Create
	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
	
	//Update
	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){
		
		if(categoria.getId() == null )
			return ResponseEntity.badRequest().build();
		
		if(categoriaRepository.existsById(categoria.getId())) 
			return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//Delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Optional<Categoria> postagem = categoriaRepository.findById(id);
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
		categoriaRepository.deleteById(id);
		
		//DELETE FROM tb_postagens WHERE id=?;
	}
	
}
