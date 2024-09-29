package ihm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class FEN_Imprimer extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldCopies;
    private JTextField textField;
    private JTextField textField_1;

    public FEN_Imprimer() {
        setTitle("Imprimer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel panelGeneral = new JPanel(new BorderLayout());
        tabbedPane.addTab("Général", null, panelGeneral, null);

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new TitledBorder(null, "Service d'impression", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelGeneral.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(0, 3, 0, 0));

        topPanel.add(new JLabel("Nom :"));
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"HPCDC0AC (HP Officejet 5740 series)"});
        topPanel.add(comboBox);

        JButton btnProprietes = new JButton("Propriétés...");
        topPanel.add(btnProprietes);
        
                JLabel label_1 = new JLabel("Statut :");
                topPanel.add(label_1);
        JLabel label_2 = new JLabel("Acceptation des tâches");
        topPanel.add(label_2);
                
                JLabel lblNewLabel_1 = new JLabel("");
                topPanel.add(lblNewLabel_1);
        
                JLabel label_3 = new JLabel("Type :");
                topPanel.add(label_3);
        
                JLabel label_4 = new JLabel("");
                topPanel.add(label_4);
        
        JLabel label_5 = new JLabel("");
        topPanel.add(label_5);
        
        JLabel lblInfos = new JLabel("Infos :");
        topPanel.add(lblInfos);
        
        JLabel label_7 = new JLabel("");
        topPanel.add(label_7);
        
        JCheckBox chckbxImprimerDansUn = new JCheckBox("Imprimer dans un fichier");
        topPanel.add(chckbxImprimerDansUn);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        panelGeneral.add(bottomPanel, BorderLayout.SOUTH);

        JPanel panelPlageDImpression = new JPanel(new GridLayout(3, 2, 5, 5));
        panelPlageDImpression.setBorder(BorderFactory.createTitledBorder("Plage d'impression"));
        bottomPanel.add(panelPlageDImpression);
        
        JPanel panel = new JPanel();
        panelPlageDImpression.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        JRadioButton rdbtnTout = new JRadioButton("Tout");
        rdbtnTout.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnTout.setSelected(true);
        panel.add(rdbtnTout);
        
        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panelPlageDImpression.add(panel_1);
        
        JRadioButton rdbtnPages = new JRadioButton("Pages");
        panel_1.add(rdbtnPages);
        
        textField = new JTextField();
        panel_1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("A");
        panel_1.add(lblNewLabel);
        
        textField_1 = new JTextField();
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        JPanel panelCopies = new JPanel(new GridLayout(3, 1, 5, 5));
        panelCopies.setBorder(BorderFactory.createTitledBorder("Copies"));
        bottomPanel.add(panelCopies);

        JPanel copiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        copiesPanel.add(new JLabel("Nombre de copies :"));
        textFieldCopies = new JTextField("1", 5);
        copiesPanel.add(textFieldCopies);
        panelCopies.add(copiesPanel);

        JCheckBox chckbxCollationner = new JCheckBox("Collationner");
        chckbxCollationner.setSelected(true);
        panelCopies.add(chckbxCollationner);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        JButton btnImprimer = new JButton("Imprimer");
        buttonsPanel.add(btnImprimer);

        JButton btnAnnuler = new JButton("Annuler");
        buttonsPanel.add(btnAnnuler);

        JPanel panelMiseEnPage = new JPanel();
        tabbedPane.addTab("Mise en page", null, panelMiseEnPage, null);

        JPanel panelApparence = new JPanel();
        tabbedPane.addTab("Apparence", null, panelApparence, null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FEN_Imprimer frame = new FEN_Imprimer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}