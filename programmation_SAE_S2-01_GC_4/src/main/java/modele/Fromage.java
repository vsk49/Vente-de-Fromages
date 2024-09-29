package modele;

import java.util.LinkedList;
import java.util.List;

public class Fromage implements Comparable<Fromage> {

	private String désignation;
	private String nomImage;
	private String description;
	private TypeLait typeFromage;
	private List<Article> articles;

	public Fromage(String désignation) {
		this.désignation = désignation;
		this.articles = new LinkedList<>();
	}

	public String getDésignation() {
		return this.désignation;
	}

	public String getNomImage() {
		return this.nomImage;
	}

	public String getDescription() {
		assert !(this.description == null);
		if (this.description == null) {
			return "Pas de description de ce fromage pour le moment. Nous en sommes désolés";
		}
		return this.description;
	}

	public void addDescription(String description) {
		this.description = description;
	}

	public void addNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public void updateTypeFromage(TypeLait type) {
		this.typeFromage = type;
	}

	public TypeLait getTypeFromage() {
		return this.typeFromage;
	}

	@Override
	public String toString() {
		return this.getDésignation() + ", Fromage au lait de "
		        + this.typeFromage.getTypeDeLait();
	}

	public void addArticle(String clé, float prixTTC) {
		this.articles.add(new Article(this, clé, prixTTC));
	}

	public int nombreArticles() {
		return this.articles.size();
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	@Override
	public int compareTo(Fromage f) {
		return this.désignation.compareTo(f.désignation);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof Fromage) {
			Fromage other = (Fromage)obj;
			return this.désignation.equals(other.désignation);
		} else {
			return false;
		}
	}

}
