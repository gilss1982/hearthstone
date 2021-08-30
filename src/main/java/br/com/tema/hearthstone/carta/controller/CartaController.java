package br.com.tema.hearthstone.carta.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tema.hearthstone.carta.entity.Carta;
import br.com.tema.hearthstone.carta.service.CartaService;
import br.com.tema.hearthstone.utils.PageableUtils;

@CrossOrigin
@RestController
@RequestMapping
public class CartaController extends PageableUtils {

	private CartaService service;

	public CartaController(CartaService service) {
		this.service = service;
	}
	
	@GetMapping("deck/{size}")
	public ResponseEntity<List<Carta>> deck(
			@PathVariable(name = "size") String size){
		return ResponseEntity.status(200).body(service.Deck(definePaginacao(size)));
	}
	
	@GetMapping("lista/classe/{classe}/{size}")
	public ResponseEntity<List<Carta>> buscaPorClasse(
			@PathVariable(name = "classe") String classe,
			@PathVariable(name = "size") String size){
		
		return ResponseEntity.status(200).body(service.buscaPorClasse(classe, definePaginacao(size)));
	}
	
	@GetMapping("lista/classe/{tipo}/{size}")
	public ResponseEntity<List<Carta>> buscaPorTipo(
			@PathVariable(name = "tipo") String tipo,
			@PathVariable(name = "size") String size){	
		
		return ResponseEntity.status(200).body(service.buscaPorTipo(tipo, definePaginacao(size)));
	}
	
	@GetMapping("lista/nome/{nome}/{size}")
	public ResponseEntity<List<Carta>> buscaPorNome(
			@PathVariable(name = "tipo") String nome,
			@PathVariable(name = "size") String size){	
		
		return ResponseEntity.status(200).body(service.buscaPorNome(nome, definePaginacao(size)));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<List<Carta>> buscaPorId(
			@PathVariable(name = "tipo") String id){
		return ResponseEntity.status(200).body(Arrays.asList(service.buscarPorId(Long.parseLong(id))));
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(
			@PathVariable(name = "tipo") String id){
		return ResponseEntity.status(202).body(service.deleteCarta(Long.parseLong(id)));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Carta> add(
			@RequestBody Carta carta){
		return ResponseEntity.status(200).body(service.add(carta));
	}	
	
}
