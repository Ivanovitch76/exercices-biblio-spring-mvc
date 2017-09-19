package be.steformations.it.javaweb.spring.gestion.controleur;

import java.util.ArrayList;
import java.util.List;

import be.steformations.it.javaweb.spring.gestion.modele.AuteursLivres;
import be.steformations.it.javaweb.spring.gestion.modele.CollectionContent;
import be.steformations.it.javaweb.spring.gestion.modele.GestionDB;
import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.pc.java_data.biblio.beans.AuteurImpl;
import be.steformations.pc.java_data.biblio.beans.CollectionImpl;
import be.steformations.pc.java_data.biblio.beans.LivreImpl;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("request")
public class AuteursControl {
	
	@org.springframework.beans.factory.annotation.Autowired // injection par Spring
	GestionDB gestionnaire;
		
	public AuteursControl(){
		System.out.println("Auteurs.Auteurs()");
	}	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/auteurs
	@org.springframework.web.bind.annotation.RequestMapping("auteurs")
	public String donneesAuteurs(
			java.util.Map<String, Object> attributs){		

		//attributs.put("listeAuteurs", gestionnaire.getAllAuteurs());
		attributs.put("listeAuteurs", listeAuteurs());

		return "/auteurs.jsp";	
	}	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/delAuteur		
	@org.springframework.web.bind.annotation.RequestMapping("delAuteur")
	public String enleverAuteurs(
			@org.springframework.web.bind.annotation.RequestParam("delvalue") String id,
			java.util.Map<String, Object> attributs){			
	//remove auteur
		int i = Integer.parseInt(id);
		gestionnaire.removeAuteur(i);
		//attributs.put("listeAuteurs", gestionnaire.getAllAuteurs());
		attributs.put("listeAuteurs", listeAuteurs());
		return "/auteurs.jsp";	
		
	}	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/addAuteur	
	@org.springframework.web.bind.annotation.RequestMapping("addAuteur")
	public String addAuteurs(
			@org.springframework.web.bind.annotation.RequestParam("prenom") String prenom,
			@org.springframework.web.bind.annotation.RequestParam("nom") String nom,
			java.util.Map<String, Object> attributs){			

		gestionnaire.addAuteur(prenom, nom);
		attributs.put("listeAuteurs", gestionnaire.getAllAuteurs());

		return "/auteurs.jsp";	
		
	}	
	
	
	private List<AuteursLivres> listeAuteurs(){
		List<LivreImpl> listeLivres = gestionnaire.getAllLivres();
		List<AuteurImpl> listeAuteurs = gestionnaire.getAllAuteurs();
		List<AuteursLivres> autLivres = new ArrayList<>();
		for(Auteur auteur : listeAuteurs){
			AuteursLivres al = new AuteursLivres();
			al.setAuteur(auteur);			
			if (gestionnaire.searchLivres(auteur.getId(), null, null).isEmpty()){ 
				al.setLivresEcrits(false);
			}	
			autLivres.add(al);		
		}	
		return autLivres;
	}
}
