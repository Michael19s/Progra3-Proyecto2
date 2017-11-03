package aplicacioninstrumentos.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class VentanaCalibraciones extends JFrame
{
       public VentanaCalibraciones() 
    {
        super("Mantenimiento de calibraciones");
        configurar();
    }
    
    private void configurar()
    {
        ajustarComponentes(getContentPane());
        setResizable(false);
        setSize(920, 400);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void ajustarComponentes(Container c)
    {
        jPanel1 = new JPanel();
        pnlBotones = new JPanel();
        btnAgregar = new JButton();
        btnEliminar = new JButton();
        btnModificar = new JButton();
        btnClonar = new JButton();
        pnlBusqueda = new JPanel();
        lblBuscar = new JLabel();
        cmbBusqueda = new JComboBox<>();
        txtBusqueda = new JTextField();
        btnBuscar = new JButton();
        lblBusquedaAdv = new JLabel();
        cbxTipo = new JCheckBox();
        cbxMinimo = new JCheckBox();
        txtBusqTipo = new JTextField();
        txtBusqMinimo = new JTextField();
        jCheckBox1 = new JCheckBox();
        jTextField1 = new JTextField();
        scpTabla = new JScrollPane();
        tblCalibraciones = new JTable();

        c.setLayout(new GridBagLayout());

        pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.LINE_AXIS));

        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/images/BotonAgregar.png")));
        pnlBotones.add(btnAgregar);

        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/images/BotonEliminar.png")));
        btnEliminar.setToolTipText("Eliminar registro");
        pnlBotones.add(btnEliminar);

        btnModificar.setIcon(new ImageIcon(getClass().getResource("/images/BotonEditar.png")));
        btnModificar.setToolTipText("Editar registro");
        pnlBotones.add(btnModificar);

        btnClonar.setIcon(new ImageIcon(getClass().getResource("/images/BotonCopiar.png")));
        btnClonar.setToolTipText("Clonar registro");
        pnlBotones.add(btnClonar);

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 1;
        gbcBotones.gridy = 1;
        gbcBotones.gridwidth = 2;
        gbcBotones.ipadx = 268;
        gbcBotones.anchor = GridBagConstraints.NORTHWEST;
        gbcBotones.insets = new Insets(11, 10, 0, 0);
        getContentPane().add(pnlBotones, gbcBotones);

        pnlBusqueda.setLayout(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.anchor = GridBagConstraints.NORTHWEST;
        gbc1.insets = new Insets(15, 10, 0, 0);
        pnlBusqueda.add(lblBuscar = new JLabel("Buscar:"), gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 3;
        gbc2.gridheight = 2;
        gbc2.anchor = GridBagConstraints.NORTHWEST;
        gbc2.insets = new Insets(12, 4, 0, 0);
        String[] lvString = new String[] { "Numero Calibracion", "Instrumento", "Fecha", "Mediciones" };
        pnlBusqueda.add(cmbBusqueda = new JComboBox<>(lvString), gbc2);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 7;
        gbc3.gridy = 0;
        gbc3.gridwidth = 15;
        gbc3.gridheight = 2;
        gbc3.ipadx = 226;
        gbc3.anchor = GridBagConstraints.NORTHWEST;
        gbc3.insets = new Insets(12, 8, 0, 0);
        pnlBusqueda.add(txtBusqueda = new JTextField(), gbc3);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 22;
        gbc4.gridy = 0;
        gbc4.gridheight = 3;
        gbc4.anchor = GridBagConstraints.NORTHWEST;
        gbc4.insets = new Insets(11, 14, 0, 0);
        pnlBusqueda.add(btnBuscar = new JButton("Buscar"), gbc4);

        lblBusquedaAdv.setText("Busqueda avanzada:");
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 3;
//        gridBagConstraints.gridwidth = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(6, 10, 0, 0);
//        pnlBusqueda.add(lblBusquedaAdv, gridBagConstraints);
//
//        cbxTipo.setText("Instrumento");
//        cbxTipo.addActionListener(new event.ActionListener() {
//            public void actionPerformed(event.ActionEvent evt) {
//                cbxTipoActionPerformed(evt);
//            }
//        });
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 3;
//        gridBagConstraints.gridy = 4;
//        gridBagConstraints.gridwidth = 5;
//        gridBagConstraints.gridheight = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(5, 2, 0, 0);
//        pnlBusqueda.add(cbxTipo, gridBagConstraints);
//
//        cbxMinimo.setText("Fecha");
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 3;
//        gridBagConstraints.gridy = 6;
//        gridBagConstraints.gridwidth = 4;
//        gridBagConstraints.gridheight = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(3, 2, 0, 0);
//        pnlBusqueda.add(cbxMinimo, gridBagConstraints);
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 11;
//        gridBagConstraints.gridy = 4;
//        gridBagConstraints.gridwidth = 10;
//        gridBagConstraints.ipadx = 156;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(6, 2, 0, 0);
//        pnlBusqueda.add(txtBusqTipo, gridBagConstraints);
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 7;
//        gridBagConstraints.gridy = 6;
//        gridBagConstraints.gridwidth = 14;
//        gridBagConstraints.ipadx = 186;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(4, 2, 0, 0);
//        pnlBusqueda.add(txtBusqMinimo, gridBagConstraints);
//
//        jCheckBox1.setText("Numero de Mediciones");
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 3;
//        gridBagConstraints.gridy = 8;
//        gridBagConstraints.gridwidth = 9;
//        gridBagConstraints.gridheight = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(3, 2, 9, 0);
//        pnlBusqueda.add(jCheckBox1, gridBagConstraints);
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 20;
//        gridBagConstraints.gridy = 8;
//        gridBagConstraints.ipadx = 108;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(4, 2, 0, 0);
//        pnlBusqueda.add(jTextField1, gridBagConstraints);
//
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(6, 10, 0, 0);
//        getContentPane().add(pnlBusqueda, gridBagConstraints);
//
//        tblInstrumentos.setModel(new table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null},
//                {null, null, null},
//                {null, null, null},
//                {null, null, null}
//            },
//            new String [] {
//                "Numero de calibracion", "Instrumento", "Numero de mediciones"
//            }
//        ) {
//            boolean[] canEdit = new boolean [] {
//                false, false, false
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit [columnIndex];
//            }
//        });
//        scpTabla.setViewportView(tblInstrumentos);
//
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 3;
//        gridBagConstraints.gridwidth = 3;
//        gridBagConstraints.fill = GridBagConstraints.BOTH;
//        gridBagConstraints.ipadx = 754;
//        gridBagConstraints.ipady = 115;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.weightx = 1.0;
//        gridBagConstraints.weighty = 1.0;
//        gridBagConstraints.insets = new Insets(6, 10, 11, 10);
//        getContentPane().add(scpTabla, gridBagConstraints);

        pack();
    }
    
    public void init()
    {
        setVisible(true);
    }
    
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnClonar;
    private JButton btnEliminar;
    private JButton btnModificar;
    
    private JCheckBox cbxMinimo;
    private JCheckBox cbxTipo;
    private JCheckBox jCheckBox1;

    
    private JComboBox<String> cmbBusqueda;
    
    private JPanel jPanel1;
    private JPanel pnlBotones;
    private JPanel pnlBusqueda;
    
    
    private JLabel lblBuscar;
    private JLabel lblBusquedaAdv;
    
    private JScrollPane scpTabla;
    
    private JTable tblCalibraciones;
    
    private JTextField txtBusqMinimo;
    private JTextField txtBusqTipo;
    private JTextField jTextField1;
    private JTextField txtBusqueda;
}
