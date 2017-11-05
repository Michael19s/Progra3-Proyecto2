package aplicacioninstrumentos.modelo;

import javax.swing.table.AbstractTableModel;

public class TablaCalibracion   extends AbstractTableModel
{
    private TablaCalibracion() 
    {
        try 
        {
            aCalibraciones = GestorCalibracion.obtenerInstancia().obtenerTabla();
        }
        catch (Exception ex) 
        { 
            System.err.printf("%s%n", ex.getMessage());
        }
    }
    
    public static TablaCalibracion obtenerInstancia() 
    {
        if (aInstacia == null) 
            aInstacia = new TablaCalibracion();
        return aInstacia;
    }
    
    @Override
    public int getRowCount() 
    {
        return aCalibraciones.length;
    }

    @Override
    public int getColumnCount()
    {
        return Calibracion.obtenerDescripcion().length;
    }
    
    @Override
    public String getColumnName(int i)
    {
        return Calibracion.obtenerDescripcion()[i];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return aCalibraciones[rowIndex][columnIndex];

    }
    
    private static TablaCalibracion aInstacia = null;
    private Object[][] aCalibraciones;
}