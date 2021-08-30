package br.com.tema.hearthstone.carta.enums;

public enum Tipo {
	CRIATURA,
	MAGIA;
	
	public static Tipo existeTipo(String tipo) {
		for (Tipo t : Tipo.values()) {
			if(t.toString().equalsIgnoreCase(tipo)) {
				return t;
			}
		}
		return null;
	}
}
