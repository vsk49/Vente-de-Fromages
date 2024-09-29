package modele;

import static org.junit.Assert.*;

import javax.swing.JComboBox;
import javax.swing.JTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ihm.FEN_Facture;
import ihm.FEN_Nos_Fromages;
import ihm.FEN_Panier_Fromages;

public class TestCalculFacture {

	private SaisieFromage saisieALaCoupe = new SaisieFromage("Brie de Melun", "brie_de_melun",
			"Plus petit que son grand frère «le Brie de Meaux», il a cependant plus de caractère. Ce fromage au lait cru de vache vous offrira une pâte "
					+ "souple de couleur jaune d’or à l’intérieur, et une croûte fleurie blanche parsemée de stries ou de taches rouges ou brunes à l’extérieur. "
					+ "Le Brie de Melun vous proposera une saveur très fruitée avec un léger goût de noisette accompagnée d’une odeur du terroir qui le rendra "
					+ "indispensable sur un plateau de fromages. Il pourra aussi entrer dans la confection de spécialités régionales dont la plus connue, "
					+ "la croûte au brie.",
			TypeVente.A_LA_COUPE_AU_POIDS, new String[] { "250 g", "500 g", "1 Kg" },
			new float[] { 9.15F, 18.3F, 36.6F });
	private SaisieFromage saisieEntierMoitie = new SaisieFromage("Brin d'Amour Corse", "brin_d_amour_corse",
			"Ce fromage au lait cru de brebis à pâte molle à croûte fleurie, propose un caractère corse qui s'exprime par sa croûte recouverte d'herbes, "
					+ "de sarriettes et de romarin. Le Brin d'Amour offre un goût inimitable et une saveur parfumée et intense, il reste une merveille pour le palais. "
					+ "C'est tout le caractère du fromage corse vous faisant voyager qui s'exprime dans ce mariage très heureux de lait de brebis et d’aromates. "
					+ "La «Fleur du maquis» dont la pâte est fine et la couleur varie entre le rouge et le vert.",
			TypeVente.ENTIER_OU_MOITIE, new String[] { "" }, new float[] { 8.5F, 16.8F });
	private SaisieFromage saisieXPersonnes = new SaisieFromage("Fondue Savoyarde", "fondue_savoyarde",
			"Idéal pour un repas convivial avec vos amis ou en famille. C'est un mélange de parfum, d'onctuosité, et de raffinement pour "
					+ "cette fondue savoyarde. Allez y piquez vos morceaux de pain dans le caquelon au centre de la table. "
					+ "La fondue savoyarde est un plat régional de la gastronomie française à base de fromage fondu et de pain, traditionnel des pays de Savoie. "
					+ "Ce plat populaire, vous envoûtera de part ses multiples arômes.",
			TypeVente.POUR_X_PERSONNES, new String[] { "3", "5", "10" }, new float[] { 22.5F, 33.75F, 67.5F });
	private Fromage f1;
	private Fromage f3;
	private Fromage f5;

	@Before
	public void setUp() throws Exception {
		this.f1 = this.saisieALaCoupe.builderFromage();
		this.f3 = this.saisieEntierMoitie.builderFromage();
		this.f5 = this.saisieXPersonnes.builderFromage();
	}

	@After
	public void tearDown() throws Exception {
		this.f1 = null;
		this.f3 = null;
		this.f5 = null;
	}
	
	@Test
	public void testCalculPrixArticlesFacture() {
	    FEN_Panier_Fromages panier = new FEN_Panier_Fromages(new FEN_Nos_Fromages());
	    panier.ajouterAuPanier(this.f1.getDésignation() + " - " 
	    		+ this.f1.getArticles().get(0).getClé(), 1, 20, 20f);
	    FEN_Facture facture = new FEN_Facture(null, panier);
	    facture.afficherArticles(panier.getTable());
	    JTable tableFacture = facture.getTableFacture();
	    assertEquals("Brie de Melun - 250 g", (String)tableFacture.getValueAt(0, 0));
	    assertEquals("1,00 €", (String)tableFacture.getValueAt(0, 1));
	    assertEquals("20", (String)tableFacture.getValueAt(0, 2));
	    assertEquals("20,00 €", (String)tableFacture.getValueAt(0, 3));
	}

	@Test
	public void testCalculSousTotalFraisLivraisonTotalTTC() {
	    FEN_Panier_Fromages panier = new FEN_Panier_Fromages(new FEN_Nos_Fromages());
	    panier.ajouterAuPanier(this.f1.getDésignation() + " - " 
	    		+ this.f1.getArticles().get(0).getClé(), 1, 15, 15f);
	    panier.ajouterAuPanier(this.f3.getDésignation() + " - " 
	    		+ this.f3.getArticles().get(0).getClé(), 1, 20, 20f);
	    panier.ajouterAuPanier(this.f5.getDésignation() + " - " 
	    		+ this.f5.getArticles().get(1).getClé(), 2, 15, 30f);
	    JComboBox<String> comboBoxColis = panier.getComboBoxColis();
	    comboBoxColis.setSelectedItem("Chronofresh");
	    FEN_Facture facture = new FEN_Facture(null, panier);
	    facture.afficherArticles(panier.getTable());
	    facture.afficherPrixTotal(panier.getPrixPanier());
	    assertEquals("Sous-Total : 65,00€", facture.getSousTotal());
	    assertEquals("Frais de Livraison : 17,80€", facture.getFraisLivraison());
	    assertEquals("Total TTC : 82,80€", facture.getTotalTTC());
	}
	
	@Test
	public void testCalculPrixPanierVide() {
	    FEN_Panier_Fromages panier = new FEN_Panier_Fromages(new FEN_Nos_Fromages());
	    FEN_Facture facture = new FEN_Facture(null, panier);
	    facture.afficherPrixTotal(panier.getPrixPanier());
	    assertEquals("Sous-Total : 0,00 €", facture.getSousTotal());
	    assertEquals("Frais de Livraison : 0,00 €", facture.getFraisLivraison());
	    assertEquals("Total TTC : 0,00 €", facture.getTotalTTC());
	}
	

}
