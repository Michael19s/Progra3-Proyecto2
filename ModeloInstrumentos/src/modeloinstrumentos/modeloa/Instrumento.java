package modeloinstrumentos.modeloa;

public class Instrumento 
{

    public Instrumento(String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
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

    public int obtenerMinimo() 
    {
        return aMinimo;
    }

    public void establecerMinimo(int pMinimo) 
    {
        this.aMinimo = pMinimo;
    }
    
    public int obtenerMaximo() 
    {
        return aMaximo;
    }

    public void establecerMaximo(int pMaximo) 
    {
        this.aMaximo = pMaximo;
    }

    public int obtenerTolerancia() 
    {
        return aTolerancia;
    }
  
    public void establecerTolerancia(int pTolerancia) 
    {
        this.aTolerancia = pTolerancia;
    }
    
    public static String[] obtenerDescripcionTbl()
    {
        return aDescripcionTbl;
    }
    
    private static final String[] aDescripcionTbl = {"Numero de serie", "Tipo", "Descripcion", "Minimo", "Maximo", "Tolerancia"};
    
    private String aNumeroSerie;
    private String aTipo;
    private String aDescripcion;
    private int aMinimo;
    private int aMaximo;
    private int aTolerancia;
}
