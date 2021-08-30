package br.com.tema.hearthstone.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class PageableUtils {
	
	private final List<Integer> tam  = Arrays.asList(10, 20, 30, 30, 50, 100);
	
	public Pageable definePaginacao(Integer tamDeck) {
		if(tamDeck==null || !tam.contains(tamDeck)) {
			return PageRequest.of(0, 100);
		}
		
		return PageRequest.of(0, tamDeck); 

	}
	
	public Pageable definePaginacao(String tamDeck) {
		Integer size = null;
		try {
			size = Integer.parseInt(tamDeck);
		} catch (NumberFormatException e) {
			size = 30;
		}
		
		
		if(!tam.contains(size)) {
			return PageRequest.of(0, 100);
		}
		
		return PageRequest.of(0, size); 

	}
	
	public Pageable definePaginacao() {
		return PageRequest.of(0, 100);
	}
	
}
