package aplicacioninstrumentos.vista;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SubVentanaTipoInstrumento extends JFrame implements Observer 
{
    public SubVentanaTipoInstrumento() 
    {
        super("Tipo Instrumental");

        configurar();
    }
    
    private void configurar()
    {
        ajustarComponentes(getContentPane());
        setResizable(false);
        setSize(400, 300);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void ajustarComponentes(Container c)
    {
        pnlBotones = new JPanel();
        pnlBusqueda = new JPanel();
        btnAgregar = new JButton();
        btnCancelar = new JButton();

        c.setLayout(new GridBagLayout());
        GridBagConstraints gbcCentral = new GridBagConstraints();

        //Panel de Botones
        pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.LINE_AXIS));

        

        gbcCentral.anchor = java.awt.GridBagConstraints.NORTHWEST;
        c.add(pnlBotones, gbcCentral);

        //Panel busqueda
       pnlBusqueda.setLayout(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(15, 0, 0, 0);
        pnlBusqueda.add(lblCodigo = new JLabel("Codigo:"), gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 1;
        gbc2.insets = new Insets(4, 3, 0, 0);
        pnlBusqueda.add(lblNombre = new JLabel("Nombre:"), gbc2);
        
        GridBagConstraints gbc7 = new GridBagConstraints();
        gbc7.gridx = 0;
        gbc7.gridy = 5;
        gbc7.gridwidth = 6;
        gbc7.insets = new Insets(10, 4, 0, 0);
        pnlBusqueda.add(lblUnidadM = new JLabel("Unidad de Medida:"), gbc7);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 2;
        gbc3.gridy = 0;
        gbc3.gridwidth = 6;
        gbc3.gridheight = 2;
        gbc3.ipadx = 215;
        gbc3.insets = new Insets(10, 0, 0, 0);
        pnlBusqueda.add(txtCodigo = new JTextField(), gbc3);
        
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 3;
        gbc4.gridy = 1;
        gbc4.gridwidth = 8;
        gbc4.gridheight = 2;
        gbc4.ipadx = 215;
        gbc4.insets = new Insets(12, 0, 0, 0);
        pnlBusqueda.add(txtNombre = new JTextField(), gbc4);
        
         GridBagConstraints gbc8 = new GridBagConstraints();
        gbc8.gridx = 3;
        gbc8.gridy = 4;
        gbc8.gridwidth = 20;
        gbc8.gridheight = 2;
        gbc8.ipadx = 175;
        gbc8.insets = new Insets(2,45, 0, 0);
        pnlBusqueda.add(txtUnidadM= new JTextField(), gbc8);
        
          GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5 .gridx = 6;
        gbc5 .gridy = 6;
        gbc5 .gridheight = 4;
        gbc5 .insets = new Insets(11, 14, 0, 10);
        pnlBusqueda.add(btnAgregar = new JButton("Agregar"), gbc5 );
        
        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6 .gridx = 7;
        gbc6 .gridy = 6;
        gbc6 .gridheight = 2;
        gbc6.insets = new Insets(11, 14, 0, 10);
        pnlBusqueda.add(btnCancelar = new JButton("Cancelar"), gbc6 );
        
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 0;
        gbcCentral.gridwidth = 2;
        gbcCentral.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbcCentral.insets = new Insets(6, 10, 0, 10);
        c.add(pnlBusqueda, gbcCentral);


    }
    public void cancelar()
    {
        dispose();
    }
      public void init()
    {
        
        setVisible(true);
    }
    
    @Override
    public void update(Observable pReferencia, Object e)
    {
        
    }

    private JButton btnAgregar;
    private JButton btnCancelar;

    
 
    
    private JPanel pnlBotones;
    private JPanel pnlBusqueda;
    
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblUnidadM;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtUnidadM;
 
}
