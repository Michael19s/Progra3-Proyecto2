package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.Control.ControlAplicacion;
import aplicacioninstrumentos.modelo.GestorInstrumentos;
import aplicacioninstrumentos.modelo.GestorMedidas;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class VentanaInclusionCalibracion extends JFrame implements Observer
{
    public VentanaInclusionCalibracion(ControlAplicacion pNuevoGestor, modoCalibracion pModo) 
    {
        super("Agregar calibracion");
        aGestorPrincipal = pNuevoGestor;
        configurar(pModo);
    }
    
    private void configurar(modoCalibracion pModo)
    {
        ajustarComponentes(getContentPane(), pModo);
        setResizable(false);
        setSize(300, 180);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                System.out.println("Eliminando observador..");
                aGestorPrincipal.eliminarRegistro(VentanaInclusionCalibracion.this);
                dispose();
            }
        });
    }
    
    private void ajustarComponentes(Container c, modoCalibracion pModo)
    {
        GridBagConstraints gridBagConstraints;

                String lvTxtBotonAceptar = "";
        
        
        
        pnlDatos = new JPanel();
        
        cmbFecha = new datechooser.beans.DateChooserCombo();
        pnlBotones = new JPanel();
        
        btnAceptar = new JButton();
        btnCancelar = new JButton();

        c.setLayout(new GridBagLayout());

        pnlDatos.setLayout(new GridBagLayout());

        switch(pModo)
        {
            case agregar:
                lvTxtBotonAceptar = "Agregar";
                break;
            case modificar:
                lvTxtBotonAceptar = "Aceptar";
                gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = 2;
                gridBagConstraints.gridy = 4;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.insets = new java.awt.Insets(9, 24, 0, 0);
                pnlDatos.add(btnMedidas = new JButton("Medidas"), gridBagConstraints);
                break;
            case clonar:
                lvTxtBotonAceptar = "Agregar";
                break;
            default:
                break;
        }
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(14, 10, 0, 0);
        pnlDatos.add(lblInstrumento = new JLabel("Instrumento:"), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 110;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 4, 0, 10);
        pnlDatos.add(cmbInstrumento = new JComboBox<>(), gridBagConstraints);
        llenarComboInstrumento();

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 10, 0, 0);
        pnlDatos.add(lblFecha = new JLabel("Fecha:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 67;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 4, 0, 0);
        pnlDatos.add(cmbFecha, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 10, 0, 10);
        c.add(pnlDatos, gridBagConstraints);

        pnlBotones.add(btnAceptar = new JButton(lvTxtBotonAceptar));

        pnlBotones.add(btnCancelar = new JButton("Cancelar"));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 108;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 10, 23, 10);
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
        
        if(pModo == pModo.modificar)
        {
            btnMedidas.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                   aGestorPrincipal.mostrarVentanaMedidas(VentanaInclusionCalibracion.this, aNumeroAModificar);
                }
            });
        }
    }
    
    public void aceptar(modoCalibracion pModo)
    {
        Date lvFechaReal = new Date(cmbFecha.getSelectedDate().getTimeInMillis());
        switch(pModo)
        {
            case agregar:
            case clonar:
                if(aGestorPrincipal.agregarCalibracion(0, (String)cmbInstrumento.getSelectedItem(), lvFechaReal, 0))
                {
                     aGestorPrincipal.eliminarRegistro(VentanaInclusionCalibracion.this);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "La calibracion ya fue ingresada..", "Error",JOptionPane.ERROR_MESSAGE);
                break;
            case modificar:
                int lvTamanno = 0;
                try 
                {
                    lvTamanno = GestorMedidas.obtenerInstancia().obtenerTablaBusqueda("NumeroCalibracion", String.valueOf(aNumeroAModificar)).length;
                } 
                catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex)
                {

                }
                if(aGestorPrincipal.modificarCalibracion(aNumeroAModificar, (String)cmbInstrumento.getSelectedItem(),lvFechaReal, lvTamanno))
                {
                     aGestorPrincipal.eliminarRegistro(VentanaInclusionCalibracion.this);
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
    
    public void modificarValoresTextos(modoCalibracion pModo,int pNumero, String pInstrumento, Date pFecha, int pMediciones)
    {
        switch(pModo)
        {
            case modificar:
            case clonar:
                cmbInstrumento.setSelectedItem(pInstrumento);
//                cmbFecha.setDate(String.valueOf(pLectura));
                aNumeroAModificar = pNumero;
        }
    }
    
    private void llenarComboInstrumento()
    {
        Object[][] lvTablaInstrumento = null;
        try
        {
            lvTablaInstrumento = GestorInstrumentos.obtenerInstancia().obtenerTabla();
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex)
        {
            
        }
        
        if(lvTablaInstrumento.length != 0)
            for(int i = 0; i < lvTablaInstrumento.length; i++)
                cmbInstrumento.addItem((String)lvTablaInstrumento[i][0]);
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
    
     public enum modoCalibracion
    {
        agregar,
        modificar,
        clonar
    };
    
    private static ControlAplicacion aGestorPrincipal;
    
    
    private datechooser.beans.DateChooserCombo cmbFecha;
    
    private int aNumeroAModificar;
    
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JButton btnMedidas;
    
    private JComboBox<String> cmbInstrumento;
    
    private JLabel lblInstrumento;
    private JLabel lblFecha;
    
    private JPanel pnlDatos;
    private JPanel pnlBotones;
}
