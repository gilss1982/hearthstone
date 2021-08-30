package br.com.tema.hearthstone.carta.enums;

public enum Classe {
	CACADOR,
	DRUIDA,
	MAGO,
	PALADINO,
	QUALQUER;
	
	public static Classe existeClasse(String classe) {
		for (Classe c : Classe.values()) {
			if(c.toString().equalsIgnoreCase(classe)) {
				return c;
			}
		}
		return null;
	}
}
