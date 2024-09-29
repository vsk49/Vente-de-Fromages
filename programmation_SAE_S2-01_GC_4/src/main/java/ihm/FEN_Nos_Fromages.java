package ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import modele.Fromage;
import modele.Fromages;
import modele.GenerationFromages;
import modele.TypeLait;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class FEN_Nos_Fromages extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static final Fromages mesArticles = GenerationFromages.générationBaseFromages();
	public static final List<Fromage> tousLesFromages = mesArticles.getFromages();
	public static final List<Fromage> fromagesVache = mesArticles.fromagesAuLaitDe(TypeLait.VACHE);
	public static final List<Fromage> fromagesChevre = mesArticles.fromagesAuLaitDe(TypeLait.CHEVRE);
	public static final List<Fromage> fromagesBrebis = mesArticles.fromagesAuLaitDe(TypeLait.BREBIS);
	private FEN_Panier_Fromages fenPanier;
	private JButton boutonPanier;
	private JList<String> listFromages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FEN_Nos_Fromages frame = new FEN_Nos_Fromages();
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
	public FEN_Nos_Fromages() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Documents\\Semestre_2\\SAE2.01\\product_1900218b.jpg"));
		setTitle("BlancJus");

		this.fenPanier = new FEN_Panier_Fromages(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNord = new JPanel();
		contentPane.add(panelNord, BorderLayout.NORTH);
		panelNord.setLayout(new BorderLayout(0, 0));

		JLabel labelTitre = new JLabel("Nos Fromages");
		labelTitre.setFont(new Font("Segoe Print", Font.BOLD, 18));
		labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
		panelNord.add(labelTitre);

		boutonPanier = new JButton("0,00€");
		ouvrirPanier();
		panelNord.add(boutonPanier, BorderLayout.EAST);

		JPanel panelCentre = new JPanel();
		contentPane.add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollFromages = new JScrollPane();
		panelCentre.add(scrollFromages, BorderLayout.CENTER);

		listFromages = new JList<>(this.extraireListeFromages(tousLesFromages));
		ouvrirDescriptionFromage(listFromages);
		listFromages.setModel(new javax.swing.AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return extraireListeFromages(tousLesFromages).length;
			}

			public String getElementAt(int i) {
				return extraireListeFromages(tousLesFromages)[i];
			}
		});
		scrollFromages.setViewportView(listFromages);

		JPanel panelSud = new JPanel();
		contentPane.add(panelSud, BorderLayout.SOUTH);
		panelSud.setLayout(new BorderLayout(0, 0));

		JPanel panelFiltre = new JPanel();
		panelFiltre
				.setBorder(new TitledBorder(null, "Filtre", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panelSud.add(panelFiltre, BorderLayout.CENTER);
		panelFiltre.setLayout(new BorderLayout(0, 0));

		JLabel labelFromage = new JLabel("");
		panelFiltre.add(labelFromage);

		JComboBox<String> comboBoxTypeFromage = new JComboBox<>();
		filtrerListeFromages(listFromages, comboBoxTypeFromage);
		comboBoxTypeFromage.setModel(
				new DefaultComboBoxModel<>(new String[] { "Tous les fromages", "Vache", "Chèvre", "Brebis" }));
		panelFiltre.add(comboBoxTypeFromage);

		JPanel panel = new JPanel();
		panelFiltre.add(panel, BorderLayout.EAST);

		JButton boutonQuitter = new JButton("Quitter");
		quitterApplication(boutonQuitter);
		panel.add(boutonQuitter);
	}

	private void ouvrirPanier() {
		boutonPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenPanier.setVisible(true);
				setVisible(false);
			}
		});
	}

	private void quitterApplication(JButton boutonQuitter) {
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void ouvrirDescriptionFromage(JList<String> listFromages) {
		listFromages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String fromageSelectionne = listFromages.getSelectedValue();
					Fromage selectedCheese = null;
					for (Fromage fromage : tousLesFromages) {
						if (fromage.getDésignation().equals(fromageSelectionne)) {
							selectedCheese = fromage;
							break;
						}
					}
					if (selectedCheese != null) {
						FEN_Description cheeseInfoWindow = new FEN_Description(selectedCheese, fenPanier,
								FEN_Nos_Fromages.this);
						cheeseInfoWindow.displayFromage(selectedCheese);
						cheeseInfoWindow.setVisible(true);
					}
				}
			}
		});
	}

	private void filtrerListeFromages(JList<String> listFromages, JComboBox<String> comboBoxTypeFromage) {
		comboBoxTypeFromage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedType = (String) comboBoxTypeFromage.getSelectedItem();
				String[] filteredFromageNamesArray;
				switch (selectedType) {
				case "Brebis":
					filteredFromageNamesArray = extraireListeFromages(fromagesBrebis);
					break;
				case "Vache":
					filteredFromageNamesArray = extraireListeFromages(fromagesVache);
					break;
				case "Chèvre":
					filteredFromageNamesArray = extraireListeFromages(fromagesChevre);
					break;
				default:
					filteredFromageNamesArray = extraireListeFromages(tousLesFromages);
				}
				listFromages.setModel(new javax.swing.AbstractListModel<String>() {
					private static final long serialVersionUID = 1L;

					public int getSize() {
						return filteredFromageNamesArray.length;
					}

					public String getElementAt(int i) {
						return filteredFromageNamesArray[i];
					}
				});
			}
		});
	}

	private String[] extraireListeFromages(List<Fromage> listeFromages) {
		List<String> nomFromages = new ArrayList<>();
		for (Fromage fromage : listeFromages) {
			nomFromages.add(fromage.getDésignation());
		}
		String[] tabNomFromages = nomFromages.toArray(new String[nomFromages.size()]);
		return tabNomFromages;
	}

	public void mettreAJourBoutonPanier(float totalPrix) {
		boutonPanier.setText(String.format("%.2f€", totalPrix));
	}
}
