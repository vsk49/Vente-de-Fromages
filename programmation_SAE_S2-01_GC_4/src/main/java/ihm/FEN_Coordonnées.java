package ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;

public class FEN_Coordonnées extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JTextField textFieldAdresse1;
    private JTextField textFieldAdresse2;
    private JTextField textFieldCodePostal;
    private JTextField textFieldVille;
    private JTextField textFieldTelephone;
    private JTextField textFieldMail;
    private final ButtonGroup paymentGroup = new ButtonGroup();
    private final ButtonGroup newsletterGroup = new ButtonGroup();
    private FEN_Panier_Fromages fenPanier;
    private FEN_Facture fenFacture;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FEN_Coordonnées frame = new FEN_Coordonnées(null);
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
    public FEN_Coordonnées(FEN_Panier_Fromages fenPanier) {
    	
    	this.fenPanier = fenPanier;
    	this.fenFacture = new FEN_Facture(this, fenPanier);
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 612, 568);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelNord = new JPanel();
        contentPane.add(panelNord, BorderLayout.NORTH);
        panelNord.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblCoordonnees = new JLabel("Vos Coordonnées");
        lblCoordonnees.setFont(new Font("Segoe Print", Font.BOLD, 30));
        panelNord.add(lblCoordonnees);

        JPanel panelCentre = new JPanel();
        contentPane.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(new BorderLayout(0, 0));

        JPanel panelForm = new JPanel();
        panelCentre.add(panelForm, BorderLayout.NORTH);
        panelForm.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panelLabels = new JPanel();
        panelForm.add(panelLabels);
        panelLabels.setLayout(new GridLayout(0, 1, 0, 10));

        JLabel lblNom = new JLabel("Nom :");
        lblNom.setFont(new Font("Arial Nova Cond", Font.BOLD, 11));
        panelLabels.add(lblNom);

        JLabel lblPrenom = new JLabel("Prénom :");
        lblPrenom.setFont(new Font("Arial Nova Cond", Font.BOLD, 11));
        panelLabels.add(lblPrenom);

        JLabel lblAdresse1 = new JLabel("Adresse 1 :");
        lblAdresse1.setFont(new Font("Arial Nova Cond", Font.BOLD, 11));
        panelLabels.add(lblAdresse1);

        JLabel lblAdresse2 = new JLabel("Adresse 2 :");
        lblAdresse2.setFont(new Font("Arial Nova Cond", Font.BOLD, 11));
        panelLabels.add(lblAdresse2);

        JLabel lblCodePostal = new JLabel("Code postal :");
        lblCodePostal.setFont(new Font("Arial Nova Cond", Font.BOLD, 11));
        panelLabels.add(lblCodePostal);

        JLabel lblVille = new JLabel("Ville :");
        lblVille.setFont(new Font("Arial Nova Cond", Font.BOLD, 11));
        panelLabels.add(lblVille);

        JLabel lblTelephone = new JLabel("Téléphone :");
        lblTelephone.setFont(new Font("Arial Nova Cond", Font.BOLD, 11));
        panelLabels.add(lblTelephone);

        JLabel lblMail = new JLabel("Mail :");
        lblMail.setFont(new Font("Arial Nova Cond", Font.BOLD, 10));
        panelLabels.add(lblMail);

        JPanel panelFields = new JPanel();
        panelForm.add(panelFields);
        GridBagLayout gbl_panelFields = new GridBagLayout();
        gbl_panelFields.columnWidths = new int[]{300};
        gbl_panelFields.rowHeights = new int[]{19, 19, 19, 19, 19, 19, 19, 19, 0};
        gbl_panelFields.columnWeights = new double[]{0.0};
        gbl_panelFields.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelFields.setLayout(gbl_panelFields);

        textFieldNom = new JTextField();
        GridBagConstraints gbc_textFieldNom = new GridBagConstraints();
        gbc_textFieldNom.fill = GridBagConstraints.BOTH;
        gbc_textFieldNom.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNom.gridx = 0;
        gbc_textFieldNom.gridy = 0;
        panelFields.add(textFieldNom, gbc_textFieldNom);
        textFieldNom.setColumns(10);

        textFieldPrenom = new JTextField();
        GridBagConstraints gbc_textFieldPrenom = new GridBagConstraints();
        gbc_textFieldPrenom.fill = GridBagConstraints.BOTH;
        gbc_textFieldPrenom.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldPrenom.gridx = 0;
        gbc_textFieldPrenom.gridy = 1;
        panelFields.add(textFieldPrenom, gbc_textFieldPrenom);
        textFieldPrenom.setColumns(10);

        textFieldAdresse1 = new JTextField();
        textFieldAdresse1.setColumns(10);
        GridBagConstraints gbc_textFieldAdresse1 = new GridBagConstraints();
        gbc_textFieldAdresse1.fill = GridBagConstraints.BOTH;
        gbc_textFieldAdresse1.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldAdresse1.gridx = 0;
        gbc_textFieldAdresse1.gridy = 2;
        panelFields.add(textFieldAdresse1, gbc_textFieldAdresse1);

        textFieldAdresse2 = new JTextField();
        textFieldAdresse2.setColumns(10);
        GridBagConstraints gbc_textFieldAdresse2 = new GridBagConstraints();
        gbc_textFieldAdresse2.fill = GridBagConstraints.BOTH;
        gbc_textFieldAdresse2.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldAdresse2.gridx = 0;
        gbc_textFieldAdresse2.gridy = 3;
        panelFields.add(textFieldAdresse2, gbc_textFieldAdresse2);

        textFieldCodePostal = new JTextField();
        textFieldCodePostal.setColumns(10);
        GridBagConstraints gbc_textFieldCodePostal = new GridBagConstraints();
        gbc_textFieldCodePostal.fill = GridBagConstraints.BOTH;
        gbc_textFieldCodePostal.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCodePostal.gridx = 0;
        gbc_textFieldCodePostal.gridy = 4;
        panelFields.add(textFieldCodePostal, gbc_textFieldCodePostal);

        textFieldVille = new JTextField();
        textFieldVille.setColumns(10);
        GridBagConstraints gbc_textFieldVille = new GridBagConstraints();
        gbc_textFieldVille.fill = GridBagConstraints.BOTH;
        gbc_textFieldVille.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldVille.gridx = 0;
        gbc_textFieldVille.gridy = 5;
        panelFields.add(textFieldVille, gbc_textFieldVille);

        textFieldTelephone = new JTextField();
        textFieldTelephone.setColumns(10);
        GridBagConstraints gbc_textFieldTelephone = new GridBagConstraints();
        gbc_textFieldTelephone.fill = GridBagConstraints.BOTH;
        gbc_textFieldTelephone.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldTelephone.gridx = 0;
        gbc_textFieldTelephone.gridy = 6;
        panelFields.add(textFieldTelephone, gbc_textFieldTelephone);

        textFieldMail = new JTextField();
        textFieldMail.setColumns(10);
        GridBagConstraints gbc_textFieldMail = new GridBagConstraints();
        gbc_textFieldMail.fill = GridBagConstraints.BOTH;
        gbc_textFieldMail.gridx = 0;
        gbc_textFieldMail.gridy = 7;
        panelFields.add(textFieldMail, gbc_textFieldMail);

        JPanel panelSud = new JPanel();
        contentPane.add(panelSud, BorderLayout.SOUTH);
        panelSud.setLayout(new BorderLayout(0, 0));

        JPanel panelPayment = new JPanel();
        panelPayment.setFont(new Font("Arial", Font.BOLD, 13));
        panelPayment.setBorder(new TitledBorder(new LineBorder(new Color(255, 153, 0), 3), "Moyen de payement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 153, 51)));
        panelSud.add(panelPayment, BorderLayout.NORTH);

        JRadioButton rdbtnCarteCredit = new JRadioButton("Carte de crédit");
        paymentGroup.add(rdbtnCarteCredit);
        rdbtnCarteCredit.setFont(new Font("Arial", Font.BOLD, 13));
        panelPayment.add(rdbtnCarteCredit);

        JRadioButton rdbtnPaypal = new JRadioButton("Paypal");
        paymentGroup.add(rdbtnPaypal);
        rdbtnPaypal.setFont(new Font("Arial", Font.BOLD, 13));
        panelPayment.add(rdbtnPaypal);

        JRadioButton rdbtnCheque = new JRadioButton("Payement par chèque");
        paymentGroup.add(rdbtnCheque);
        rdbtnCheque.setFont(new Font("Arial", Font.BOLD, 13));
        panelPayment.add(rdbtnCheque);

        JPanel panelNewsletter = new JPanel();
        panelNewsletter.setFont(new Font("Alef", Font.BOLD, 14));
        panelNewsletter.setBorder(new TitledBorder(new LineBorder(new Color(255, 153, 0), 3), "Abonnement \u00E0 notre NewsLetter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 153, 51)));
        panelSud.add(panelNewsletter, BorderLayout.CENTER);

        JRadioButton rdbtnOui = new JRadioButton("Oui");
        newsletterGroup.add(rdbtnOui);
        rdbtnOui.setFont(new Font("Arial", Font.BOLD, 13));
        panelNewsletter.add(rdbtnOui);

        JRadioButton rdbtnNon = new JRadioButton("Non");
        newsletterGroup.add(rdbtnNon);
        rdbtnNon.setFont(new Font("Arial", Font.BOLD, 13));
        panelNewsletter.add(rdbtnNon);

        JPanel panelBoutons = new JPanel();
        panelSud.add(panelBoutons, BorderLayout.SOUTH);
        panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        fermerFicheClient(btnAnnuler);
        btnAnnuler.setBorder(UIManager.getBorder("Button.border"));
        btnAnnuler.setFont(new Font("Arial", Font.BOLD, 15));
        panelBoutons.add(btnAnnuler);

        JButton btnOk = new JButton("Ok");
        btnOk.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnOk.setHorizontalAlignment(SwingConstants.RIGHT);
        btnOk.setFont(new Font("Arial", Font.BOLD, 15));
        ouvrirFacture(fenPanier, btnOk);
        panelBoutons.add(btnOk);
    }

	private void fermerFicheClient(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
	}

	private void ouvrirFacture(FEN_Panier_Fromages fenPanier, JButton btnOk) {
		btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validerChamps()) {
                	String nomPrenom = textFieldNom.getText() + " " + textFieldPrenom.getText();
                	String adresse = "Adresse : " + textFieldAdresse1.getText() + ", " +  
                			textFieldAdresse2.getText() + ", " + textFieldCodePostal.getText()
                			+ ", " + textFieldVille.getText();
                	String telephone = "Téléphone : " + textFieldTelephone.getText();
                	String mail = "Mail : " + textFieldMail.getText();
                    JOptionPane.showMessageDialog(null, "Informations enregistrées avec succès !");
                    fenFacture.setVisible(true);
                    fenFacture.afficherInformationsPersonnelles(nomPrenom, adresse, telephone, mail);
                    fenFacture.afficherArticles(fenPanier.getTable());
                    fenFacture.afficherPrixTotal(fenPanier.getPrixPanier());
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
	}

    private boolean validerChamps() {
        return !textFieldNom.getText().isEmpty() && !textFieldPrenom.getText().isEmpty() &&
               !textFieldAdresse1.getText().isEmpty() && !textFieldCodePostal.getText().isEmpty() &&
               !textFieldVille.getText().isEmpty() && !textFieldTelephone.getText().isEmpty() &&
               !textFieldMail.getText().isEmpty();
    }
    
    public FEN_Panier_Fromages getPanierClient() {
    	return this.fenPanier;
    }
    
}
