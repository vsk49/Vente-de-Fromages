package modele;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ihm.FEN_Nos_Fromages;
import ihm.FEN_Panier_Fromages;

public class TestPanier {

	private SaisieFromage infoFromage;
	private Fromage unFromage;

	@Before
	public void setUp() throws Exception {
		this.infoFromage = new SaisieFromage("Brie de Melun", "brie_de_melun",
				"Plus petit que son grand frère «le Brie de Meaux», il a cependant plus de caractère. Ce fromage au lait cru de vache vous offrira une pâte "
						+ "souple de couleur jaune d’or à l’intérieur, et une croûte fleurie blanche parsemée de stries ou de taches rouges ou brunes à l’extérieur. "
						+ "Le Brie de Melun vous proposera une saveur très fruitée avec un léger goût de noisette accompagnée d’une odeur du terroir qui le rendra "
						+ "indispensable sur un plateau de fromages. Il pourra aussi entrer dans la confection de spécialités régionales dont la plus connue, "
						+ "la croûte au brie.",
				TypeVente.A_LA_COUPE_AU_POIDS, new String[] { "250 g", "500 g", "1 Kg" },
				new float[] { 9.15F, 18.3F, 36.6F });
		this.unFromage = infoFromage.builderFromage();
	}
	
	@After
	public void tearDown() throws Exception {
		this.infoFromage = null;
		this.unFromage = null;
	}

	@Test
	public void testAjoutFromageAuPanier() {
		FEN_Nos_Fromages fenetreFromages = new FEN_Nos_Fromages();
		FEN_Panier_Fromages fenPanier = new FEN_Panier_Fromages(fenetreFromages);
		fenPanier.ajouterAuPanier(this.unFromage.getDésignation() + " - " + 
				this.unFromage.getArticles().get(0).getClé(), 0, 0, 0);
		assertTrue(fenPanier.getTable().getRowCount() == 1);
	}
	
	@Test
	public void testSupprimerArticleDuPanier() {
	    FEN_Nos_Fromages fenetreFromages = new FEN_Nos_Fromages();
	    FEN_Panier_Fromages fenPanier = new FEN_Panier_Fromages(fenetreFromages);
	    for (int i = 1; i <= 5; i++) {
	    	fenPanier.ajouterAuPanier(this.unFromage.getDésignation() + " - " + 
					this.unFromage.getArticles().get(0).getClé(), 0, 0, 0);
	    }
	    JButton boutonVider = fenPanier.getBoutonVider();
	    boutonVider.doClick();
	    assertTrue("Le panier devrait être vide après avoir vidé.", fenPanier.estPanierVide());
	}

}
