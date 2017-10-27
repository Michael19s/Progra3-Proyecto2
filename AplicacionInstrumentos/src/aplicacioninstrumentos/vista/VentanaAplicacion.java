package aplicacioninstrumentos.vista;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaAplicacion extends JFrame
{

    public VentanaAplicacion() 
    {
        super("Manejo Instrumentos");
        configurar();
    }
    
    private void configurar() 
    {
        ajustarComponentes(getContentPane());
        setResizable(true);
        setSize(800, 600);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) 
    {
        c.setLayout(new BorderLayout());
    }

    public void init() 
    {
        setVisible(true);
    }
}
