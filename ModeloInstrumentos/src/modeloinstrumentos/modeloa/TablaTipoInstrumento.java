package modeloinstrumentos.modeloa;

import javax.swing.table.AbstractTableModel;

public class TablaTipoInstrumento extends AbstractTableModel
{
    private TablaTipoInstrumento() 
    {
        try 
        {
            aTipoInstrumentos = GestorTipoInstrumentos.obtenerInstancia().obtenerTabla();
        }
        catch (Exception ex) 
        { 
            System.err.printf("%s%n", ex.getMessage());
        }
    }
    
    public static TablaTipoInstrumento obtenerInstancia() 
    {
        if (aInstacia == null) 
            aInstacia = new TablaTipoInstrumento();
        return aInstacia;
    }
    
    @Override
    public int getRowCount() 
    {
        return aTipoInstrumentos.length;
    }

    @Override
    public int getColumnCount()
    {
        return TipoInstrumento.obtenerDescripcion().length;

    }
    
    @Override
    public String getColumnName(int i)
    {
        return TipoInstrumento.obtenerDescripcion()[i];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        boolean[] canEdit = new boolean [] {false, false, false};
        return canEdit[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return aTipoInstrumentos[rowIndex][columnIndex];

    }
    
    private static TablaTipoInstrumento aInstacia = null;
    private Object[][] aTipoInstrumentos;
}
