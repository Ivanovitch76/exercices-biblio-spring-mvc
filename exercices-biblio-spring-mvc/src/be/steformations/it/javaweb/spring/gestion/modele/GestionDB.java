package be.steformations.it.javaweb.spring.gestion.modele;

import java.util.Date;
import java.util.List;

import be.steformations.pc.java_data.biblio.beans.AuteurImpl;
import be.steformations.pc.java_data.biblio.beans.CollectionImpl;
import be.steformations.pc.java_data.biblio.beans.LivreImpl;
import be.steformations.pc.java_data.biblio.beans.ReservationImpl;
import be.steformations.pc.java_data.biblio.dao.jpa.JpaGestionnaireBibliotheque;

@org.springframework.stereotype.Service //specialisation de @Component
@org.springframework.context.annotation.Scope("application") // singleton + durée de vie égale à l'application)
public class GestionDB {

	private JpaGestionnaireBibliotheque jgb;
		
	public GestionDB() {
		super();
		//String persistenceUnit = "postgresql_hibernate";
		javax.persistence.EntityManager em 
		 	= javax.persistence.Persistence.createEntityManagerFactory("postgresql_eclipselink")
		 		.createEntityManager();
		this.jgb = new JpaGestionnaireBibliotheque(em);
	}

	public List<AuteurImpl> getAllAuteurs() {
		return jgb.getAllAuteurs();
	}

	public AuteurImpl addAuteur(String prenom, String nom) {
		return jgb.addAuteur(prenom, nom);
	}

	public void removeAuteur(int code) {
		jgb.removeAuteur(code);
	}

	public List<CollectionImpl> getAllCollections() {
		return jgb.getAllCollections();
	}

	public CollectionImpl addCollection(String nom) {
		return jgb.addCollection(nom);
	}

	public void removeCollection(int code) {
		jgb.removeCollection(code);
	}

	public List<LivreImpl> getAllLivres() {
		return jgb.getAllLivres();
	}

	public LivreImpl addLivre(String titre, short nombreDePages, Date dateDeParution, int idCollection,
			short numeroEdition, List<Integer> auteurs) {
		return jgb.addLivre(titre, nombreDePages, dateDeParution, idCollection, numeroEdition, auteurs);
	}

	public void removeLivre(String code) {
		jgb.removeLivre(code);
	}

	public List<ReservationImpl> getReservationsByLivreCode(String code) {
		return jgb.getReservationsByLivreCode(code);
	}

	public List<LivreImpl> searchLivres(Integer auteurId, Integer collectionId, String partieDuTitre) {
		return jgb.searchLivres(auteurId, collectionId, partieDuTitre);
	}
	
	
	
}
