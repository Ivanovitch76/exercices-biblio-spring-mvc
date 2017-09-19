package be.steformations.it.javaweb.spring.gestion.modele;

import be.steformations.java_data.biblio.interfaces.Livre;

public class LivresReserves {

	Livre livre = null;
	boolean reserve = false;
	
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	public boolean isReserve() {
		return reserve;
	}
	public void setReserve(boolean reserve) {
		this.reserve = reserve;
	}
	
}
