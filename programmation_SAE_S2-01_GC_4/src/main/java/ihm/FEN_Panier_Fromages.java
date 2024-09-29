package ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import modele.Article;

public class FEN_Panier_Fromages extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSousTotal;
	private JTextField textFieldExpedition;
	private JTextField textFieldTotal;
	private DefaultTableModel tableModel;
	private FEN_Description fenDescription;
	private FEN_Nos_Fromages fenAccueil;
	private JTable tablePanier;
	private JComboBox<String> comboBoxColis;
	private FEN_Facture fenFacture;
	private FEN_Coordonnées fenInfo;
	private JScrollPane scrollPane;
	private JButton boutonVider;
	private JButton boutonRetour;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FEN_Panier_Fromages frame = new FEN_Panier_Fromages(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FEN_Panier_Fromages(FEN_Nos_Fromages fenAccueil) {

		this.fenAccueil = fenAccueil;
		this.fenInfo = new FEN_Coordonnées(this);
		this.fenFacture = new FEN_Facture(this.fenInfo, this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNord = new JPanel();
		contentPane.add(panelNord, BorderLayout.NORTH);
		panelNord.setLayout(new BorderLayout(0, 0));

		JLabel labelPanier = new JLabel("Votre Panier");
		labelPanier.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 24));
		labelPanier.setHorizontalAlignment(SwingConstants.CENTER);
		panelNord.add(labelPanier, BorderLayout.CENTER);

		JButton boutonCalcul = new JButton("Recalculer le panier");
		panelNord.add(boutonCalcul, BorderLayout.EAST);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tablePanier = new JTable() {
			private static final long serialVersionUID = 1L;
			
			private int getArticleMaxQuantity(int row) {
			    String articleKey = (String) getValueAt(row, 0);
			    String[] parts = articleKey.split(" - ");
			    if (parts.length < 2) return 0;
			    String designation = parts[0];
			    String cle = parts[1];
			    Article article = FEN_Nos_Fromages.mesArticles.getArticle(designation, cle);
			    return article != null ? article.getQuantitéEnStock() + (Integer) getValueAt(row, 2) : 0;
			}


			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 2; // Seule la colonne Quantité est éditable
			}

			@Override
			public TableCellEditor getCellEditor(int row, int column) {
			    if (column == 2) {  // Supposons que la colonne des quantités est la deuxième colonne
			        int maxQuantity = getArticleMaxQuantity(row);  // Assurez-vous que cette méthode renvoie la bonne quantité maximale
			        return new SpinnerEditor(maxQuantity);
			    }
			    return super.getCellEditor(row, column);
			}


			@Override
			public void setValueAt(Object aValue, int row, int column) {
			    if (column == 2) {  // Supposons que la colonne des quantités est la deuxième colonne
			        int nouvelleQuantite = (Integer) aValue;
			        String[] articleDesignationKey = ((String)getValueAt(row, 0)).split(" - ");
			        String designation = articleDesignationKey[0];
			        String cle = articleDesignationKey[1];
			        float prix = (Float) getValueAt(row, 1);
			        int originalQuantity = (Integer) getValueAt(row, 2);
			        Article a = FEN_Nos_Fromages.mesArticles.getArticle(designation, cle);

			        if (nouvelleQuantite > a.getQuantitéEnStock() + originalQuantity) {
			            JOptionPane.showMessageDialog(null, "La quantité demandée dépasse le stock disponible.", "Erreur de stock", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        a.setQuantitéEnStock(a.getQuantitéEnStock() - (nouvelleQuantite - originalQuantity));  // Mise à jour du stock
			        float total = nouvelleQuantite * prix;
			        super.setValueAt(nouvelleQuantite, row, 2);
			        super.setValueAt(total, row, 3);
			    } else {
			        super.setValueAt(aValue, row, column);
			    }
			}

		};

		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Article", "Prix", "Quantite", "Total" });
		tablePanier.setModel(tableModel);
		scrollPane.setViewportView(tablePanier);

		JPanel panelSud = new JPanel();
		contentPane.add(panelSud, BorderLayout.SOUTH);
		panelSud.setLayout(new BorderLayout(0, 0));

		JPanel panelInformations = new JPanel();
		panelSud.add(panelInformations, BorderLayout.NORTH);
		panelInformations.setLayout(new BorderLayout(0, 0));

		JPanel panelLivraison = new JPanel();
		panelLivraison.setBorder(
				new TitledBorder(null, "Transporteur", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panelInformations.add(panelLivraison, BorderLayout.WEST);
		panelLivraison.setLayout(new BorderLayout(0, 0));

		JPanel panelRemarque = new JPanel();
		panelLivraison.add(panelRemarque, BorderLayout.NORTH);

		JLabel labelRemarque = new JLabel("Frais de ports offerts a partir de 120 €");
		panelRemarque.add(labelRemarque);

		JPanel panelColis = new JPanel();
		panelLivraison.add(panelColis, BorderLayout.SOUTH);

		JLabel labelIcon = new JLabel("");
		panelColis.add(labelIcon);

		comboBoxColis = new JComboBox<>();
		comboBoxColis.setModel(new DefaultComboBoxModel<>(new String[] { "Colissimo", "Chronorelais", "Chronofresh" }));
		modifierPrixTotal();
		panelColis.add(comboBoxColis);

		JPanel panelPrix = new JPanel();
		panelInformations.add(panelPrix, BorderLayout.EAST);
		panelPrix.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelSousTotal = new JPanel();
		panelPrix.add(panelSousTotal);

		JLabel labelSousTotal = new JLabel("Sous-total :  ");
		panelSousTotal.add(labelSousTotal);

		textFieldSousTotal = new JTextField();
		panelSousTotal.add(textFieldSousTotal);
		textFieldSousTotal.setColumns(10);

		JPanel panelExpedition = new JPanel();
		panelPrix.add(panelExpedition);

		JLabel labelExpedition = new JLabel("Expedition : ");
		panelExpedition.add(labelExpedition);

		textFieldExpedition = new JTextField();
		panelExpedition.add(textFieldExpedition);
		textFieldExpedition.setColumns(10);

		JPanel panelTotal = new JPanel();
		panelPrix.add(panelTotal);

		JLabel labelTotal = new JLabel(" TOTAL :      ");
		panelTotal.add(labelTotal);

		textFieldTotal = new JTextField();
		panelTotal.add(textFieldTotal);
		textFieldTotal.setColumns(10);

		JPanel panelBoutons = new JPanel();
		panelSud.add(panelBoutons, BorderLayout.SOUTH);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton boutonValider = new JButton("Valider le panier");
		ouvrirFicheClient(boutonValider);
		panelBoutons.add(boutonValider);

		boutonVider = new JButton("Vider le panier");
		viderPanier(boutonVider);
		panelBoutons.add(boutonVider);

		boutonRetour = new JButton("Continuer les achats");
		continuerLesAchats(fenAccueil, boutonRetour);
		panelBoutons.add(boutonRetour);

		recalculerPanier(boutonCalcul);
	}

	public void viderPanier(JButton boutonVider) {
		boutonVider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) tablePanier.getModel();
				tableModel.setRowCount(0);
				mettreAJourTotalPanier();
			}
		});
	}

	private void continuerLesAchats(FEN_Nos_Fromages fenAccueil, JButton boutonRetour) {
		boutonRetour.addActionListener(e -> {
			boolean success = true;
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				String articleKey = (String) tableModel.getValueAt(i, 0);
				int quantite = (Integer) tableModel.getValueAt(i, 2);
				float prix = (Float) tableModel.getValueAt(i, 1);
				
				// Récupération des informations existantes pour ne pas augmenter de nouveau le stock
				int quantitePrecedente = (Integer) tableModel.getValueAt(i, 2);
				if (quantite != quantitePrecedente) {
					updateArticleQuantite(i, articleKey, quantite, prix);
				}
			}

			if (success) {
				mettreAJourTotalPanier();
				dispose(); // Fermez la fenêtre seulement si la mise à jour du stock est réussie
				if (fenDescription != null) {
					fenDescription.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour du stock.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void updateArticleQuantite(int row, String articleKey, int nouvelleQuantite, float prix) {
	    String[] parts = articleKey.split(" - ");  // Supposons que la clé est de la forme "Designation - Clé"
	    if (parts.length < 2) return;  // Vérifiez que la clé est correctement formée
	    String designation = parts[0];
	    String cle = parts[1];

	    Article a = FEN_Nos_Fromages.mesArticles.getArticle(designation, cle);
	    if (a == null) {
	        JOptionPane.showMessageDialog(this, "Article non trouvé dans le stock!");
	        return;
	    }

	    if (a.getQuantitéEnStock() <= 0) {
	        JOptionPane.showMessageDialog(this, "L'article " + designation + " n'est plus disponible.", "Erreur de stock", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int quantitePrecedente = (Integer) tableModel.getValueAt(row, 2);
	    int difference = nouvelleQuantite - quantitePrecedente;

	    if (nouvelleQuantite > a.getQuantitéEnStock() + quantitePrecedente) {
	        JOptionPane.showMessageDialog(this, "Quantité supérieure à celle en stock !");
	        return;
	    }

	    a.setQuantitéEnStock(a.getQuantitéEnStock() - difference);  // Mise à jour du stock

	    float total = nouvelleQuantite * prix;
	    tableModel.setValueAt(nouvelleQuantite, row, 2);
	    tableModel.setValueAt(total, row, 3);
	    mettreAJourTotalPanier();
	}

	private void recalculerPanier(JButton boutonCalcul) {
		boutonCalcul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mettreAJourTotalPanier();
			}
		});
	}

	private void modifierPrixTotal() {
	    comboBoxColis.addActionListener(e -> updateFraisExpedition());
	}

	private void ouvrirFicheClient(JButton boutonValider) {
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estPanierVide()) {
					JOptionPane.showMessageDialog(null, "Votre panier est vide! ", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					FEN_Coordonnées fen = fenInfo;
					fen.setVisible(true);
				}
			}
		});
	}

	public void ajouterAuPanier(String articleKey, float prix, int quantite, float total) {
	    String[] parts = articleKey.split(" - ");
	    String designation;
	    String cle;
	    if (parts.length < 2) {
	        designation = parts[0];
	        cle = "";
	    } else {
	    	designation = parts[0];
	    	cle = parts[1];
	    }
	    Article a = FEN_Nos_Fromages.mesArticles.getArticle(designation, cle);
	    if (a == null) {
	        JOptionPane.showMessageDialog(null, "L'article spécifié n'existe pas.", "Erreur de stock", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (a.getQuantitéEnStock() <= 0) {
	        JOptionPane.showMessageDialog(null, "L'article " + designation + " n'est plus disponible.", "Erreur de stock", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Ajoute l'article au panier
	    tableModel.addRow(new Object[]{articleKey, prix, quantite, total});
	    revalidate();
	    repaint();
	    mettreAJourTotalPanier();
	  
	    // Réduit le stock de l'article
	    a.setQuantitéEnStock(a.getQuantitéEnStock() - quantite);
	}

	public void setFEN_Description(FEN_Description fenDescription) {
		this.fenDescription = fenDescription;
	}

	public float getPrixTotal() {
		float totalPrix = 0f;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			totalPrix += (float) tableModel.getValueAt(i, 3);
		}
		return totalPrix;
	}

	private void mettreAJourTotalPanier() {
		float sousTotal = getPrixTotal();
		float fraisExpedition = calculerFraisDePort(sousTotal, (String) comboBoxColis.getSelectedItem());
		float total = sousTotal + fraisExpedition;

		textFieldSousTotal.setText(String.format("%.2f€", sousTotal));
		textFieldExpedition.setText(String.format("%.2f€", fraisExpedition));
		textFieldTotal.setText(String.format("%.2f€", total));

		fenAccueil.mettreAJourBoutonPanier(sousTotal);
	}

	private void updateFraisExpedition() {
	    float sousTotal = getPrixTotal();
	    float fraisExpedition = calculerFraisDePort(sousTotal, (String) comboBoxColis.getSelectedItem());
	    textFieldExpedition.setText(String.format("%.2f€", fraisExpedition));
	    mettreAJourTotalPanier(); // Ensure this is called to reflect the new total including shipping.
	}

	private float calculerFraisDePort(float totalCommande, String transporteur) {
		switch (transporteur) {
		case "Colissimo":
		case "Chronorelais":
			if (totalCommande < 60)
				return 14.90f;
			if (totalCommande < 90)
				return 9.90f;
			if (totalCommande < 120)
				return 4.90f;
			return 0f;
		case "Chronofresh":
			if (totalCommande < 50)
				return 23.80f;
			if (totalCommande < 80)
				return 17.80f;
			if (totalCommande < 120)
				return 9.90f;
			return 0f;
		default:
			return 0f;
		}
	}

	public class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		final JSpinner spinner = new JSpinner();

	    public SpinnerEditor(int maxQuantity) {
	        spinner.setModel(new SpinnerNumberModel(0, 0, maxQuantity, 1));
	        spinner.addChangeListener(e -> {
	            int row = tablePanier.getSelectedRow();
	            if (row != -1) {
	                int newQuantity = (int) spinner.getValue();
	                String articleKey = (String) tableModel.getValueAt(row, 0);
	                float price = (float) tableModel.getValueAt(row, 1);
	                updateArticleQuantite(row, articleKey, newQuantity, price);
	            }
	        });
	    }

	    @Override
	    public Object getCellEditorValue() {
	        return spinner.getValue();
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        spinner.setValue(value);
	        return spinner;
	    }
	}

	public DefaultTableModel getTable() {
		return this.tableModel;
	}

	public String[] getPrixPanier() {
		if (this.estPanierVide()) {
			return new String[] { "0,00 €", "0,00 €", "0,00 €" };
		} else {
			String sousTotal = textFieldSousTotal.getText();
			String fraisLivraison = textFieldExpedition.getText();
			String prixTotal = textFieldTotal.getText();
			return new String[] { sousTotal, fraisLivraison, prixTotal };
		}

	}

	public FEN_Facture getFenFacture() {
		return this.fenFacture;
	}

	public boolean estPanierVide() {
		return tablePanier.getModel().getRowCount() == 0;
	}

	public JButton getBoutonVider() {
		return this.boutonVider;
	}

	public JComboBox<String> getComboBoxColis() {
		return this.comboBoxColis;
	}

	public JButton getBoutonRetour() {
		return this.boutonRetour;
	}

}
