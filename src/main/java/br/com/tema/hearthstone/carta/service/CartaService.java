package br.com.tema.hearthstone.carta.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.tema.hearthstone.carta.entity.Carta;
import br.com.tema.hearthstone.carta.enums.Classe;
import br.com.tema.hearthstone.carta.enums.Tipo;
import br.com.tema.hearthstone.carta.repository.CartaRepository;

@Service
public class CartaService {
	
	private CartaRepository repo;

	public CartaService(CartaRepository repo) {
		this.repo = repo;
	}
	
	public Carta buscarPorId(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Carta> buscaPorClasse(String classe, Pageable pageable) {
		
		Classe c = Classe.existeClasse(classe);
		
		if(c==null) {
			throw new RuntimeException("A classe informada não existe.");
		}
		
		return repo.findByClasse(c, pageable).getContent();
	}
	
	public List<Carta> buscaPorTipo(String tipo, Pageable pageable) {
		Tipo t = Tipo.existeTipo(tipo);
		
		if(t==null) {
			throw new RuntimeException("A classe informada não existe.");
		}
		
		return repo.findByTipo(t, pageable).getContent();
	}
	
	public List<Carta> buscaPorNome(String nome, Pageable pageable){
		return repo.findByNomeIgnoreCase(nome, pageable).getContent();
	}
	
	public List<Carta> Deck(Pageable pageable){
		return repo.findAll(pageable).getContent();
	}
	
	public Carta add(Carta carta) {
		if(carta==null) {
			throw new RuntimeException("A carta não pode ser nula");
		}
		
		return salvar(carta);
	}
	
	public Carta update(Carta carta) {
		if(carta==null || carta.getId()==null) {
			throw new RuntimeException("A carta/id não pode ser nulo.");
		}
		
		Carta tmp = repo.findById(carta.getId()).orElse(null);
		
		if(tmp==null) {
			throw new RuntimeException("A carta informada não foi encontrada."); 
		}
		
		if(!carta.getAtaque().equals(tmp.getAtaque()) && carta.getAtaque()>=0) {
			tmp.setAtaque(carta.getAtaque());
		}
		
		if(!carta.getDefesa().equals(tmp.getDefesa()) && carta.getDefesa()>=0) {
			tmp.setDefesa(carta.getDefesa());
		}
		
		if(!carta.getClasse().equals(tmp.getClasse())) {
			tmp.setClasse(carta.getClasse());
		}
		
		if(!carta.getTipo().equals(tmp.getTipo())) {
			tmp.setTipo(carta.getTipo());
		}
		
		if(!carta.getNome().equalsIgnoreCase(tmp.getNome())) {
			tmp.setNome(carta.getNome());
		}
		
		return salvar(carta);
	}
	
	public Boolean deleteCarta(Long id) {
		try {
			
			if(!repo.existsById(id)) {
				throw new RuntimeException("O registro não foi enontrado.");
			}
			
			repo.deleteById(id);
			return true;
		}catch (IllegalArgumentException e) {
			throw new RuntimeException("Id informado está invalido.");
		}
	}
	
	private Carta salvar(Carta c) {
		return repo.save(c);
	}
}
