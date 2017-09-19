package be.steformations.it.javaweb.spring.gestion.controleur;

//@org.springframework.stereotype.Component //instanciation par le framework Spring
@org.springframework.stereotype.Controller //sp�cialisation de Component
@org.springframework.context.annotation.Scope("request") //dur�e de vie �gale � une seule requ�te
public class Index {
		
		//obligatoire pour l'instanciation par Spring
		public Index(){
			System.out.println("Gestion.Gestion()");
		}
		
		// http://localhost:8080/exercices-biblio-spring-mvc/spring/new
		@org.springframework.web.bind.annotation.RequestMapping("index") //unique si @Controller
		public String showIndex(){
			return "/index.jsp"; // http://localhost:8080/exercices-biblio-spring-mvc/index.jsp
		}	
	
}
