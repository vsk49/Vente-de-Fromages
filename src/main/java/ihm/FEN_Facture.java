package ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FEN_Facture extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FEN_Panier_Fromages fenPanier;
	private FEN_Coordonnées fenInfo;
	private JLabel lbl_prenom_nom;
	private JLabel lbl_adresse;
	private JLabel lbl_téléphone;
	private JLabel lbl_mail;
	private JTable table;
	private JLabel labelSousTotal;
	private JLabel labelFraisLivraison;
	private JLabel labelPrixTotal;
	private FEN_Imprimer fenImprimer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FEN_Facture frame = new FEN_Facture(null, null);
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
	public FEN_Facture(FEN_Coordonnées fenInfo, FEN_Panier_Fromages fenPanier) {

		this.fenInfo = fenInfo;
		this.fenPanier = fenPanier;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 638);
		contentPane = new JPanel(new BorderLayout(5, 5));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Header panel
		JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titre = new JLabel("Votre facture");
		titre.setForeground(new Color(210, 105, 30));
		titre.setFont(new Font("Segoe Script", Font.BOLD, 24));
		JLabel logoFacture = new JLabel(new ImageIcon("C:\\Users\\Nolan\\Pictures\\logoFacture2.png"));
		header.add(logoFacture);
		header.add(titre);
		contentPane.add(header, BorderLayout.NORTH);

		// Central panel for customer details and table
		JPanel centralPanel = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		centralPanel.add(scrollPane, BorderLayout.CENTER);

		// Details Panel inside central panel
		JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 5, 5)); // Using GridLayout for auto-spacing
		lbl_prenom_nom = new JLabel("Nom et prénom: John Doe");
		lbl_adresse = new JLabel("Adresse : 123 Rue de Exemple, 75000 Paris");
		lbl_téléphone = new JLabel("Téléphone : 0123456789");
		lbl_mail = new JLabel("Mail : john.doe@example.com");
		detailsPanel.add(lbl_prenom_nom);
		detailsPanel.add(lbl_adresse);
		detailsPanel.add(lbl_téléphone);
		detailsPanel.add(lbl_mail);
		centralPanel.add(detailsPanel, BorderLayout.NORTH);

		// Adding the table to the central panel
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Produit(s)", "Prix Unitaire", "Quantité", "Prix TTC" }));
		scrollPane.setViewportView(table);

		// Bottom panel for totals and buttons
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel totalsPanel = new JPanel(new GridLayout(3, 1));
		labelSousTotal = new JLabel("");
		labelFraisLivraison = new JLabel("");
		labelPrixTotal = new JLabel("");
		totalsPanel.add(labelSousTotal);
		totalsPanel.add(labelFraisLivraison);
		totalsPanel.add(labelPrixTotal);
		bottomPanel.add(totalsPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenImprimer = new FEN_Imprimer();
				fenImprimer.setVisible(true);
			}
		});
		JButton btnQuitter = new JButton("Quitter");
		buttonPanel.add(btnImprimer);
		buttonPanel.add(btnQuitter);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		contentPane.add(centralPanel, BorderLayout.CENTER);

		// Listeners remain unchanged
		btnQuitter.addActionListener(e -> dispose());
	}

	public void afficherInformationsPersonnelles(String prenomNom, String adresse, String telephone, String mail) {
		this.lbl_prenom_nom.setText(prenomNom);
		this.lbl_adresse.setText(adresse);
		this.lbl_téléphone.setText(telephone);
		this.lbl_mail.setText(mail);
	}

	public void afficherArticles(DefaultTableModel tablePanier) {
		for (int i = 0; i < tablePanier.getRowCount(); i++) {
			String produit = (String) tablePanier.getValueAt(i, 0);
			String prixUnitaire = String.format("%.2f", (float)tablePanier.getValueAt(i, 1)) + " €";
			String quantite = String.format("%d", (int)tablePanier.getValueAt(i, 2));
			String PrixTTC = String.format("%.2f", (float)tablePanier.getValueAt(i, 3)) + " €";
			DefaultTableModel model = (DefaultTableModel) this.table.getModel();
			model.addRow(new Object[] {produit, prixUnitaire, quantite, PrixTTC});
		}
	}
	
	public void afficherPrixTotal(String[] informations) {
		this.labelSousTotal.setText("Sous-Total : " + informations[0]);
		this.labelFraisLivraison.setText("Frais de Livraison : " + informations[1]);
		this.labelPrixTotal.setText("Total TTC : " + informations[2]);
	}
	
	public FEN_Coordonnées getFicheClient() {
		return this.fenInfo;
	}
	
	public FEN_Panier_Fromages getPanierDeLaFacture() {
		return this.fenPanier;
	}
	
	public JTable getTableFacture() {
		return this.table;
	}
	
	public String getSousTotal() {
		return this.labelSousTotal.getText();
	}
	
	public String getFraisLivraison() {
		return this.labelFraisLivraison.getText();
	}
	
	public String getTotalTTC() {
		return this.labelPrixTotal.getText();
	}
	
}
