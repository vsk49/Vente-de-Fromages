package modele;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ihm.FEN_Nos_Fromages;
import ihm.FEN_Panier_Fromages;

public class TestMiseAJourStocks {

    private List<Fromage> mesFromages = GenerationFromages.générationBaseFromages().getFromages(); 
    private List<Fromage> mesFromagesTest = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
        mesFromagesTest.addAll(mesFromages.subList(0, 5));
    }

    @After
    public void tearDown() throws Exception {
        mesFromagesTest.clear();
    }

    @Test
    public void testModificationStockApresAvoirModifieLePanier() {
        FEN_Panier_Fromages panier = new FEN_Panier_Fromages(new FEN_Nos_Fromages());
        for (Fromage f : mesFromagesTest) {
            panier.ajouterAuPanier(f.getDésignation() + " - " + f.getArticles().get(0).getClé(),
                f.getArticles().get(0).getPrixTTC(), 4, f.getArticles().get(0).getPrixTTC() * 4);
        }

        // Modifier la quantité de la première ligne de 4 à 5
        DefaultTableModel tableModel = panier.getTable();
        int initialQuantite = (int) tableModel.getValueAt(0, 2);
        tableModel.setValueAt(initialQuantite + 1, 0, 2);
        
        // Simule un clic sur le bouton de retour
        panier.getBoutonRetour().doClick();
        
        // Vérifie que le stock de l'article modifié a été mis à jour correctement
        Fromage f = mesFromagesTest.get(0);
        int expectedStock = f.getArticles().get(0).getQuantitéEnStock() - (initialQuantite + 1);
        int actualStock = f.getArticles().get(0).getQuantitéEnStock();
        assertEquals(expectedStock, actualStock);
    }

    @Test
    public void testModificationStockSansModifieLePanier() {
        FEN_Panier_Fromages panier = new FEN_Panier_Fromages(new FEN_Nos_Fromages());
        HashMap<String, Integer> initialStocks = new HashMap<>(); // Stocke les stocks initiaux pour comparaison

        for (Fromage f : mesFromagesTest) {
            String key = f.getDésignation() + " - " + f.getArticles().get(0).getClé();
            int initialStock = f.getArticles().get(0).getQuantitéEnStock();
            initialStocks.put(key, initialStock);
            panier.ajouterAuPanier(key, f.getArticles().get(0).getPrixTTC(), 4, 
                f.getArticles().get(0).getPrixTTC() * 4);
        }
        // Simule un clic sur le bouton de retour
        panier.getBoutonRetour().doClick();
        // Vérifie que le stock a été correctement diminué de 4 pour chaque fromage
        for (Fromage f : mesFromagesTest) {
            String key = f.getDésignation() + " - " + f.getArticles().get(0).getClé();
            int expectedStock = initialStocks.get(key) - 4;
            int actualStock = f.getArticles().get(0).getQuantitéEnStock();
            assertEquals(expectedStock, actualStock);
        }
    }
}
