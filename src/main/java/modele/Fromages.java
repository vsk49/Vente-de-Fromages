package modele;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fromages {

	private List<Fromage> fromages;

	public Fromages() {
		this.fromages = new LinkedList<>();
	}

	public void addFromages(List<Fromage> fromages) {
		this.fromages.addAll(fromages);
	}

	public String toStringFromagesEtArticles() {
		StringBuilder enForme = new StringBuilder();
		for (Fromage f : this.fromages) {
			enForme.append(f.toString()).append('\n');
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toString()).append('\n');
				}
			}
		}
		return enForme.toString();
	}

	public String toStringArticlesEtStock() {
		StringBuilder enForme = new StringBuilder();
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toStringAvecStock()).append('\n');
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
		StringBuilder enForme = new StringBuilder();
		for (Fromage f : this.fromages) {
			if (f.nombreArticles() == 0) {
				enForme.append("Pas d'articles pour ").append(f).append('\n');
			}
			if (f.getDescription() == null) {
				enForme.append("Pas de description pour ").append(f).append('\n');
			}
			if (f.getNomImage() == null) {
				enForme.append("Pas de nom d'image pour ").append(f).append('\n');
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

}
