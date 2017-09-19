package be.steformations.it.javaweb.spring.gestion.modele;

import be.steformations.java_data.biblio.interfaces.Collection;

public class CollectionContent {
	Collection collection = null;
	boolean contenu = false;
	
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public boolean isContenu() {
		return contenu;
	}
	public void setContenu(boolean contenu) {
		this.contenu = contenu;
	}
		
}
