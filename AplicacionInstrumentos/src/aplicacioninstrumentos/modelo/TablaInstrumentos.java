package aplicacioninstrumentos.modelo;

import javax.swing.table.AbstractTableModel;

public class TablaInstrumentos  extends AbstractTableModel
{
    private TablaInstrumentos() 
    {
        try 
        {
            aInstrumentos = GestorInstrumentos.obtenerInstancia().obtenerTabla();
        }
        catch (Exception ex) 
        { 
            System.err.printf("%s%n", ex.getMessage());
        }
    }
    
    public static TablaInstrumentos obtenerInstancia() 
    {
        if (aInstacia == null) 
            aInstacia = new TablaInstrumentos();
        return aInstacia;
    }
    
    @Override
    public int getRowCount() 
    {
        return aInstrumentos.length;
    }

    @Override
    public int getColumnCount()
    {
        return Instrumento.obtenerDescripcionTbl().length;
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
        return aInstrumentos[rowIndex][columnIndex];

    }
    
    private static TablaInstrumentos aInstacia = null;
    private Object[][] aInstrumentos;
}