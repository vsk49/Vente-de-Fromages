package ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import modele.Article;
import modele.Fromage;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class FEN_Description extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FEN_Panier_Fromages fenPanier;
	private FEN_Nos_Fromages fenAccueil;
	private Fromage f;
	private List<Article> articlesFromage;
	private JLabel labelNomFromage;
	private JLabel labelImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FEN_Description frame = new FEN_Description(null, null, null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FEN_Description(Fromage f, FEN_Panier_Fromages fenPanier, FEN_Nos_Fromages fenAccueil) {

		this.f = f;
		this.fenPanier = fenPanier;
		this.fenAccueil = fenAccueil;

		this.articlesFromage = f.getArticles();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelContenu = new JPanel();
		contentPane.add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelImage = new JPanel();
		panelContenu.add(panelImage);
		panelImage.setLayout(new BorderLayout(0, 0));

		labelNomFromage = new JLabel(f.getDésignation());
		labelNomFromage.setHorizontalAlignment(SwingConstants.CENTER);
		labelNomFromage.setFont(new Font("Segoe Script", Font.BOLD, 20));
		labelNomFromage.setVerticalAlignment(SwingConstants.TOP);
		panelImage.add(labelNomFromage, BorderLayout.NORTH);
		
		labelImage = new JLabel("");
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		panelImage.add(labelImage, BorderLayout.CENTER);

		JPanel panelDescription = new JPanel();
		panelDescription.setBorder(
				new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panelContenu.add(panelDescription);
		panelDescription.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelDescription.add(scrollPane, BorderLayout.CENTER);

		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setText(f.getDescription());
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaDescription);

		JPanel panelSud = new JPanel();
		contentPane.add(panelSud, BorderLayout.SOUTH);
		panelSud.setLayout(new BorderLayout(0, 0));

		JPanel panelStock = new JPanel();
		panelSud.add(panelStock, BorderLayout.NORTH);

		JComboBox<String> comboBoxTypeVente = new JComboBox<>();
		fixerTypeArticles(comboBoxTypeVente);
		panelStock.add(comboBoxTypeVente);

		JSpinner spinnerStock = new JSpinner();
		int itemIndex = comboBoxTypeVente.getSelectedIndex();
		spinnerStock.setModel(new SpinnerNumberModel(0, 0, articlesFromage.get(itemIndex).getQuantitéEnStock(), 1));
		configurerQuantiteEnStock(comboBoxTypeVente, spinnerStock);
		panelStock.add(spinnerStock);

		JPanel panelBouton = new JPanel();
		panelSud.add(panelBouton, BorderLayout.SOUTH);

		JButton boutonAjouterAuPanier = new JButton("Ajouter au panier");
		ajouterLigneDeCommande(f, fenPanier, comboBoxTypeVente, spinnerStock, boutonAjouterAuPanier);
		panelBouton.add(boutonAjouterAuPanier);

		JButton boutonAnnuler = new JButton("Annuler");
		revenirALaPageAccueil(boutonAnnuler);
		panelBouton.add(boutonAnnuler);
	}

	private void ajouterLigneDeCommande(Fromage f, FEN_Panier_Fromages fenPanier, JComboBox<String> comboBoxTypeVente,
			JSpinner spinnerStock, JButton boutonAjouterAuPanier) {
		boutonAjouterAuPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String articleSelectionne = (String) comboBoxTypeVente.getSelectedItem();
				int quantite = (int) spinnerStock.getValue();
				float prix = 0;
				String cleArticle = "";
				for (Article a : articlesFromage) {
					if (articleSelectionne.contains(a.getClé())) {
						prix = a.getPrixTTC();
						cleArticle = a.getClé();
					}
				}
				float total = prix * quantite;
				fenPanier.ajouterAuPanier(f.getDésignation() + " - " + cleArticle, prix, quantite, total);
				fenPanier.setFEN_Description(FEN_Description.this);
				fenPanier.setVisible(true);
			}
		});
	}

	private void configurerQuantiteEnStock(JComboBox<String> comboBoxTypeVente, JSpinner spinnerStock) {
		comboBoxTypeVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String articleSelectionne = (String) comboBoxTypeVente.getSelectedItem();
				for (Article a : articlesFromage) {
					if (articleSelectionne.contains(a.getClé())) {
						spinnerStock.setModel(new SpinnerNumberModel(0, 0, a.getQuantitéEnStock(), 1));
						break;
					}
				}
			}
		});
	}

	private void fixerTypeArticles(JComboBox<String> comboBoxTypeVente) {
		for (Article a : this.articlesFromage) {
			if (this.articlesFromage.size() == 1) {
				comboBoxTypeVente.addItem("A l'unité, Prix TTC : " + a.getPrixTTC() + "€");
			} else {
				comboBoxTypeVente.addItem(a.getClé() + ", Prix TTC : " + a.getPrixTTC() + "€");
			}
		}
	}

	private void revenirALaPageAccueil(JButton boutonAnnuler) {
		boutonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public FEN_Panier_Fromages getFEN_Panier_Fromages() {
		return this.fenPanier;
	}

	public FEN_Nos_Fromages getFEN_Nos_Fromages() {
		return this.fenAccueil;
	}

	public Fromage getFromageDeLaFenetre() {
		return this.f;
	}
	
	public void displayFromage(Fromage fromage) {
	    String imagePath = "src/main/resources/images/fromages/hauteur200/" + fromage.getNomImage() + ".jpg";
	    ImageIcon icon = new ImageIcon(imagePath);
	    labelImage.setIcon(icon); // Supposons que vous avez un JLabel nommé labelImage pour l'image
	}

}
