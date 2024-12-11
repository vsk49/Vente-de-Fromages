package modele;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fromages {

	private List<Fromage> fromages;

	public Fromages() {
		this.fromages = new LinkedList<Fromage>();
	}

	public void addFromages(List<Fromage> fromages) {
		this.fromages.addAll(fromages);
	}

	public String toStringFromagesEtArticles() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.fromages) {
			enForme.append(f.toString() + '\n');
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toString() + '\n');
				}
			}
		}
		return enForme.toString();
	}

	public String toStringArticlesEtStock() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toStringAvecStock() + '\n');
				}
			}
		}
		return enForme.toString();
	}

	public List<Fromage> fromagesAuLaitDe(TypeLait lait) {
		List<Fromage> fromagesDuType = new LinkedList<>();
		for (Fromage f : this.fromages) {
			if (f.getTypeFromage().equals(lait)) {
				fromagesDuType.add(f);
			}
		}
		return fromagesDuType;
	}

	public List<Fromage> getFromages() {
		Collections.sort(this.fromages);
		return this.fromages;
	}

	public Fromage getFromage(String désignation) {
		for (Fromage f : this.fromages) {
			if (f.getDésignation().equals(désignation)) {
				return f;
			}
		}
		return null;
	}

	public void regénérationDuStock() {
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					article.setQuantitéEnStock(
					        (int) Math.round(Math.random() * 100));
				}
			}
		}
	}

	public String vérificationSaisie() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() == 0) {
				enForme.append("Pas d'articles pour " + f.toString() + '\n');
			}
			if (f.getDescription() == null) {
				enForme.append(
				        "Pas de description pour " + f.toString() + '\n');
			}
			if (f.getNomImage() == null) {
				enForme.append(
				        "Pas de nom d'image pour " + f.toString() + '\n');
			}
		}
		return enForme.toString();
	}

	public Article getArticle(String désignation, String clé) {
		for (Fromage f : this.fromages) {
			if (f.getDésignation().equals(désignation)) {
				for (Article article : f.getArticles()) {
					if (article.getClé().equals(clé)) {
						return article;
					}
				}
			}
		}
		return null;
	}
	
	public void updateStock(String designation, String articleCle, int quantitySold) {
	    Article article = getArticle(designation, articleCle);
	    if (article != null) {
	        int currentStock = article.getQuantitéEnStock();
	        article.setQuantitéEnStock(currentStock - quantitySold);  // Réduction du stock
	    }
	}

}
