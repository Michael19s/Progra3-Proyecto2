/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioninstrumentos.Control;


import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author Johan
 */
public class ControlAplicacion {
    
    public ControlAplicacion(Modelo pDatos) 
    {
        System.out.println("Inicializando el control del programa..");
        
        System.out.println("Asociando el modelo del programa..");
        this.aDatos = pDatos;
    }

    
   public boolean agregarCalibraciones(String pNumeroCalibracion, String pInstrumento, String pFecha, String pMediciones) 
    {
        boolean lvCalibracion = false;
        if(aDatos.agregarCalibracion(pNumeroCalibracion, pInstrumento, pFecha, pMediciones))
        {
         
             lvCalibracion  = true;
        }
        return  lvCalibracion;
    }
 public boolean agregarInstrumetos(String pNumeroSerie, String pTipo, String pDescripcion, String pMinimo, String pMaximo, String pTolerancia) 
    {
        boolean lvInstrumeto = false;
        if(aDatos.agregarInstrumeto(pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia))
        {
           
            lvInstrumeto  = true;
        }
        return  lvInstrumeto;
    }
   public boolean agregarMediciones(String pNumero, String pReferencia, String pLectura) 
    {
        boolean lvMedida = false;
        if(aDatos.agregarMedicion(pNumero, pReferencia, pLectura))
        {
           
             lvMedida  = true;
        }
        return  lvMedida;
    }
    public boolean agregarTipoInstrumetos(String pCodigo, String pNombre, String pUnidadMedicion) {
        boolean lvTipoInstrumeto = false;
        if (aDatos.agregarTipoInstrumeto(pCodigo, pNombre, pUnidadMedicion)) {
           
            lvTipoInstrumeto = true;
        }
        return lvTipoInstrumeto;
    }
    
    public void registrar(Observer pObservador)
    {
        aDatos.addObserver(pObservador);
    }
    
    public void eliminarRegistro(Observer pObservador)
    {
        aDatos.deleteObserver(pObservador);
    }
    // este error se debe   a que nose como unir armbos proyectos para unir los que es modelo con el control
    // solo de da en la version origial , en la copia no dio problema asi que se lo dejo a su criterio 
    private final Modelo aDatos;
}


