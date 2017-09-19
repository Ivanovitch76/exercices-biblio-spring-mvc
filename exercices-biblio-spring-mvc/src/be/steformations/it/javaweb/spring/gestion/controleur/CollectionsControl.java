package be.steformations.it.javaweb.spring.gestion.controleur;

import java.util.ArrayList;
import java.util.List;

import be.steformations.it.javaweb.spring.gestion.modele.CollectionContent;
import be.steformations.it.javaweb.spring.gestion.modele.GestionDB;
import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.pc.java_data.biblio.beans.CollectionImpl;
import be.steformations.pc.java_data.biblio.beans.LivreImpl;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("request")
public class CollectionsControl {

	@org.springframework.beans.factory.annotation.Autowired // injection par Spring
	GestionDB gestionnaire;
	
	public CollectionsControl(){
		System.out.println("Collections.Collections()");
	}		
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/collections
	@org.springframework.web.bind.annotation.RequestMapping("collections")
	public String donneesCollections(
			java.util.Map<String, Object> attributs){		

//		attributs.put("listeCollections", gestionnaire.getAllCollections());
		attributs.put("listeCollections", listeCollections());
		
		return "/collections.jsp";	
	}	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/delColl		
	@org.springframework.web.bind.annotation.RequestMapping("delColl")
	public String enleverAuteurs(
			@org.springframework.web.bind.annotation.RequestParam("idColl") String id,
			java.util.Map<String, Object> attributs){			
	//remove auteur
		int i = Integer.parseInt(id);
		gestionnaire.removeCollection(i);
		attributs.put("listeCollections", listeCollections());

		return "/collections.jsp";	
		
	}	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/addColl	
	@org.springframework.web.bind.annotation.RequestMapping("addColl")
	public String addAuteurs(
			@org.springframework.web.bind.annotation.RequestParam("nom") String nom,
			java.util.Map<String, Object> attributs){			

		gestionnaire.addCollection(nom);
		attributs.put("listeCollections", gestionnaire.getAllCollections());

		return "/collections.jsp";	
		
	}	
	
	private List<CollectionContent> listeCollections(){
		List<LivreImpl> listeLivres = gestionnaire.getAllLivres();
		List<CollectionImpl> listeColl = gestionnaire.getAllCollections();
		List<CollectionContent> collContent = new ArrayList<>();
		for(Collection collection : listeColl){
			CollectionContent cc = new CollectionContent();
			cc.setCollection(collection);			
			for (Livre livre : listeLivres) {
				if (collection.getId().equals(livre.getCollection().getId())){ 
					cc.setContenu(true);
				}	
			}
			collContent.add(cc);
		}		

		return collContent;
	}
	
}
