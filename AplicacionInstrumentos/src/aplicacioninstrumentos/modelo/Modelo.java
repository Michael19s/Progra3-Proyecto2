package aplicacioninstrumentos.modelo;

import java.sql.Date;
import java.util.Observable;

public class Modelo extends Observable 
{
    public Modelo()
    {
        System.out.println("Inicializando el modelo..");
    }
    
    //--------------------------------Manejo de tipo de instrumento--------------------------------
    public boolean agregarTipoInstrumeto(String pCodigo, String pNombre, String pUnidadMedicion)
    {
        boolean lvExito = false;

        TipoInstrumento lvTipoInstrumento = new TipoInstrumento(pCodigo, pNombre, pUnidadMedicion);
        try
        {
            GestorTipoInstrumentos.obtenerInstancia().agregar(lvTipoInstrumento);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("Se ingreso correctamente el tipo instrumeto");
            
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
    public boolean eliminarTipoInstrumeto(String pCodigo)
    {
        boolean lvExito = false;
        try
        {
            GestorTipoInstrumentos.obtenerInstancia().eliminar(pCodigo);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("Se elimino correctamente el tipo instrumeto");
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
    public TipoInstrumento recuperarTipoInstrumento(String pCodigo)
    {
        TipoInstrumento lvTipo = null;
        try
        {
            lvTipo = GestorTipoInstrumentos.obtenerInstancia().recuperar(pCodigo);
            setChanged();
            notifyObservers();
            System.out.println("El  tipo se recupero correctamente");
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvTipo;
    }
    
    public boolean modificarTipoInstrumento(String pCodigo, String pNombre, String pUnidadMedicion)
    {
        boolean lvExito = false;
        TipoInstrumento lvTipoInstrumento = new TipoInstrumento(pCodigo, pNombre, pUnidadMedicion);
        try
        {
            GestorTipoInstrumentos.obtenerInstancia().actualizar(lvTipoInstrumento);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("Se actualizo correctamente el tipo instrumeto");
            
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    //----------------------------------------------------------------------------------------------------------
    //------------------------------------------Manejo de instrumento-------------------------------------------
    public boolean agregarInstrumento(String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
    {
        boolean lvExito = false;
        Instrumento lvInstrumento = new Instrumento(pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia);
        try 
        {
            GestorInstrumentos.obtenerInstancia().agregar(lvInstrumento);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("Se ingreso correctamente el instrumento ");
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
    public boolean eliminarInstrumento(String pNumeroSerie)
    {
        boolean lvExito = false;
        try 
        {
            GestorInstrumentos.obtenerInstancia().eliminar(pNumeroSerie);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("Se elimino correctamente el instrumento ");
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
    public Instrumento recuperarInstrumento(String pNumeroSerie)
    {
        Instrumento lvInstrumento = null;
        try
        {
            lvInstrumento = GestorInstrumentos.obtenerInstancia().recuperar(pNumeroSerie);
            setChanged();
            notifyObservers();
            System.out.println("El  instrumeto se recupero correctamente");
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvInstrumento;
    }
    
    public boolean modificarInstrumento(String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
    {
        boolean lvExito = false;
        Instrumento lvInstrumento = new Instrumento(pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia);
        try
        {
            GestorInstrumentos.obtenerInstancia().actualizar(lvInstrumento);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("El instrumeto se actualizo correctamente");
            
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    //----------------------------------------------------------------------------------------------------------
    //-----------------------------------------Manejo de Calibraciones------------------------------------------   
    public boolean agregarCalibracion(int pNumeroCalibracion, String pInstrumento, Date pFecha, int pMediciones) 
    {
        boolean lvExito = false;
        Calibracion lvNuevaCalibracion = new Calibracion(pNumeroCalibracion,pInstrumento, pFecha, pMediciones);
        try 
        {
            GestorCalibracion.obtenerInstancia().agregar(lvNuevaCalibracion);
            setChanged();
            notifyObservers();
            System.out.println("Se ingreso correctamente la calibracion ");
            lvExito = true;
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
    public boolean eliminarCalibracion(int pNumeroCalibracion) 
    {
        boolean lvExito = false;
        try 
        {
            GestorCalibracion.obtenerInstancia().eliminar(pNumeroCalibracion);
            setChanged();
            notifyObservers();
            System.out.println("Se ingreso correctamente la calibracion ");
            lvExito = true;
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
        public Calibracion recuperarCalibracion(int pNumero)
    {
        Calibracion lvCalibracion = null;
        try
        {
            lvCalibracion = GestorCalibracion.obtenerInstancia().recuperar(pNumero);
            setChanged();
            notifyObservers();
            System.out.println("La calibracion se recupero correctamente");
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvCalibracion;
    }
    
    public boolean modificarCalibracion(int pNumero, String pInstrumento, Date pFecha, int pMedidas)
    {
        boolean lvExito = false;
        Calibracion lvCalibracion = new Calibracion(pNumero, pInstrumento, pFecha, pMedidas);
        try
        {
            GestorCalibracion.obtenerInstancia().actualizar(lvCalibracion);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("La calibracion se actualizo correctamente");
            
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    //----------------------------------------------------------------------------------------------------------
    //-----------------------------------------Manejo de medidas------------------------------------------ 
    public boolean agregarMedida(int pNumero, int pReferencia, int pLectura, int pNumeroCalibracion)
    {
        boolean lvExito = false;
        Medida lvMedida = new Medida(pNumero, pReferencia, pLectura, pNumeroCalibracion);
        try
        {
            GestorMedidas.obtenerInstancia().agregar(lvMedida);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("Se elimino correctamente la medida");
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
    public boolean eliminarMedida(int pNumero)
    {
        boolean lvExito = false;
        try
        {
            GestorMedidas.obtenerInstancia().eliminar(pNumero);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("Se elimino correctamente la medida");
        } 
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
    
    public Medida recuperarMedida(int pNumero)
    {
        Medida lvMedida = null;
        try
        {
            lvMedida = GestorMedidas.obtenerInstancia().recuperar(pNumero);
            setChanged();
            notifyObservers();
            System.out.println("La medida se recupero correctamente");
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvMedida;
    }
    
    public boolean modificarMedida(int pNumero, int pReferencia, int pLectura, int pNumeroCalibracion)
    {
        boolean lvExito = false;
        Medida lvMedida = new Medida(pNumero, pReferencia, pLectura, pNumeroCalibracion);
        try
        {
            GestorMedidas.obtenerInstancia().actualizar(lvMedida);
            setChanged();
            notifyObservers();
            lvExito = true;
            System.out.println("La medida se actualizo correctamente");
            
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) 
        {
            System.out.printf("Mensaje de error: '%s'%n", ex.getMessage());
        }
        return lvExito;
    }
}
