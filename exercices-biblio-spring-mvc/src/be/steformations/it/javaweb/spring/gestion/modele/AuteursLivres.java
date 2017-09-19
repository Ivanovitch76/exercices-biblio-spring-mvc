package be.steformations.it.javaweb.spring.gestion.modele;

import be.steformations.java_data.biblio.interfaces.Auteur;

public class AuteursLivres {

		Auteur auteur = null;
		boolean livresEcrits = true;
		public Auteur getAuteur() {
			return auteur;
		}
		public void setAuteur(Auteur auteur) {
			this.auteur = auteur;
		}
		public boolean isLivresEcrits() {
			return livresEcrits;
		}
		public void setLivresEcrits(boolean livresEcrits) {
			this.livresEcrits = livresEcrits;
		}
		
		
}
