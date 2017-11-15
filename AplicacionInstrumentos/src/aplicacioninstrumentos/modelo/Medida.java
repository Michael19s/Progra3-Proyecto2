package aplicacioninstrumentos.modelo;

public class Medida
{
    public Medida(int pNumero, int pReferencia, int pLectura, int pNumeroCalibracion) 
    {
        this.aNumero = pNumero;
        this.aReferencia = pReferencia;
        this.aLectura = pLectura;
        this.aNumeroCalibracion = pNumeroCalibracion;
    }

    public int obtenerNumero() 
    {
        return aNumero;
    }

    public void establecerNumero(int pNumero)
    {
        this.aNumero = pNumero;
    }

    public int obtenerReferencia() 
    {
        return aReferencia;
    }

    public void establecerReferencia(int pReferencia) 
    {
        this.aReferencia = pReferencia;
    }

    public int obtenerLectura() 
    {
        return aLectura;
    }

    public void establecerLectura(int pLectura) 
    {
        this.aLectura = pLectura;
    }
    
    public int obtenerNumeroCalibracion()
    {
        return aNumeroCalibracion;
    }

    public void actualizarNumeroCalibracion(int pNumeroCalibracion)
    {
        this.aNumeroCalibracion = pNumeroCalibracion;
    }
    
    public static String[] obtenerDescripcion()
    {
        return aDescripcion;
    }
    
    private static final String[] aDescripcion = {"Numero", "Numero de referencia", "Numero de lectura"};
    
    private int aNumero;
    private int aReferencia;
    private int aLectura;
    private int aNumeroCalibracion;
}
