package aplicacioninstrumentos.modelo;

import javax.swing.table.AbstractTableModel;

public class TablaMedidas   extends AbstractTableModel
{
    private TablaMedidas() 
    {
        try 
        {
            aMedidas = GestorMedidas.obtenerInstancia().obtenerTabla();
        }
        catch (Exception ex) 
        { 
            System.err.printf("%s%n", ex.getMessage());
        }
    }
    
    public static TablaMedidas obtenerInstancia() 
    {
        if (aInstacia == null) 
            aInstacia = new TablaMedidas();
        return aInstacia;
    }
    
    @Override
    public int getRowCount() 
    {
        return aMedidas.length;
    }

    @Override
    public int getColumnCount()
    {
        return Medida.obtenerDescripcion().length;
    }
    
    @Override
    public String getColumnName(int i)
    {
        return Instrumento.obtenerDescripcionTbl()[i];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return aMedidas[rowIndex][columnIndex];

    }
    
    private static TablaMedidas aInstacia = null;
    private Object[][] aMedidas;
}
