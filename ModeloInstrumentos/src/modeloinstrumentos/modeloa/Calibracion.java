package modeloinstrumentos.modeloa;

public class Calibracion 
{
    public Calibracion(String pNumeroCalibracion, String pInstrumento, String pFecha, String pMediciones) 
    {
        this.aNumeroCalibracion = pNumeroCalibracion;
        this.aInstrumento = pInstrumento;
        this.aFecha = pFecha;
        this.aMediciones = pMediciones;
    }

    public String obtenerNumeroCalibracion() 
    {
        return aNumeroCalibracion;
    }

    public void setaNumeroCalibracion(String pNumeroCalibracion) 
    {
        this.aNumeroCalibracion = pNumeroCalibracion;
    }

    public String establecerInstrumento() 
    {
        return aInstrumento;
    }

    public void establecerInstrumento(String pInstrumento) 
    {
        this.aInstrumento = pInstrumento;
    }

    public String obtenerFecha() 
    {
        return aFecha;
    }

    public void establecerFecha(String pFecha) {
        this.aFecha = pFecha;
    }

    public String obtenerMediciones() 
    {
        return aMediciones;
    }

    public void establecerMediciones(String pMediciones)
    {
        this.aMediciones = pMediciones;
    }
    
    
    
    String aNumeroCalibracion;
    String aInstrumento;
    String aFecha;
    String aMediciones;
}
