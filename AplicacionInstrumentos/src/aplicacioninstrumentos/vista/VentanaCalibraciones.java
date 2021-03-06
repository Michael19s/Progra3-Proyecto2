package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.modelo.Calibracion;
import aplicacioninstrumentos.modelo.GestorCalibracion;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


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
        pnlBotones = new JPanel();
        
        btnAgregar = new JButton();
        btnEliminar = new JButton();
        btnModificar = new JButton();
        btnClonar = new JButton();
        
        pnlBusqueda = new JPanel();
        
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
        String[] lvString = new String[] { "Numero de calibracion", "Instrumento", "Fecha", "Mediciones" };
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

        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridx = 0;
        gbc5.gridy = 3;
        gbc5.gridwidth = 2;
        gbc5.anchor = GridBagConstraints.NORTHWEST;
        gbc5.insets = new Insets(6, 10, 0, 0);
        pnlBusqueda.add(lblBusquedaAdv = new JLabel("Busqueda avanzada:"), gbc5);

        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.gridx = 3;
        gbc6.gridy = 4;
        gbc6.gridwidth = 5;
        gbc6.gridheight = 2;
        gbc6.anchor = GridBagConstraints.NORTHWEST;
        gbc6.insets = new Insets(5, 2, 0, 0);
        pnlBusqueda.add(cbxInstrumento = new JCheckBox("Instrumento"), gbc6);

        GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.gridx = 3;
        gbc7.gridy = 6;
        gbc7.gridwidth = 4;
        gbc7.gridheight = 2;
        gbc7.anchor = GridBagConstraints.NORTHWEST;
        gbc7.insets = new Insets(3, 2, 0, 0);
        pnlBusqueda.add(cbxFecha = new JCheckBox("Fecha"), gbc7);
        
        GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.gridx = 11;
        gbc8.gridy = 4;
        gbc8.gridwidth = 10;
        gbc8.ipadx = 156;
        gbc8.anchor = GridBagConstraints.NORTHWEST;
        gbc8.insets = new Insets(6, 2, 0, 0);
        pnlBusqueda.add(txtBusqTipo = new JTextField(), gbc8);
        
        GridBagConstraints gbc9 = new GridBagConstraints();
        gbc9.gridx = 7;
        gbc9.gridy = 6;
        gbc9.gridwidth = 14;
        gbc9.ipadx = 186;
        gbc9.anchor = GridBagConstraints.NORTHWEST;
        gbc9.insets = new Insets(4, 2, 0, 0);
        pnlBusqueda.add(txtBusqMinimo = new JTextField(), gbc9);

        GridBagConstraints gbc10 = new GridBagConstraints();
        gbc10.gridx = 3;
        gbc10.gridy = 8;
        gbc10.gridwidth = 9;
        gbc10.gridheight = 2;
        gbc10.anchor = GridBagConstraints.NORTHWEST;
        gbc10.insets = new Insets(3, 2, 9, 0);
        pnlBusqueda.add(cbxMediciones = new JCheckBox("Numero de Mediciones"), gbc10);
        
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.gridx = 20;
        gbc11.gridy = 8;
        gbc11.ipadx = 108;
        gbc11.anchor = GridBagConstraints.NORTHWEST;
        gbc11.insets = new Insets(4, 2, 0, 0);
        pnlBusqueda.add(jTextField1 = new JTextField(), gbc11);

        GridBagConstraints gbcBusqueda = new GridBagConstraints();
        gbcBusqueda.gridx = 1;
        gbcBusqueda.gridy = 2;
        gbcBusqueda.anchor = GridBagConstraints.NORTHWEST;
        gbcBusqueda.insets = new Insets(6, 10, 0, 0);
        getContentPane().add(pnlBusqueda, gbcBusqueda);

         try 
        {
            tblCalibraciones = new JTable(GestorCalibracion.obtenerInstancia().obtenerTabla(), Calibracion.obtenerDescripcion());
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.err.printf(ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        scpTabla.setViewportView(tblCalibraciones);

        GridBagConstraints gbcTabla = new GridBagConstraints();
        gbcTabla.gridx = 1;
        gbcTabla.gridy = 3;
        gbcTabla.gridwidth = 3;
        gbcTabla.fill = GridBagConstraints.BOTH;
        gbcTabla.ipadx = 754;
        gbcTabla.ipady = 115;
        gbcTabla.anchor = GridBagConstraints.NORTHWEST;
        gbcTabla.weightx = 1.0;
        gbcTabla.weighty = 1.0;
        gbcTabla.insets = new Insets(6, 10, 11, 10);
        getContentPane().add(scpTabla, gbcTabla);
        
        txtBusqueda.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                try 
                {
                    tblBusqueda = new JTable(GestorCalibracion.obtenerInstancia().obtenerTablaBusqueda((String)cmbBusqueda.getSelectedItem(), txtBusqueda.getText()), Calibracion.obtenerDescripcion());
                }
                catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
                {
                    System.err.printf(ex.getMessage());
                    JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                }
                scpTabla.setViewportView(tblBusqueda);
            }
        });
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
    
    private JCheckBox cbxFecha;
    private JCheckBox cbxInstrumento;
    private JCheckBox cbxMediciones;

    
    private JComboBox<String> cmbBusqueda;
    
    private JPanel jPanel1;
    private JPanel pnlBotones;
    private JPanel pnlBusqueda;
    
    
    private JLabel lblBuscar;
    private JLabel lblBusquedaAdv;
    
    private JScrollPane scpTabla;
    
    private JTable tblCalibraciones;
    private JTable tblBusqueda;
    
    private JTextField txtBusqMinimo;
    private JTextField txtBusqTipo;
    private JTextField jTextField1;
    private JTextField txtBusqueda;
}
