package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.Control.ControlAplicacion;
import aplicacioninstrumentos.modelo.GestorMedidas;
import aplicacioninstrumentos.modelo.GestorTipoInstrumentos;
import aplicacioninstrumentos.modelo.Medida;
import aplicacioninstrumentos.modelo.TipoInstrumento;
import aplicacioninstrumentos.vista.VentanaInclusionMedidas.modoMedidas;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
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
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class VentanaMedidas  extends JFrame implements Observer
{

    public VentanaMedidas(ControlAplicacion pNuevoGestor) 
    {
        super("Mantenimiento de medidas");
        aGestorPrincipal = pNuevoGestor;
        configurar();
    }
    
    private void configurar()
    {
        ajustarComponentes(getContentPane());
        setResizable(false);
        setSize(920, 400);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                System.out.println("Eliminando observador..");
                aGestorPrincipal.eliminarRegistro(VentanaMedidas.this);
                dispose();
            }
        });
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
        
        tblMedidas = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        c.setLayout(new GridBagLayout());

        pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.LINE_AXIS));

        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/images/BotonAgregar.png"))); // NOI18N
        pnlBotones.add(btnAgregar);

        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/images/BotonEliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar registro");
        pnlBotones.add(btnEliminar);

        btnModificar.setIcon(new ImageIcon(getClass().getResource("/images/BotonEditar.png"))); // NOI18N
        btnModificar.setToolTipText("Editar registro");
        pnlBotones.add(btnModificar);

        btnClonar.setIcon(new ImageIcon(getClass().getResource("/images/BotonCopiar.png"))); // NOI18N
        btnClonar.setToolTipText("Clonar registro");
        pnlBotones.add(btnClonar);

        GridBagConstraints gbcBotones= new GridBagConstraints();
        gbcBotones.gridx = 1;
        gbcBotones.gridy = 1;
        gbcBotones.ipadx = 268;
        gbcBotones.anchor = GridBagConstraints.NORTHWEST;
        gbcBotones.insets = new Insets(11, 10, 0, 0);
        c.add(pnlBotones, gbcBotones);

        pnlBusqueda.setLayout(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.anchor = GridBagConstraints.NORTHWEST;
        gbc1.insets = new Insets(20, 10, 0, 0);
        pnlBusqueda.add(lblBuscar = new JLabel("Buscar:"), gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 3;
        gbc2.gridheight = 2;
        gbc2.anchor = GridBagConstraints.NORTHWEST;
        gbc2.insets = new Insets(17, 4, 0, 0);
        String[] lvString = new String[] { "Numero de medida", "Numero de referencia", "Numero de lectura"};
        pnlBusqueda.add(cmbBusqueda = new JComboBox<>(lvString), gbc2);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 6;
        gbc3.gridy = 0;
        gbc3.gridwidth = 7;
        gbc3.gridheight = 2;
        gbc3.ipadx = 226;
        gbc3.anchor = GridBagConstraints.NORTHWEST;
        gbc3.insets = new Insets(17, 6, 0, 0);
        pnlBusqueda.add(txtBusqueda = new JTextField(), gbc3);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 13;
        gbc4.gridy = 0;
        gbc4.gridheight = 3;
        gbc4.anchor = GridBagConstraints.NORTHWEST;
        gbc4.insets = new Insets(16, 6, 0, 10);
        pnlBusqueda.add(btnBuscar = new JButton("Buscar"), gbc4);

        GridBagConstraints gbc5= new GridBagConstraints();
        gbc5.gridx = 0;
        gbc5.gridy = 3;
        gbc5.gridwidth = 2;
        gbc5.anchor = GridBagConstraints.NORTHWEST;
        gbc5.insets = new Insets(6, 10, 0, 0);
        pnlBusqueda.add(lblBusquedaAdv = new JLabel("Busqueda avanzada:"), gbc5);

        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.gridx = 3;
        gbc6.gridy = 4;
        gbc6.gridwidth = 6;
        gbc6.gridheight = 2;
        gbc6.anchor = GridBagConstraints.NORTHWEST;
        gbc6.insets = new Insets(5, 2, 0, 0);
        pnlBusqueda.add(cbxReferencia = new JCheckBox("Numero de referencia"), gbc6);

        GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.gridx = 3;
        gbc7.gridy = 6;
        gbc7.gridwidth = 4;
        gbc7.gridheight = 2;
        gbc7.anchor = GridBagConstraints.NORTHWEST;
        gbc7.insets = new Insets(3, 2, 11, 0);
        pnlBusqueda.add(cbxLectura = new JCheckBox("Numero de lectura"), gbc7);
        
        GridBagConstraints gbc8= new GridBagConstraints();
        gbc8.gridx = 11;
        gbc8.gridy = 4;
        gbc8.ipadx = 114;
        gbc8.anchor = GridBagConstraints.NORTHWEST;
        gbc8.insets = new Insets(6, 2, 0, 0);
        pnlBusqueda.add(txtBusqReferencia = new JTextField(), gbc8);
        
        GridBagConstraints gbc9 = new GridBagConstraints();
        gbc9.gridx = 11;
        gbc9.gridy = 6;
        gbc9.ipadx = 114;
        gbc9.anchor = GridBagConstraints.NORTHWEST;
        gbc9.insets = new Insets(4, 2, 0, 0);
        pnlBusqueda.add(txtBusqLectura = new JTextField(), gbc9);

        GridBagConstraints gbcBusqueda = new GridBagConstraints();
        gbcBusqueda.gridx = 1;
        gbcBusqueda.gridy = 2;
        gbcBusqueda.gridwidth = 2;
        gbcBusqueda.ipady = 11;
        gbcBusqueda.anchor = GridBagConstraints.NORTHWEST;
        gbcBusqueda.insets = new Insets(6, 10, 0, 0);
        c.add(pnlBusqueda, gbcBusqueda);

        mostrarTabla(c);
        
        txtBusqueda.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                try 
                {
                    tblBusqueda = new JTable(GestorMedidas.obtenerInstancia().obtenerTablaBusqueda((String)cmbBusqueda.getSelectedItem(), txtBusqueda.getText()), Medida.obtenerDescripcion());
                }
                catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
                {
                    System.err.printf(ex.getMessage());
                    JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                }
                scpTabla.setViewportView(tblBusqueda);
            }
        });
        
        cbxReferencia.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                if(cbxReferencia.isSelected())
                    busquedaAvanzada();
                else
                    mostrarTabla(c);
            }
        });
        
        cbxLectura.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                if(cbxLectura.isSelected())
                    busquedaAvanzada();
                else
                    mostrarTabla(c);
            }
        });
        
        txtBusqReferencia.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e) 
            {
                if(cbxReferencia.isSelected())
                    busquedaAvanzada();
            }
        });
        
        txtBusqLectura.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent e) 
            {
                if(cbxLectura.isSelected())
                    busquedaAvanzada();
            }
        });
        
        btnAgregar.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                agregar();
            }
        });
        
        btnEliminar.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                eliminar();
            }
        });
        
        btnModificar.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                modificar();
            }
        });
        
        btnClonar.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                clonar();
            }
        });
    }
    
        
    private void mostrarTabla(Container c)
    {
        if(aCalibracionXMedida != 0)
        {
            try 
            {
                tblMedidas = new JTable(GestorMedidas.obtenerInstancia().obtenerTablaBusqueda("NumeroCalibracion", String.valueOf(aCalibracionXMedida)), Medida.obtenerDescripcion());
            }
            catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
            {
                System.err.printf(ex.getMessage());
                JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            scpTabla.setViewportView(tblMedidas);
        }
        else
        {
            try 
            {
                tblMedidas = new JTable(GestorMedidas.obtenerInstancia().obtenerTabla(), Medida.obtenerDescripcion());
            }
            catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
            {
                System.err.printf(ex.getMessage());
                JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            scpTabla.setViewportView(tblMedidas);
        }
            
        GridBagConstraints gbcTabla= new GridBagConstraints();
        gbcTabla.gridx = 1;
        gbcTabla.gridy = 3;
        gbcTabla.gridwidth = 3;
        gbcTabla.fill = java.awt.GridBagConstraints.BOTH;
        gbcTabla.ipadx = 754;
        gbcTabla.ipady = 133;
        gbcTabla.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbcTabla.weightx = 1.0;
        gbcTabla.weighty = 1.0;
        gbcTabla.insets = new java.awt.Insets(6, 10, 11, 10);
        c.add(scpTabla, gbcTabla);
    }
    
    private void busquedaAvanzada()
    {
            try 
            {
                tblBusqueda = new JTable(GestorMedidas.obtenerInstancia().obtenerTablaBusquedaAvanzada(txtBusqReferencia.getText(), txtBusqLectura.getText()), Medida.obtenerDescripcion());
            }
            catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
            {
                System.err.printf(ex.getMessage());
                JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            scpTabla.setViewportView(tblBusqueda); 
    }
    
      private void agregar()
    {
        aGestorPrincipal.mostrarVentanaInclusionMedidas(VentanaInclusionMedidas.modoMedidas.agregar, VentanaMedidas.this, aCalibracionXMedida);
    }
    
    private void eliminar()
    {
        int lvFilaSeleccionada = tblMedidas.getSelectedRow();
        if(lvFilaSeleccionada == -1)
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro..", "Advertencia",JOptionPane.WARNING_MESSAGE);
        else
        {
            Object lvNumero = tblMedidas.getValueAt(lvFilaSeleccionada, 0);
            aGestorPrincipal.eliminarTipoInstrumeto((String)lvNumero);
        }
    }
    
    private void modificar()
    {
        int lvFilaSeleccionada = tblMedidas.getSelectedRow();
        if(lvFilaSeleccionada == -1)
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro..", "Advertencia",JOptionPane.WARNING_MESSAGE);
        else
        {
            Object lvNumero = tblMedidas.getValueAt(lvFilaSeleccionada, 0);
            Medida lvMedida = aGestorPrincipal.recuperarMedida((int)lvNumero);
            aGestorPrincipal.mostrarVentanaInclusionMedidas(modoMedidas.modificar, VentanaMedidas.this,(int)lvNumero ,lvMedida.obtenerReferencia(), lvMedida.obtenerLectura(), aCalibracionXMedida);
        }
    }
    
    private void clonar()
    {
        int lvFilaSeleccionada = tblMedidas.getSelectedRow();
        if(lvFilaSeleccionada == -1)
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro..", "Advertencia",JOptionPane.WARNING_MESSAGE);
        else
        {
            Object lvNumero = tblMedidas.getValueAt(lvFilaSeleccionada, 0);
            Medida lvMedida = aGestorPrincipal.recuperarMedida((int)lvNumero);
            aGestorPrincipal.mostrarVentanaInclusionMedidas(modoMedidas.clonar, VentanaMedidas.this, (int)lvNumero, lvMedida.obtenerReferencia(), lvMedida.obtenerLectura(), aCalibracionXMedida);
        }
    }

    public void establecerNumeroCalibracion(int pNumero)
    {
        aCalibracionXMedida = pNumero;
    }
    
    public void init(JFrame pBase)
    {
        setLocationRelativeTo(pBase);
        setVisible(true);
    }
    
    @Override
    public void update(Observable pReferencia, Object e)
    {
        if(aCalibracionXMedida != 0)
        {
            try 
            {
                tblMedidas = new JTable(GestorMedidas.obtenerInstancia().obtenerTablaBusqueda("NumeroCalibracion", String.valueOf(aCalibracionXMedida)), Medida.obtenerDescripcion());
            }
            catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
            {
                System.err.printf(ex.getMessage());
                JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            scpTabla.setViewportView(tblMedidas);
        }
        else
        {
            try 
            {
                tblMedidas = new JTable(GestorMedidas.obtenerInstancia().obtenerTabla(), Medida.obtenerDescripcion());
            }
            catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
            {
                System.err.printf(ex.getMessage());
                JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            scpTabla.setViewportView(tblMedidas);
        }
    }
    
    private static ControlAplicacion aGestorPrincipal;
    
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnClonar;
    private JButton btnEliminar;
    private JButton btnModificar;
    
    private JCheckBox cbxLectura;
    private JCheckBox cbxReferencia;
    private JComboBox<String> cmbBusqueda;
    
    private JPanel pnlBotones;
    private JPanel pnlBusqueda;
    
    private JLabel lblBuscar;
    private JLabel lblBusquedaAdv;
    
    private JScrollPane scpTabla;
    
    private JTable tblMedidas;
    private JTable tblBusqueda;
    
    private JTextField txtBusqLectura;
    private JTextField txtBusqReferencia;
    private JTextField txtBusqueda;
    
    private int aCalibracionXMedida = 0;
}
