package aplicacioninstrumentos.vista;

import aplicacioninstrumentos.Control.ControlAplicacion;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class VentanaAplicacion extends JFrame implements Observer
{
    public VentanaAplicacion(ControlAplicacion pNuevoGestor) 
    {
        super("Manejo Instrumentos");
        System.out.println("Inicializando la vista de la aplicacion..");
        
        System.out.println("Asociando el control del sistema..");
        aGestorPrincipal = pNuevoGestor;
        
        configurar();
    }
    
    private void configurar() 
    {
        ajustarComponentes(getContentPane());
        setResizable(true);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) 
    {
        c.setLayout(new FlowLayout());
        c.add(btnTipoInstrumento = new JButton("Manejo de tipo de instrumentos"));
        c.add(btnInstrumento = new JButton("Manejo de instrumentos"));
        c.add(btnCalibraciones = new JButton("Manejo de calibraciones"));
        
        btnTipoInstrumento.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                aGestorPrincipal.mostrarVentanaTipoInstrumento(VentanaAplicacion.this);
            }
        });
        
        btnInstrumento.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                aGestorPrincipal.mostrarVentanaInstrumento(VentanaAplicacion.this);
            }
        });
        btnCalibraciones.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                aGestorPrincipal.mostrarVentanaCalibraciones(VentanaAplicacion.this);
            }
        });
    }

    public void init() 
    {
        setVisible(true);
    }
    
    @Override
    public void update(Observable pReferencia, Object e)
    {
        
    }
    
    private static ControlAplicacion aGestorPrincipal;
    
    private JButton btnTipoInstrumento;
    private JButton btnInstrumento;
    private JButton btnCalibraciones;
}
