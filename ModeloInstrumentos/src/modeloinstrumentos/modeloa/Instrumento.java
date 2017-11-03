package modeloinstrumentos.modeloa;

public class Instrumento 
{

    public Instrumento(String pNumeroSerie, String pTipo, String pDescripcion, String pMinimo, String pMaximo, String pTolerancia)
    {
        this.aNumeroSerie = pNumeroSerie;
        this.aTipo = pTipo;
        this.aDescripcion = pDescripcion;
        this.aMinimo = pMinimo;
        this.aMaximo = pMaximo;
        this.aTolerancia = pTolerancia;
    }
    
    @Override
    public String toString() 
    {
        return String.format("{%s, %s %s, %s, %s, %s}", aNumeroSerie, obtenerTipo(), obtenerDescripcion(), obtenerMinimo(), obtenerMaximo(), obtenerTolerancia());
    }

    public String obtenerNumeroSerie()
    {
        return aNumeroSerie;
    }

    public void establecerNumeroSerie(String pNumeroSerie) 
    {
        this.aNumeroSerie = pNumeroSerie;
    }
        
    public String obtenerTipo() 
    {
        return aTipo;
    }
    
    public void establecerTipo(String pTipo) 
    {
        this.aTipo = pTipo;
    }

    public String obtenerDescripcion()
    {
        return aDescripcion;
    }
    
    public void establecerDescripcion(String pDescripcion) 
    {
        this.aDescripcion = pDescripcion;
    }

    public String obtenerMinimo() 
    {
        return aMinimo;
    }

    public void establecerMinimo(String pMinimo) 
    {
        this.aMinimo = pMinimo;
    }
    
    public String obtenerMaximo() 
    {
        return aMaximo;
    }

    public void establecerMaximo(String pMaximo) 
    {
        this.aMaximo = pMaximo;
    }

    public String obtenerTolerancia() 
    {
        return aTolerancia;
    }
  
    public void establecerTolerancia(String pTolerancia) 
    {
        this.aTolerancia = pTolerancia;
    }
    
    public static String[] obtenerDescripcionTbl()
    {
        return aDescripcionTbl;
    }
    
    private static final String[] aDescripcionTbl = {"Numero Serie", "Tipo", "Descripcion", "Maximo", "Minimo", "Tolerancia"};
    
    String aNumeroSerie;
    String aTipo;
    String aDescripcion;
    String aMinimo;
    String aMaximo;
    String aTolerancia;
}
