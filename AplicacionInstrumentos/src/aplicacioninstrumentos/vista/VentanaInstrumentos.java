package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.modelo.GestorInstrumentos;
import aplicacioninstrumentos.modelo.Instrumento;
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

public class VentanaInstrumentos extends JFrame
{
    public VentanaInstrumentos() 
    {
        super("Mantenimiento de Instrumentos");
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
        tblInstrumentos = new JTable();

        c.setLayout(new GridBagLayout());
        GridBagConstraints gbcCentral =  new GridBagConstraints();

        //Panel de Botones
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

        gbcCentral.anchor = java.awt.GridBagConstraints.NORTHWEST;
        c.add(pnlBotones, gbcCentral);

        //Panel busqueda
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
        String[] lvComboBox = new String[]{ "Numero de serie", "Tipo", "Descripcion", "Minimo", "Maximo", "Indice de tolerancia" };
        pnlBusqueda.add(cmbBusqueda = new JComboBox<>(lvComboBox), gbc2);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 7;
        gbc3.gridy = 0;
        gbc3.gridwidth = 6;
        gbc3.gridheight = 2;
        gbc3.ipadx = 226;
        gbc3.insets = new Insets(12, 5, 0, 0);
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
        pnlBusqueda.add(cbxTipo = new JCheckBox("Tipo"), gbc6);

        GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.gridx = 2;
        gbc7.gridy = 6;
        gbc7.gridwidth = 4;
        gbc7.gridheight = 2;
        gbc7.insets = new Insets(3, 2, 9, 0);
        pnlBusqueda.add(cbxMinimo = new JCheckBox("Minimo"), gbc7);

        GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.gridx = 10;
        gbc8.gridy = 4;
        gbc8.gridheight = 2;
        gbc8.insets = new Insets(5, 2, 0, 0);
        pnlBusqueda.add(cbxMaximo = new JCheckBox("Maximo"), gbc8);

        GridBagConstraints gbc9 = new GridBagConstraints();
        gbc9.gridx = 10;
        gbc9.gridy = 6;
        gbc9.gridwidth = 2;
        gbc9.gridheight = 2;
        gbc9.insets = new Insets(3, 2, 9, 0);
        pnlBusqueda.add(cbxTolerancia = new JCheckBox("Indice tolerancia"), gbc9);
        
        GridBagConstraints gbc10 = new GridBagConstraints();
        gbc10.gridx = 3;
        gbc10.gridy = 4;
        gbc10.gridwidth = 5;
        gbc10.ipadx = 114;
        gbc10.insets = new Insets(6, 2, 0, 0);
        pnlBusqueda.add(txtBusqTipo = new JTextField(), gbc10);
        
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.gridx = 7;
        gbc11.gridy = 6;
        gbc11.ipadx = 102;
        gbc11.insets = new Insets(4, 2, 0, 0);
        pnlBusqueda.add(txtBusqMinimo = new JTextField(), gbc11);

        GridBagConstraints gbc12 = new GridBagConstraints();
        gbc12.gridx = 11;
        gbc12.gridy = 4;
        gbc12.gridwidth = 8;
        gbc12.ipadx = 135;
        gbc12.insets = new Insets(6, 2, 0, 10);
        pnlBusqueda.add(txtBusqMaximo = new JTextField(), gbc12);
        
        GridBagConstraints gbc13 = new GridBagConstraints();
        gbc13.gridx = 12;
        gbc13.gridy = 6;
        gbc13.gridwidth = 7;
        gbc13.ipadx = 91;
        gbc13.insets = new Insets(4, 2, 0, 10);
        pnlBusqueda.add(txtBusqTolerancia = new JTextField(), gbc13);

        gbcCentral.gridx = 0;
        gbcCentral.gridy = 1;
        gbcCentral.gridwidth = 2;
        gbcCentral.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbcCentral.insets = new Insets(6, 10, 0, 10);
        c.add(pnlBusqueda, gbcCentral);

        try 
        {
            tblInstrumentos = new JTable(GestorInstrumentos.obtenerInstancia().obtenerTabla(), Instrumento.obtenerDescripcionTbl());
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.err.printf(ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        scpTabla.setViewportView(tblInstrumentos);

        gbcCentral.gridx = 0;
        gbcCentral.gridy = 3;
        gbcCentral.gridwidth = 2;
        gbcCentral.fill = GridBagConstraints.BOTH;
        gbcCentral.ipadx = 473;
        gbcCentral.ipady = 109;
        gbcCentral.anchor = GridBagConstraints.NORTHWEST;
        gbcCentral.weightx = 1.0;
        gbcCentral.weighty = 1.0;
        gbcCentral.insets = new Insets(6, 10, 11, 10);
        c.add(scpTabla, gbcCentral);
        
        txtBusqueda.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                try 
                {
                    tblBusqueda = new JTable(GestorInstrumentos.obtenerInstancia().obtenerTablaBusqueda((String)cmbBusqueda.getSelectedItem(), txtBusqueda.getText()), Instrumento.obtenerDescripcionTbl());
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
    
    private JCheckBox cbxMaximo;
    private JCheckBox cbxMinimo;
    private JCheckBox cbxTipo;
    private JCheckBox cbxTolerancia;
    
    private JComboBox<String> cmbBusqueda;
    
    private JPanel pnlBotones;
    private JPanel pnlBusqueda;
    
    private JLabel lblBuscar;
    private JLabel lblBusquedaAdv;
    
    private JScrollPane scpTabla;
    
    private JTable tblInstrumentos;
    private JTable tblBusqueda;

    
    private JTextField txtBusqMaximo;
    private JTextField txtBusqMinimo;
    private JTextField txtBusqTipo;
    private JTextField txtBusqTolerancia;
    private JTextField txtBusqueda;
}
