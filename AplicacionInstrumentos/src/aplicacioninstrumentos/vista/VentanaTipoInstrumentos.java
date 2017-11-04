package aplicacioninstrumentos.vista;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modeloinstrumentos.modeloa.GestorInstrumentos;
import modeloinstrumentos.modeloa.GestorTipoInstrumentos;
import modeloinstrumentos.modeloa.Instrumento;
import modeloinstrumentos.modeloa.TipoInstrumento;

public class VentanaTipoInstrumentos extends JFrame
{

    public VentanaTipoInstrumentos() 
    {
        super("Mantenimiento Tipo de Instrumento");
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
        
        tblTipoInstrumentos = new JTable();

        c.setLayout(new GridBagLayout());

        pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.LINE_AXIS));

        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/images/BotonAgregar.png")));
        btnAgregar.setToolTipText("Agregar registro");
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
        gbcBotones.gridx = 0;
        gbcBotones.gridy = 0;
        gbcBotones.gridwidth = 2;
        gbcBotones.ipadx = 268;
        gbcBotones.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbcBotones.insets = new Insets(11, 10, 0, 10);
        c.add(pnlBotones, gbcBotones);

        pnlBusqueda.setLayout(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(15, 10, 0, 0);
        pnlBusqueda.add(lblBuscar = new JLabel("Buscar:"), gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 3;
        gbc2.gridheight = 2;
        gbc2.insets = new Insets(12, 4, 0, 0);
        String[] lvString = new String[] { "Codigo", "Nombre", "Unidad de medicion" };
        pnlBusqueda.add(cmbBusqueda = new JComboBox<>(lvString), gbc2);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 3;
        gbc3.gridy = 0;
        gbc3.gridwidth = 6;
        gbc3.gridheight = 2;
        gbc3.ipadx = 226;
        gbc3.insets = new Insets(12, 0, 0, 0);
        pnlBusqueda.add(txtBusqueda = new JTextField(), gbc3);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 18;
        gbc4.gridy = 0;
        gbc4.gridheight = 3;
        gbc4.insets = new Insets(11, 14, 0, 10);
        pnlBusqueda.add(btnBuscar = new JButton("Buscar"), gbc4);

        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridx = 0;
        gbc5.gridy = 3;
        gbc5.gridwidth = 2;
        gbc5.insets = new Insets(6, 10, 0, 0);
        pnlBusqueda.add(lblBusquedaAdv = new JLabel("Busqueda avanzada:"), gbc5);

        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.gridx = 2;
        gbc6.gridy = 4;
        gbc6.gridheight = 2;
        gbc6.insets = new Insets(5, 2, 0, 0);
        pnlBusqueda.add(cbxNombre = new JCheckBox("Nombre"), gbc6);

        GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.gridx = 2;
        gbc7.gridy = 6;
        gbc7.gridwidth = 4;
        gbc7.gridheight = 2;
        gbc7.insets = new Insets(3, 2, 9, 0);
        pnlBusqueda.add(cbxUnidadMedicion = new JCheckBox("Unidad de medicion"), gbc7);
        
        GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.gridx = 3;
        gbc8.gridy = 4;
        gbc8.gridwidth = 5;
        gbc8.fill = GridBagConstraints.HORIZONTAL;
        gbc8.ipadx = 114;
        gbc8.insets = new Insets(6, 2, 0, 0);
        pnlBusqueda.add(txtBusqTipo = new JTextField(), gbc8);
        
        GridBagConstraints gbc9 = new GridBagConstraints();
        gbc9.gridx = 7;
        gbc9.gridy = 6;
        gbc9.fill = GridBagConstraints.HORIZONTAL;
        gbc9.ipadx = 102;
        gbc9.insets = new Insets(4, 2, 0, 0);
        pnlBusqueda.add(txtBusqMinimo = new JTextField(), gbc9);

        GridBagConstraints gbcBusqueda = new GridBagConstraints();
        gbcBusqueda.gridx = 0;
        gbcBusqueda.gridy = 1;
        gbcBusqueda.gridwidth = 2;
        gbcBusqueda.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbcBusqueda.insets = new Insets(6, 10, 0, 10);
        c.add(pnlBusqueda, gbcBusqueda);

        try 
        {
            tblTipoInstrumentos = new JTable(GestorTipoInstrumentos.obtenerInstancia().obtenerTabla(), TipoInstrumento.obtenerDescripcion());
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.err.printf(ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        scpTabla.setViewportView(tblTipoInstrumentos);
        
        GridBagConstraints gbcTabla = new GridBagConstraints();
        gbcTabla.gridx = 0;
        gbcTabla.gridy = 3;
        gbcTabla.gridwidth = 2;
        gbcTabla.fill = GridBagConstraints.BOTH;
        gbcTabla.ipadx = 473;
        gbcTabla.ipady = 109;
        gbcTabla.weightx = 1.0;
        gbcTabla.weighty = 1.0;
        gbcTabla.insets = new Insets(6, 10, 11, 10);
        c.add(scpTabla, gbcTabla);
        
        txtBusqueda.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                try 
                {
                    tblBusqueda = new JTable(GestorTipoInstrumentos.obtenerInstancia().obtenerTablaBusqueda((String)cmbBusqueda.getSelectedItem(), txtBusqueda.getText()), TipoInstrumento.obtenerDescripcion());
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
    
    private JCheckBox cbxNombre;
    private JCheckBox cbxUnidadMedicion;
    
    private JComboBox<String> cmbBusqueda;
    
    private JPanel jPanel1;
    private JPanel pnlBotones;
    private JPanel pnlBusqueda;
    
    private JLabel lblBuscar;
    private JLabel lblBusquedaAdv;
    
    private JScrollPane scpTabla;
    
    private JTable tblTipoInstrumentos;
    private JTable tblBusqueda;
    
    private JTextField txtBusqMinimo;
    private JTextField txtBusqTipo;
    private JTextField txtBusqueda;
}
