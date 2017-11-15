package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.Control.ControlAplicacion;
import aplicacioninstrumentos.modelo.GestorCalibracion;
import aplicacioninstrumentos.modelo.GestorInstrumentos;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class VentanaInclusionMedidas extends JFrame implements Observer
{
        public VentanaInclusionMedidas(ControlAplicacion pNuevoGestor, modoMedidas pModo) 
    {
        super("Agregar medida");
        aGestorPrincipal = pNuevoGestor;
        configurar(pModo);
    }
    
    private void configurar(modoMedidas pModo)
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
                aGestorPrincipal.eliminarRegistro(VentanaInclusionMedidas.this);
                dispose();
            }
        });
    }
    
    private void ajustarComponentes(Container c, modoMedidas pModo)
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

        c.setLayout(new GridBagLayout());

        pnlDatos.setLayout(new GridBagLayout());

        pnlDatos.setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(9, 10, 0, 0);
        pnlDatos.add(lblReferencia = new JLabel("Numero de referencia:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 204;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 3, 0, 0);
        pnlDatos.add(txtReferencia = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 10, 11, 0);
        pnlDatos.add(lblLectura = new JLabel("Numero de lectura:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 220;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 4, 11, 0);
        pnlDatos.add(txtLectura = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(20, 10, 0, 0);
        pnlDatos.add(lblCalibracion = new JLabel("Numero de calibracion:"), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(17, 4, 0, 0);
        pnlDatos.add(cmbCalibracion = new JComboBox<>(), gridBagConstraints);
        llenarComboCalibracion();

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(99, 29, 0, 29);
        c.add(pnlDatos, gridBagConstraints);

        pnlBotones.add(btnAceptar = new JButton(lvTxtBotonAceptar));

        pnlBotones.add(btnCancelar = new JButton("Cancelar"));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 181;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 29, 99, 29);
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
    }
    
    private void llenarComboCalibracion()
    {
        Object[][] lvTablaCalibracion = null;
        try
        {
            lvTablaCalibracion = GestorCalibracion.obtenerInstancia().obtenerTabla();
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex)
        {
            
        }
        
        if(lvTablaCalibracion.length != 0)
            for(int i = 0; i < lvTablaCalibracion.length; i++)
            {
                int lvItem = (int)lvTablaCalibracion[i][0];
                cmbCalibracion.addItem(String.valueOf(lvItem));

            }
    }
    
    public void aceptar(modoMedidas pModo)
    {
        switch(pModo)
        {
            case agregar:
            case clonar:
                if(aGestorPrincipal.agregarMedida(0, Integer.parseInt(txtReferencia.getText()), Integer.parseInt((txtLectura.getText())), aNumeroCalibracion))
                {
                     aGestorPrincipal.eliminarRegistro(VentanaInclusionMedidas.this);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "La medida ya fue ingresada..", "Error",JOptionPane.ERROR_MESSAGE);
                break;
            case modificar:
                if(aGestorPrincipal.modificarMedida(aNumeroAModificar, Integer.parseInt(txtReferencia.getText()), Integer.parseInt(txtLectura.getText()), aNumeroCalibracion))
                {
                     aGestorPrincipal.eliminarRegistro(VentanaInclusionMedidas.this);
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
    
    public void modificarValoresTextos(modoMedidas pModo,int pNumero, int pReferencia, int pLectura)
    {
        switch(pModo)
        {
            case modificar:
            case clonar:
                cmbCalibracion.setSelectedItem(aNumeroCalibracion);
                txtReferencia.setText(String.valueOf(pReferencia));
                txtLectura.setText(String.valueOf(pLectura));
                aNumeroAModificar = pNumero;
        }
    }
    
    public void esablecerNumeroCalibracion(int pNumero)
    {
        aNumeroCalibracion = pNumero;
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
    
     public enum modoMedidas
    {
        agregar,
        modificar,
        clonar
    };
    
    private static ControlAplicacion aGestorPrincipal;
    
    
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private JLabel lblReferencia;
    private JLabel lblLectura;
    
    private JPanel pnlBotones;
    private JPanel pnlDatos;
    
    private JTextField txtReferencia;
    private JTextField txtLectura;
    
    private int aNumeroCalibracion;
    private int aNumeroAModificar;
    
    private JComboBox<String> cmbCalibracion;
    private JLabel lblCalibracion;
}
