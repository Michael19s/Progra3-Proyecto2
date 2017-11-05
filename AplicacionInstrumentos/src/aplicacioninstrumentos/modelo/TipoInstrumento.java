package aplicacioninstrumentos.modelo;


public class TipoInstrumento
{

    public TipoInstrumento(String pCodigo, String pNombre, String pUnidadMedicion)
    {
        this.aCodigo = pCodigo;
        this.aNombre = pNombre;
        this.aUnidadMedicion = pUnidadMedicion;
    }
        
    @Override
    public String toString() 
    {
        return String.format("{%s, %s %s}", aCodigo, obtenerNombre(), obtenerUnidadMedicion());
    }

    public String obtenerCodigo() 
    {
        return aCodigo;
    }

    public void establecerCodigo(String pCodigo)
    {
        this.aCodigo = pCodigo;
    }

    public String obtenerNombre() 
    {
        return aNombre;
    }

    public void establecerNombre(String pNombre)
    {
        this.aNombre = pNombre;
    }

    public String obtenerUnidadMedicion()
    {
        return aUnidadMedicion;
    }

    public static String[] obtenerDescripcion()
    {
        return aDescripcion;
    }
    
    
    
    public void establecerUnidadMedicion(String pUnidadMedicion)
    {
        this.aUnidadMedicion = pUnidadMedicion;
    }
    
    private static final String[] aDescripcion = {"Codigo", "Nombre", "Unidad de medicion"};
    
    String aCodigo;
    String aNombre;
    String aUnidadMedicion;
}
