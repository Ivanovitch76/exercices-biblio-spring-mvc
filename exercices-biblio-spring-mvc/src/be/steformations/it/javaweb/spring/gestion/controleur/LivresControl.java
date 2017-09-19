package be.steformations.it.javaweb.spring.gestion.controleur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import be.steformations.it.javaweb.spring.gestion.modele.GestionDB;
import be.steformations.it.javaweb.spring.gestion.modele.LivresReserves;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.pc.java_data.biblio.beans.LivreImpl;
import be.steformations.pc.java_data.biblio.beans.ReservationImpl;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("request")
public class LivresControl {

	@org.springframework.beans.factory.annotation.Autowired // injection par Spring
	GestionDB gestionnaire;
	
	public LivresControl(){
		System.out.println("Livres.Livres()");
	}	
	
	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/livres
	@org.springframework.web.bind.annotation.RequestMapping("livres")
	public String donneesLivres(

			java.util.Map<String, Object> attributs){		

		attributs.put("listeLivres", gestionnaire.getAllLivres());
		attributs.put("reservations", listeReservations());
		attributs.put("listeCollections", gestionnaire.getAllCollections());
		attributs.put("listeAuteurs", gestionnaire.getAllAuteurs());

		

		return "/livres.jsp";	
	}	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/delAuteur		
	@org.springframework.web.bind.annotation.RequestMapping("delLivre")
	public String enleverLivre(
			@org.springframework.web.bind.annotation.RequestParam("livreCode") String code,
			java.util.Map<String, Object> attributs){			
	//remove auteur

		gestionnaire.removeLivre(code);
		attributs.put("listeLivres", gestionnaire.getAllLivres());
		attributs.put("reservations", listeReservations());
		attributs.put("listeCollections", gestionnaire.getAllCollections());
		attributs.put("listeAuteurs", gestionnaire.getAllAuteurs());

		return "/livres.jsp";	
		
	}	
	
	// http://localhost:8080/webcalcul-spring-mvc/spring/addAuteur	
	@org.springframework.web.bind.annotation.RequestMapping("addLivre")
	public String addLivre(
			@org.springframework.web.bind.annotation.RequestParam("titre") String titre,
			@org.springframework.web.bind.annotation.RequestParam("edition") String edition,
			@org.springframework.web.bind.annotation.RequestParam("parution") String parution,
			@org.springframework.web.bind.annotation.RequestParam("pages") String pages,
			@org.springframework.web.bind.annotation.RequestParam("collection") String collection,
			@org.springframework.web.bind.annotation.RequestParam("auteurs") String[] auteurs,
			java.util.Map<String, Object> attributs){			

		
		int idcoll = 0;
		if (collection != null){
			idcoll = Integer.parseInt(collection);
		} 	
		
		short nbPages = 0;
		if (pages != null){
			nbPages = (short) Integer.parseInt(pages);
		}	
		short numEd = (short) Integer.parseInt(edition);		
		Date dateParution = null;  
		if (parution != null) {
			try {
				dateParution = new SimpleDateFormat("dd-mm-yyyy").parse(parution);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<String> auteursString = Arrays.asList(auteurs);		
		List<Integer> auteursInt = new ArrayList();
		for (String i : auteursString) {
			int auteur = Integer.parseInt(i);
			auteursInt.add(auteur);
		}

		gestionnaire.addLivre(titre, nbPages, dateParution, idcoll, numEd, auteursInt);
		attributs.put("listeLivres", gestionnaire.getAllLivres());
		attributs.put("listeCollections", gestionnaire.getAllCollections());
		attributs.put("listeAuteurs", gestionnaire.getAllAuteurs());

		return "/livres.jsp";	
		
	}	
	
	private List<LivresReserves> listeReservations(){
		List<LivreImpl> listeLivres = gestionnaire.getAllLivres();
		List<LivresReserves> livresReserves = new ArrayList<>();
		for(Livre livre : listeLivres){
			LivresReserves lr = new LivresReserves();
			lr.setLivre(livre);
			List<ReservationImpl> listeReserv = gestionnaire.getReservationsByLivreCode(livre.getCode());
			if (listeReserv.size() > 0){
				lr.setReserve(true);
			}
			livresReserves.add(lr);
		}		
		
		return livresReserves;
	}
	
}
