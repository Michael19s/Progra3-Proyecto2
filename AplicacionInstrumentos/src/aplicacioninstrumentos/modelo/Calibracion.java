package aplicacioninstrumentos.modelo;

import java.sql.Date;

public class Calibracion 
{
    public Calibracion(int pNumeroCalibracion, String pInstrumento, Date pFecha, String pMediciones) 
    {
        this.aNumeroCalibracion = pNumeroCalibracion;
        this.aInstrumento = pInstrumento;
        this.aFecha = pFecha;
        this.aMediciones = pMediciones;
    }

    public int obtenerNumeroCalibracion() 
    {
        return aNumeroCalibracion;
    }

    public void setaNumeroCalibracion(int pNumeroCalibracion) 
    {
        this.aNumeroCalibracion = pNumeroCalibracion;
    }

    public String obtenerInstrumento() 
    {
        return aInstrumento;
    }

    public void establecerInstrumento(String pInstrumento) 
    {
        this.aInstrumento = pInstrumento;
    }

    public Date obtenerFecha() 
    {
        return aFecha;
    }

    public void establecerFecha(Date pFecha) {
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
    
    public static String[] obtenerDescripcion()
    {
        return aDescripcion;
    }
    
    private static final String[] aDescripcion = {"Numero de calibracion", "Instrumento", "Fecha", "Mediciones"};

    
    int aNumeroCalibracion;
    String aInstrumento;
    Date aFecha;
    String aMediciones;
}
