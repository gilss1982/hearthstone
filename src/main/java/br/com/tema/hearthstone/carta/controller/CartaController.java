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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "carta")
@RestController
@RequestMapping("carta")
public class CartaController extends PageableUtils {

	private CartaService service;

	public CartaController(CartaService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "Lista o deck de cartas disponiveis para escolher")
	@GetMapping("deck/{size}")
	public ResponseEntity<List<Carta>> deck(
			@PathVariable(name = "size") String size){
		return ResponseEntity.status(200).body(service.Deck(definePaginacao(size)));
	}
	
	@ApiOperation(value = "Lista cartas por classe")
	@GetMapping("lista/classe/{classe}/{size}")
	public ResponseEntity<List<Carta>> buscaPorClasse(
			@PathVariable(name = "classe") String classe,
			@PathVariable(name = "size") String size){
		
		return ResponseEntity.status(200).body(service.buscaPorClasse(classe, definePaginacao(size)));
	}
	
	@ApiOperation(value = "Lista cartas por tipo")
	@GetMapping("lista/tipo/{tipo}/{size}")
	public ResponseEntity<List<Carta>> buscaPorTipo(
			@PathVariable(name = "tipo") String tipo,
			@PathVariable(name = "size") String size){	
		
		return ResponseEntity.status(200).body(service.buscaPorTipo(tipo, definePaginacao(size)));
	}
	
	@ApiOperation(value = "Lista as cartas por nome")
	@GetMapping("lista/nome/{nome}/{size}")
	public ResponseEntity<List<Carta>> buscaPorNome(
			@PathVariable(name = "tipo") String nome,
			@PathVariable(name = "size") String size){	
		
		return ResponseEntity.status(200).body(service.buscaPorNome(nome, definePaginacao(size)));
	}
	
	@ApiOperation(value = "Busca as cartas por id")
	@GetMapping("id/{id}")
	public ResponseEntity<List<Carta>> buscaPorId(
			@PathVariable(name = "id") String id){
		return ResponseEntity.status(200).body(Arrays.asList(service.buscarPorId(Long.parseLong(id))));
	}
	
	@ApiOperation(value = "Deleta uma carta por id.")
	@GetMapping("delete/{id}")
	public ResponseEntity<Boolean> delete(
			@PathVariable(name = "id") String id){
		return ResponseEntity.status(202).body(service.deleteCarta(Long.parseLong(id)));
	}
	
	@ApiOperation(value = "Cria uma carta")
	@PostMapping("criar")
	public ResponseEntity<Carta> criar(
			@RequestBody Carta carta){
		return ResponseEntity.status(200).body(service.criar(carta));
	}	
	
}
