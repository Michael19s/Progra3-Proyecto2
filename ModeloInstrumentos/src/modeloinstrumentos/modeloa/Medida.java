package modeloinstrumentos.modeloa;

public class Medida
{

    public Medida(String pNumero, String pReferencia, String pLectura) 
    {
        this.aNumero = pNumero;
        this.aReferencia = pReferencia;
        this.aLectura = pLectura;
    }

    public String obtenerNumero() 
    {
        return aNumero;
    }

    public void establecerNumero(String pNumero)
    {
        this.aNumero = pNumero;
    }

    public String obtenerReferencia() 
    {
        return aReferencia;
    }

    public void establecerReferencia(String pReferencia) 
    {
        this.aReferencia = pReferencia;
    }

    public String obtenerLectura() 
    {
        return aLectura;
    }

    public void establecerLectura(String pLectura) 
    {
        this.aLectura = pLectura;
    }
    
    String aNumero;
    String aReferencia;
    String aLectura;
}
