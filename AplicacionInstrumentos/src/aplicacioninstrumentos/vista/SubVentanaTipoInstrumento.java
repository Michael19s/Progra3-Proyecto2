package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.Control.ControlAplicacion;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;


public class SubVentanaTipoInstrumento extends JFrame implements Observer
{
    public SubVentanaTipoInstrumento(ControlAplicacion pNuevoGestor, modo pModo) 
    {
        super("Inclusion tipo de instrumento");
        aGestorPrincipal = pNuevoGestor;
        configurar(pModo);
    }
    
    private void configurar(modo pModo)
    {
        ajustarComponentes(getContentPane(), pModo);
        setResizable(false);
        setSize(300, 200);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                System.out.println("Eliminando observador..");
                aGestorPrincipal.eliminarRegistro(SubVentanaTipoInstrumento.this);
                dispose();
            }
        });
    }
    
    private void ajustarComponentes(Container c, modo pModo)
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

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(14, 10, 0, 0);
        pnlDatos.add(lblCodigo = new JLabel("Codigo:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 204;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 4, 0, 10);
        pnlDatos.add(txtCodigo = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(9, 10, 0, 0);
        pnlDatos.add(lblNombre = new JLabel("Nombre:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 204;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 4, 0, 10);
        pnlDatos.add(txtNombre = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(9, 10, 0, 0);
        pnlDatos.add(lblUnidadMedida = new JLabel("Unidad de medida:"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 156;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 4, 11, 10);
        pnlDatos.add(txtUnidadMedida = new JTextField(), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 10, 0, 10);
        c.add(pnlDatos, gridBagConstraints);

        pnlBotones.add(btnAceptar = new JButton(lvTxtBotonAceptar));

        pnlBotones.add(btnCancelar = new JButton("Cancelar"));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 10, 11, 10);
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
    
    public void aceptar(modo pModo)
    {
        switch(pModo)
        {
            case agregar:
            case clonar:
                if(aGestorPrincipal.agregarTipoInstrumeto(txtCodigo.getText(), txtNombre.getText(), txtUnidadMedida.getText()))
                {
                     aGestorPrincipal.eliminarRegistro(SubVentanaTipoInstrumento.this);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "El tipo ya fue ingresado..", "Error",JOptionPane.ERROR_MESSAGE);
                break;
            case modificar:
                if(aGestorPrincipal.modificarTipoInstrumento(txtCodigo.getText(), txtNombre.getText(), txtUnidadMedida.getText()))
                {
                     aGestorPrincipal.eliminarRegistro(SubVentanaTipoInstrumento.this);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "El tipo no se pudo modificar..", "Error",JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    
    public void cancelar()
    {
        dispose();
    }
    
    public void modificarValoresTextos(modo pModo, String pCodigo, String pNombre, String pUnidadMedicion)
    {
        switch(pModo)
        {
            case modificar:
                txtCodigo.setText(pCodigo);
                txtCodigo.setEditable(false);
                txtCodigo.setEnabled(false);
                //------------------------------
                txtNombre.setText(pNombre);
                txtUnidadMedida.setText(pUnidadMedicion);
            case clonar:
                txtNombre.setText(pNombre);
                txtUnidadMedida.setText(pUnidadMedicion);
        }
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
    
    private static ControlAplicacion aGestorPrincipal;

    public enum modo
    {
        agregar,
        modificar,
        clonar
    };
    
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblUnidadMedida;
    private JPanel pnlBotones;
    private JPanel pnlDatos;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtUnidadMedida;
 
}
