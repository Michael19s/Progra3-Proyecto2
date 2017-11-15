package aplicacioninstrumentos.Control;


import aplicacioninstrumentos.modelo.Calibracion;
import aplicacioninstrumentos.modelo.Instrumento;
import aplicacioninstrumentos.modelo.Medida;
import aplicacioninstrumentos.modelo.Modelo;
import aplicacioninstrumentos.modelo.TipoInstrumento;
import aplicacioninstrumentos.vista.SubVentanaTipoInstrumento;
import aplicacioninstrumentos.vista.SubVentanaTipoInstrumento.modo;
import aplicacioninstrumentos.vista.VentanaCalibraciones;
import aplicacioninstrumentos.vista.VentanaInclusionCalibracion;
import aplicacioninstrumentos.vista.VentanaInclusionCalibracion.modoCalibracion;
import aplicacioninstrumentos.vista.VentanaInclusionInstrumento;
import aplicacioninstrumentos.vista.VentanaInclusionInstrumento.modoInstrumento;
import aplicacioninstrumentos.vista.VentanaInclusionMedidas;
import aplicacioninstrumentos.vista.VentanaInclusionMedidas.modoMedidas;
import aplicacioninstrumentos.vista.VentanaInstrumentos;
import aplicacioninstrumentos.vista.VentanaMedidas;
import aplicacioninstrumentos.vista.VentanaTipoInstrumentos;
import java.sql.Date;
import java.util.Observer;
import javax.swing.JFrame;

public class ControlAplicacion 
{
    public ControlAplicacion(Modelo pDatos) 
    {
        System.out.println("Inicializando el control del programa..");
        
        System.out.println("Asociando el modelo del programa..");
        this.aDatos = pDatos;
    }
    
    public ControlAplicacion()
    {
        this(new Modelo());
    }
    
    //--------------------------------Manejo de tipo de instrumento--------------------------------
    public boolean agregarTipoInstrumeto(String pCodigo, String pNombre, String pUnidadMedicion)
    {
        return aDatos.agregarTipoInstrumeto(pCodigo, pNombre, pUnidadMedicion);
    }
    
    public boolean eliminarTipoInstrumeto(String pCodigo)
    {
        return aDatos.eliminarTipoInstrumeto(pCodigo);
    }
    
    public TipoInstrumento recuperarTipoInstrumento(String pCodigo)
    {
        return aDatos.recuperarTipoInstrumento(pCodigo);
    }
    
    public boolean modificarTipoInstrumento(String pCodigo, String pNombre, String pUnidadMedicion)
    {
        return aDatos.modificarTipoInstrumento(pCodigo, pNombre, pUnidadMedicion);
    }
    //----------------------------------------------------------------------------------------------------------
    //------------------------------------------Manejo de instrumento-------------------------------------------
    public boolean agregarInstrumento(String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
    {
        return aDatos.agregarInstrumento(pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia);
    }
    
    public boolean eliminarInstrumento(String pNumeroSerie)
    {
        return aDatos.eliminarInstrumento(pNumeroSerie);
    }
    
    public Instrumento recuperarInstrumento(String pNumeroSerie)
    {
        return aDatos.recuperarInstrumento(pNumeroSerie);
    }
    
    public boolean modificarInstrumento(String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
    {
        return aDatos.modificarInstrumento(pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia);
    }
    //----------------------------------------------------------------------------------------------------------
    //-----------------------------------------Manejo de Calibraciones------------------------------------------   
    public boolean agregarCalibracion(int pNumeroCalibracion, String pInstrumento, Date pFecha, int pMediciones) 
    {
        return aDatos.agregarCalibracion(pNumeroCalibracion, pInstrumento, pFecha, pMediciones);
    }
    
    public boolean eliminarCalibracion(int pNumeroCalibracion) 
    {
        return aDatos.eliminarCalibracion(pNumeroCalibracion);
    }
    
        public Calibracion recuperarCalibracion(int pNumero)
    {
        return aDatos.recuperarCalibracion(pNumero);
    }
    
    public boolean modificarCalibracion(int pNumero, String pInstrumento, Date pFecha, int pMedidas)
    {
        return aDatos.modificarCalibracion(pNumero, pInstrumento, pFecha, pMedidas);
    }
    //----------------------------------------------------------------------------------------------------------
    //-----------------------------------------Manejo de medidas------------------------------------------------
    public boolean agregarMedida(int pNumero, int pReferencia, int pLectura, int pNumeroCalibracion)
    {
        return aDatos.agregarMedida(pNumero, pReferencia, pLectura, pNumeroCalibracion);
    }
    
    public boolean eliminarMedida(int pNumero)
    {
        return aDatos.eliminarMedida(pNumero);
    }
    
    public Medida recuperarMedida(int pNumero)
    {
        return aDatos.recuperarMedida(pNumero);
    }
    
    public boolean modificarMedida(int pNumero, int pReferencia, int pLectura, int pNumeroCalibracion)
    {
        return aDatos.modificarMedida(pNumero, pReferencia, pLectura, pNumeroCalibracion);
    }
    //--------------------------------------------------------------------------------------------------------------

    //Ventanas
    
    //----------------------------------------------------------------------------------------------------------
    //-----------------------------------------Tipo de instrumento----------------------------------------------
    public void mostrarVentanaTipoInstrumento(JFrame pBase)
    {
        System.out.println("Abriendo ventana de manejo de tipos de instrumentos..");
        VentanaTipoInstrumentos lvVentanaTipo = new VentanaTipoInstrumentos(this);
        registrar(lvVentanaTipo);
        lvVentanaTipo.init(pBase);
    }
    
    public void mostrarVentanaInclusionTipoInstrumento(modo pModo, JFrame pBase)
    {
        System.out.println("Abriendo ventana de inclusion de tipos de instrumentos..");
        SubVentanaTipoInstrumento lvVentanaTipo = new SubVentanaTipoInstrumento(this, pModo);
        registrar(lvVentanaTipo);
        lvVentanaTipo.init(pBase);
    }
    
    public void mostrarVentanaInclusionTipoInstrumento(modo pModo, JFrame pBase, String pCodigo, String pNombre, String pUnidadMedicion)
    {
        System.out.println("Abriendo ventana de inclusion de tipos de instrumentos..");
        SubVentanaTipoInstrumento lvVentanaTipo = new SubVentanaTipoInstrumento(this, pModo);
        lvVentanaTipo.modificarValoresTextos(pModo, pCodigo, pNombre, pUnidadMedicion);
        registrar(lvVentanaTipo);
        lvVentanaTipo.init(pBase);
    }
    //----------------------------------------------------------------------------------------------------------
    //---------------------------------------------Instrumento--------------------------------------------------
    public void mostrarVentanaInstrumento(JFrame pBase)
    {
        System.out.println("Abriendo ventana de manejo de instrumentos..");
        VentanaInstrumentos lvVentanaInstrumentos = new VentanaInstrumentos(this);
        registrar(lvVentanaInstrumentos);
        lvVentanaInstrumentos.init(pBase);
    }
    
    public void mostrarVentanaInclusionInstrumento(modoInstrumento pModo, JFrame pBase)
    {
        System.out.println("Abriendo ventana de manejo de instrumentos..");
        VentanaInclusionInstrumento lvVentanaInstrumentos = new VentanaInclusionInstrumento(this, pModo);
        registrar(lvVentanaInstrumentos);
        lvVentanaInstrumentos.init(pBase);
    }
    
    public void mostrarVentanaInclusionInstrumento(modoInstrumento pModo, JFrame pBase, String pNumeroSerie, String pTipo, String pDescripcion, int pMinimo, int pMaximo, int pTolerancia)
    {
        System.out.println("Abriendo ventana de inclusion de tipos de instrumentos..");
        VentanaInclusionInstrumento lvVentanaInstrumentos = new VentanaInclusionInstrumento(this, pModo);
        lvVentanaInstrumentos.modificarValoresTextos(pModo, pNumeroSerie, pTipo, pDescripcion, pMinimo, pMaximo, pTolerancia);
        registrar(lvVentanaInstrumentos);
        lvVentanaInstrumentos.init(pBase);
    }
    //----------------------------------------------------------------------------------------------------------
    //---------------------------------------------Calibracion--------------------------------------------------
    public void mostrarVentanaCalibraciones(JFrame pBase)
    {
        System.out.println("Abriendo ventana de manejo de calibraciones de instrumentos..");
        VentanaCalibraciones lvVentanaCalibraciones = new VentanaCalibraciones(this);
        registrar(lvVentanaCalibraciones);
        lvVentanaCalibraciones.init(pBase);
    }
    
        public void mostrarVentanaInclusionCalibracion(modoCalibracion pModo, JFrame pBase)
    {
        System.out.println("Abriendo ventana de manejo de instrumentos..");
        VentanaInclusionCalibracion lvVentanaCalibracion = new VentanaInclusionCalibracion(this, pModo);
        registrar(lvVentanaCalibracion);
        lvVentanaCalibracion.init(pBase);
    }
    
    public void mostrarVentanaInclusionCalibracion(modoCalibracion pModo, JFrame pBase, int pNumero, String pInstrumento, Date pFecha, int pMediciones)
    {
        System.out.println("Abriendo ventana de inclusion de tipos de instrumentos..");
        VentanaInclusionCalibracion lvVentanaCalibracion = new VentanaInclusionCalibracion(this, pModo);
        lvVentanaCalibracion.modificarValoresTextos(pModo, pNumero, pInstrumento, pFecha, pMediciones);
        registrar(lvVentanaCalibracion);
        lvVentanaCalibracion.init(pBase);
    }
    //----------------------------------------------------------------------------------------------------------
    //------------------------------------------------Medida----------------------------------------------------
    public void mostrarVentanaMedidas(JFrame pBase)
    {
        System.out.println("Abriendo ventana de manejo de medidas de instrumentos..");
        VentanaMedidas lvVentanaMedidas = new VentanaMedidas(this);
        registrar(lvVentanaMedidas);
        lvVentanaMedidas.init(pBase);
    }
    
    public void mostrarVentanaMedidas(JFrame pBase, int pNumeroCalibracion)
    {
        System.out.println("Abriendo ventana de manejo de medidas de instrumentos..");
        VentanaMedidas lvVentanaMedidas = new VentanaMedidas(this);
        lvVentanaMedidas.establecerNumeroCalibracion(pNumeroCalibracion);
        registrar(lvVentanaMedidas);
        lvVentanaMedidas.init(pBase);
    }
    
    public void mostrarVentanaInclusionMedidas(modoMedidas pModo,JFrame pBase, int pNumeroCalibracion)
    {
        System.out.println("Abriendo ventana de manejo de instrumentos..");
        VentanaInclusionMedidas lvVentanaMedidas = new VentanaInclusionMedidas(this, pModo);
        lvVentanaMedidas.esablecerNumeroCalibracion(pNumeroCalibracion);
        registrar(lvVentanaMedidas);
        lvVentanaMedidas.init(pBase);
    }
    
    public void mostrarVentanaInclusionMedidas(modoMedidas pModo, JFrame pBase, int pNumero, int pReferencia, int pLectura, int pNumeroCalibracion)
    {
        System.out.println("Abriendo ventana de inclusion de tipos de instrumentos..");
        VentanaInclusionMedidas lvVentanaMedidas = new VentanaInclusionMedidas(this, pModo);
        lvVentanaMedidas.esablecerNumeroCalibracion(pNumeroCalibracion);
        lvVentanaMedidas.modificarValoresTextos(pModo, pNumero, pReferencia, pLectura);
        registrar(lvVentanaMedidas);
        lvVentanaMedidas.init(pBase);
    }
    
    public void registrar(Observer pObservador)
    {
        aDatos.addObserver(pObservador);
    }
    
    public void eliminarRegistro(Observer pObservador)
    {
        aDatos.deleteObserver(pObservador);
    }
    
    
    private final Modelo aDatos;
}


