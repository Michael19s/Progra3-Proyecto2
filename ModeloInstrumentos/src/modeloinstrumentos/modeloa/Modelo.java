/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloinstrumentos.modeloa;

import java.sql.Date;
import java.util.Observable;

/**
 *
 * @author Johan
 */
public class Modelo extends Observable {

    public Modelo() throws InstantiationException, ClassNotFoundException, IllegalAccessException {
     System.out.println("Inicializando el modelo..");
        aAdministrador= new AdministrarGestores();
        
    }
 public boolean agregarCalibracion(int pNumeroCalibracion, String pInstrumento, Date pFecha, String pMediciones) 
    {
        boolean lvCalibracion = false;
        if(aAdministrador.agregarCalibracion(pNumeroCalibracion, pInstrumento, pFecha, pMediciones))
        {
            setChanged();
            notifyObservers();
             lvCalibracion  = true;
        }
        return  lvCalibracion;
    }
 public boolean agregarInstrumeto(String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
    {
        boolean lvInstrumeto = false;
        if(aAdministrador.agregarInstrumento(pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia))
        {
            setChanged();
            notifyObservers();
            lvInstrumeto  = true;
        }
        return  lvInstrumeto;
    }
   public boolean agregarMedicion(int pNumero, int pReferencia, int pLectura)  
    {
        boolean lvMedida = false;
        if(aAdministrador.agregarMedidas(pNumero, pReferencia, pLectura))
        {
            setChanged();
            notifyObservers();
             lvMedida  = true;
        }
        return  lvMedida;
    }
    public boolean agregarTipoInstrumeto(String pCodigo, String pNombre, String pUnidadMedicion) {
        boolean lvTipoInstrumeto = false;
        if (aAdministrador.agregarTipoInstrumeto(pCodigo, pNombre, pUnidadMedicion)) {
            setChanged();
            notifyObservers();
            lvTipoInstrumeto = true;
        }
        return lvTipoInstrumeto;
    }

    public AdministrarGestores getaAdministrador() {
        return aAdministrador;
    }
    
private AdministrarGestores aAdministrador;
}
