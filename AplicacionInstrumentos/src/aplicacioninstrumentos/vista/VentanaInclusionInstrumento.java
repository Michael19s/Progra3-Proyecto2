package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.Control.ControlAplicacion;
import aplicacioninstrumentos.modelo.GestorTipoInstrumentos;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class VentanaInclusionInstrumento extends JFrame implements Observer
{
    public VentanaInclusionInstrumento(ControlAplicacion pNuevoGestor, modoInstrumento pModo) 
    {
        super("Agregar instrumento");
        aGestorPrincipal = pNuevoGestor;
        configurar(pModo);
    }
    
    private void configurar(modoInstrumento pModo)
    {
        ajustarComponentes(getContentPane(), pModo);
        setResizable(false);
        setSize(520, 250);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                System.out.println("Eliminando observador..");
                aGestorPrincipal.eliminarRegistro(VentanaInclusionInstrumento.this);
                dispose();
            }
        });
    }
    
    private void ajustarComponentes(Container c, modoInstrumento pModo)
    {
        GridBagConstraints gridBagConstraints;
        
        String lvTxtBotonAceptar = "";
        
        switch(pModo)
        {
            case agregar:
                lvTxtBotonAceptar = "Agregar";
                break;
            case modificar:
                lvTxtBotonAceptar = "Aceptar";
                break;
            case clonar:
                lvTxtBotonAceptar = "Agregar";
                break;
            default:
                break;
        }

        pnlDatos = new JPanel();
        pnlBotones = new JPanel();
        
        jScrollPane1 = new JScrollPane();

        c.setLayout(new GridBagLayout());
        
        pnlDatos.setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(14, 10, 0, 0);
        pnlDatos.add(lblNumeroSerie = new JLabel("Numero de serie:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 238;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 4, 0, 0);
        pnlDatos.add(txtNumeroSerie = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(14, 18, 0, 0);
        pnlDatos.add(lblTipo = new JLabel("Tipo:"), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 4, 0, 10);
        pnlDatos.add(cmbTipo = new JComboBox<>(), gridBagConstraints);
        llenarComboTipo();

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(9, 10, 0, 0);
        pnlDatos.add(lblMinimo = new JLabel("Minimo:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 89;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 4, 11, 0);
        pnlDatos.add(txtMinimo = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(9, 18, 0, 0);
        pnlDatos.add(lblMaximo = new JLabel("Maximo:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 18, 11, 0);
        pnlDatos.add(txtMaximo = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(9, 18, 0, 0);
        pnlDatos.add(lblTolerancia = new JLabel("Tolerancia"), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 4, 11, 10);
        pnlDatos.add(txtTolerancia = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 10, 0, 10);
        c.add(pnlDatos, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 20, 0, 0);
        c.add(lblDescripcion = new JLabel("Descripcion:"), gridBagConstraints);

        txaDescripcion = new JTextArea();
        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txaDescripcion);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 449;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(6, 20, 0, 10);
        c.add(jScrollPane1, gridBagConstraints);

        pnlBotones.add(btnAceptar = new JButton(lvTxtBotonAceptar));

        pnlBotones.add(btnCancelar = new JButton("Cancelar"));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 347;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 10, 15, 10);
        c.add(pnlBotones, gridBagConstraints);
        
        //Eventos
        btnAceptar.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                aceptar(pModo);
            }
        });
        
        btnCancelar.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
               cancelar();
            }
        });
        
        txaDescripcion.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyTyped(KeyEvent e) 
            {
                txaDescripcion.setLineWrap(true);
                if(txaDescripcion.getText().length() == 200)
                    e.consume();
            }
            
        });
    }
    
    public void aceptar(modoInstrumento pModo)
    {
        switch(pModo)
        {
            case agregar:
            case clonar:
                if(aGestorPrincipal.agregarInstrumento(txtNumeroSerie.getText(), (String)cmbTipo.getSelectedItem(), txaDescripcion.getText(), Integer.parseInt(txtMinimo.getText()), Integer.parseInt(txtMaximo.getText()), Integer.parseInt(txtTolerancia.getText())))
                {
                     aGestorPrincipal.eliminarRegistro(VentanaInclusionInstrumento.this);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "El instrumento ya fue ingresado..", "Error",JOptionPane.ERROR_MESSAGE);
                break;
            case modificar:
                if(aGestorPrincipal.modificarInstrumento(txtNumeroSerie.getText(), (String)cmbTipo.getSelectedItem(), txaDescripcion.getText(), Integer.parseInt(txtMinimo.getText()), Integer.parseInt(txtMaximo.getText()), Integer.parseInt(txtTolerancia.getText())))
                {
                     aGestorPrincipal.eliminarRegistro(VentanaInclusionInstrumento.this);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "El instrumento no se pudo modificar..", "Error",JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    
    public void cancelar()
    {
        dispose();
    }
    
    public void modificarValoresTextos(modoInstrumento pModo, String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
    {
        switch(pModo)
        {
            case modificar:
                txtNumeroSerie.setText(pNumeroSerie);
                txtNumeroSerie.setEditable(false);
                txtNumeroSerie.setEnabled(false);
                //------------------------------
                cmbTipo.setSelectedItem(pTipo);
                txaDescripcion.setText(pDescripcion);
                txtMinimo.setText(String.valueOf(pMinimo));
                txtMaximo.setText(String.valueOf(pMaximo));
                txtTolerancia.setText(String.valueOf(pTolerancia));
            case clonar:
                cmbTipo.setSelectedItem(pTipo);
                txaDescripcion.setText(pDescripcion);
                txtMinimo.setText(String.valueOf(pMinimo));
                txtMaximo.setText(String.valueOf(pMaximo));
                txtTolerancia.setText(String.valueOf(pTolerancia));
        }
    }
    
    private void llenarComboTipo()
    {
        Object[][] lvTablaTipo = null;
        try
        {
            lvTablaTipo = GestorTipoInstrumentos.obtenerInstancia().obtenerTabla();
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex)
        {
            
        }
        
        if(lvTablaTipo.length != 0)
            for(int i = 0; i < lvTablaTipo.length; i++)
                cmbTipo.addItem((String)lvTablaTipo[i][1]);
    }
    
    public void init(JFrame pBase)
    {
        setLocationRelativeTo(pBase);
        setVisible(true);
    }
    
    @Override
    public void update(Observable pReferencia, Object e)
    {
        
    }
    
     public enum modoInstrumento
    {
        agregar,
        modificar,
        clonar
    };
    
    private static ControlAplicacion aGestorPrincipal;
    
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private JComboBox<String> cmbTipo;
    
    private JScrollPane jScrollPane1;
    
    private JTextField txtMaximo;
    
    private JLabel lblDescripcion;
    private JLabel lblMaximo;
    private JLabel lblMinimo;
    private JLabel lblNumeroSerie;
    private JLabel lblTipo;
    private JLabel lblTolerancia;
    
    private JPanel pnlBotones;
    private JPanel pnlDatos;
    
    private JTextArea txaDescripcion;
    
    private JTextField txtMinimo;
    private JTextField txtNumeroSerie;
    private JTextField txtTolerancia;
}
