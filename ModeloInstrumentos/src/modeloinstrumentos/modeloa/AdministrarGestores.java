/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloinstrumentos.modeloa;

import java.sql.Date;

/**
 *
 * @author Johan
 */
public class AdministrarGestores {
       public AdministrarGestores() throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        aGestorCalibracion = new GestorCalibracion();
        aGestorInstrumentos = new GestorInstrumentos();
        aGestorMedidas = new GestorMedidas();
        aGestorTipoInstrumentos = new GestorTipoInstrumentos();
    }
public boolean agregarCalibracion(int pNumeroCalibracion, String pInstrumento, Date pFecha, String pMediciones) 
    {
        boolean lvProceso = false;
        Calibracion lvCalibracion = new Calibracion(pNumeroCalibracion, pInstrumento, pFecha, pMediciones);
        if (aGestorCalibracion.agregar(lvCalibracion)) {
            lvProceso = true;
        System.out.println("Se ingreso correctamente la calibracion ");
        }
       
        return lvProceso;
    }
    public boolean agregarInstrumento(String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia) {
        boolean lvProceso = false;
        if (aGestorInstrumentos.ingresaSerie(pNumeroSerie) == false) {
            Instrumento lvInstrumento = new Instrumento(pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia);
            if (aGestorInstrumentos.agregar(lvInstrumento)) {
                lvProceso = true;
                System.out.println("Se ingreso correctamente el instrumento ");
            }
        }

        return lvProceso;
    }
    public boolean agregarMedidas(int pNumero, int pReferencia, int pLectura)  {
        boolean lvProceso = false;

        Medida lvMedida = new Medida(pNumero, pReferencia, pLectura);
        if (aGestorMedidas.agregar(lvMedida)) {
            lvProceso = true;
            System.out.println("Se ingreso correctamente la medida ");
        }

        return lvProceso;
    }
    
    public boolean agregarTipoInstrumeto(String pCodigo, String pNombre, String pUnidadMedicion) {
        boolean lvProceso = false;

        TipoInstrumento lvTipoInstrumento = new TipoInstrumento(pCodigo, pNombre, pUnidadMedicion);
        if (aGestorTipoInstrumentos.agregar(lvTipoInstrumento)) {
            lvProceso = true;
            System.out.println("Se ingreso correctamente el tipo instrumeto");
        }

        return lvProceso;
    }

    
    private GestorMedidas aGestorMedidas;
    private GestorCalibracion aGestorCalibracion;
    private GestorInstrumentos aGestorInstrumentos;
    private GestorTipoInstrumentos aGestorTipoInstrumentos; 
}
