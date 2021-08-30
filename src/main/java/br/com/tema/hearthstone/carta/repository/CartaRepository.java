package br.com.tema.hearthstone.carta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.tema.hearthstone.carta.entity.Carta;
import br.com.tema.hearthstone.carta.enums.Classe;
import br.com.tema.hearthstone.carta.enums.Tipo;

@Repository
public interface CartaRepository extends PagingAndSortingRepository<Carta, Long> {

	Page<Carta> findByClasse(Classe classe, Pageable pageable);
	Page<Carta> findByTipo(Tipo tipo, Pageable pageable);
	
	Page<Carta> findByNomeContains(String nome, Pageable pageable);
	Page<Carta> findByNomeIgnoreCase(String nome, Pageable pageable);
	
}
