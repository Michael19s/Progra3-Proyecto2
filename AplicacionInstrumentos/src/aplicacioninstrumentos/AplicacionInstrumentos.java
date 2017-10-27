package aplicacioninstrumentos;

import aplicacioninstrumentos.vista.VentanaAplicacion;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AplicacionInstrumentos
{
    public static void main(String[] args)
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
        {
            System.err.printf("Excepción: '%s'%n ", ex.getMessage());
        }

        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                mostrarInterfaz();
            }
        });
    }
    
    public static void mostrarInterfaz()
    {
        VentanaAplicacion lvVentanaPrincipal = new VentanaAplicacion();
        lvVentanaPrincipal.init();
    }
    
}
