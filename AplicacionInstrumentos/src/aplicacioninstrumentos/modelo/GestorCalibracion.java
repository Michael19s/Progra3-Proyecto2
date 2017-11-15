package aplicacioninstrumentos.modelo;

import aplicacioninstrumentos.DAL.GestorBaseDatos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestorCalibracion 
{
    public GestorCalibracion() throws InstantiationException, ClassNotFoundException, IllegalAccessException
    {
        aBaseDatos = GestorBaseDatos.obtenerGestorBD( GestorBaseDatos.GBD.MYSQL_SERVER, GestorBaseDatos.SERVIDOR_POR_DEFECTO);
    }
    
    public static GestorCalibracion obtenerInstancia() throws InstantiationException, ClassNotFoundException, IllegalAccessException 
    {
        if (aInstancia == null) 
            aInstancia = new GestorCalibracion();
        return aInstancia;
    }
    
    public boolean agregar(Calibracion pNuevaCalibracion)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_AGREGAR)) {
                lvPaso.clearParameters();
                //Existe el setInt x aquello de que luego se genere una bronca
                lvPaso.setString(1, pNuevaCalibracion.obtenerInstrumento());
                lvPaso.setDate(2, pNuevaCalibracion.obtenerFecha());
                lvPaso.setInt(3, pNuevaCalibracion.obtenerMediciones());

                int r = lvPaso.executeUpdate();
                lvValorRetorno = (r == 1);
            }
        } 
        catch (SQLException ex)
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvValorRetorno;
    }

    public Calibracion recuperar(int pNumero)
    {
        Calibracion lvCalibracion = null;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_RECUPERAR)) 
            {
                lvPaso.clearParameters();
                lvPaso.setInt(1, pNumero);

                try (ResultSet rs = lvPaso.executeQuery()) 
                {
                    if (rs.next()) 
                        lvCalibracion = new Calibracion(rs.getInt("Numero"), rs.getString("Instrumento"), rs.getDate("Fecha"), rs.getInt("Mediciones"));
                }
            }
        } 
        catch (SQLException ex)
        {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return lvCalibracion;
    }

    public boolean actualizar(Calibracion pCalibracion)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ACTUALIZAR))
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pCalibracion.obtenerInstrumento());
                lvPaso.setDate(2, pCalibracion.obtenerFecha());
                lvPaso.setInt(3, pCalibracion.obtenerMediciones());
                lvPaso.setInt(4, pCalibracion.obtenerNumeroCalibracion());

                int r = lvPaso.executeUpdate();
                lvValorRetorno = (r == 1);
            }
        }
        catch (SQLException ex)
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvValorRetorno;
    }

    public boolean eliminar(int pNumero)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ELIMINAR))
            {
                lvPaso.clearParameters();
                lvPaso.setInt(1, pNumero);

                int r = lvPaso.executeUpdate();
                lvValorRetorno = (r == 1);
            }
        }
        catch (SQLException ex) 
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvValorRetorno;
    }
    
    public List<Calibracion> listaCalibraciones()
    {
        List<Calibracion> lvLista = new ArrayList<>();

        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_LISTAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new Calibracion(rs.getInt("Numero"), rs.getString("Instrumento"), rs.getDate("Fecha"), rs.getInt("Mediciones")));
                }
            }
        }
        catch (SQLException ex) 
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvLista;
    }
    
    private String obtenerCriterioBusqueda(String pFiltro)
    {
        String lvCriterioBusqueda = "";
        switch (pFiltro)
        {
            case "Numero de calibracion":
                lvCriterioBusqueda = "Numero";
                break;
            case "Instrumento":
            case "Fecha":
            case "Mediciones":
                lvCriterioBusqueda = pFiltro;
                break;
            default:
                break;
        }
        return lvCriterioBusqueda;
    }
    
    public List<Calibracion> buscar(String pFiltro, String pValor)
    {
        String CMD_BUSCAR = obtenerCriterioBusqueda(pFiltro);
        List<Calibracion> lvLista = new ArrayList<>();
        String lvCriterioBusqueda = "";
       
        if(lvCriterioBusqueda.equals("Instrumento") || pValor.equals(""))
            CMD_BUSCAR = "SELECT * FROM calibracion WHERE "+ lvCriterioBusqueda + " LIKE '%" + pValor + "%';";
        else
            if(lvCriterioBusqueda.equals("Fecha"))
                CMD_BUSCAR = "SELECT * FROM calibracion WHERE "+ lvCriterioBusqueda + " LIKE '%" + Date.valueOf(pValor) + "%';";
            else
                if(lvCriterioBusqueda.equals("Numero"))
                    CMD_BUSCAR = "SELECT * FROM calibracion WHERE "+ lvCriterioBusqueda + " LIKE '%" + Integer.parseInt(pValor) + "%';";   
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_BUSCAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new Calibracion(rs.getInt("Numero"), rs.getString("Instrumento"), rs.getDate("Fecha"), rs.getInt("Mediciones")));
                }
            }
        }
        catch (SQLException ex) 
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvLista;
    }
        
    public List<Calibracion> buscarAvanzado(String pInstrumento, Date pFecha, String pNumeroMediciones)
    {
        String CMD_BUSCAR = obtenerComandoBusquedaAvanzada(pInstrumento, pFecha, pNumeroMediciones);
        List<Calibracion> lvLista = new ArrayList<>();
        if(!"".equals(CMD_BUSCAR))
        {
            try 
            {
                try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_BUSCAR)) 
                {
                    while (rs.next()) 
                    {
                        lvLista.add(new Calibracion(rs.getInt("Numero"), rs.getString("Instrumento"), rs.getDate("Fecha"), rs.getInt("Mediciones")));
                    }
                }
            }
            catch (SQLException ex) 
            {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
            }
        }
        return lvLista;
    }
    
     private String obtenerComandoBusquedaAvanzada(String pInstrumento, Date pFecha, String pNumeroMediciones)
    {
        String CMD_BUSCAR = "";
            if(!"".equals(pInstrumento) && pFecha != null && !"".equals(pNumeroMediciones))
                CMD_BUSCAR = "SELECT * FROM calibracion WHERE Instrumento LIKE '%" + pInstrumento + "%' AND Fecha LIKE '%" + pFecha + "%' AND Mediciones LIKE '%" + pNumeroMediciones + "%';";
            else
                if(!"".equals(pInstrumento) && pFecha != null && !"".equals(pNumeroMediciones))
                    CMD_BUSCAR = "SELECT * FROM calibracion WHERE Instrumento LIKE '%" + pInstrumento + "%' AND Mediciones LIKE '%" + pNumeroMediciones + "%';";
                else
                    if(!"".equals(pInstrumento) && pFecha != null && "".equals(pNumeroMediciones))
                        CMD_BUSCAR = "SELECT * FROM calibracion WHERE Instrumento LIKE '%" + pInstrumento + "%' AND Fecha LIKE '%" + pFecha + "%';";
                    else
                        if(!"".equals(pInstrumento) && pFecha != null && "".equals(pNumeroMediciones))
                            CMD_BUSCAR = "SELECT * FROM calibracion WHERE Instrumento LIKE '%" + pInstrumento + "%';";
                        else
                            if("".equals(pInstrumento) &&  pFecha != null && !"".equals(pNumeroMediciones))
                                CMD_BUSCAR = "SELECT * FROM calibracion WHERE Fecha LIKE '%" + pFecha + "%' AND Mediciones LIKE '%" + pNumeroMediciones + "%';";
                            else
                                if("".equals(pInstrumento) &&  pFecha != null && "".equals(pNumeroMediciones))
                                    CMD_BUSCAR = "SELECT * FROM calibracion WHERE Fecha LIKE '%" + pFecha + "%';";
                                else
                                    if("".equals(pInstrumento) &&  pFecha != null && !"".equals(pNumeroMediciones))
                                        CMD_BUSCAR = "SELECT * FROM calibracion WHERE Mediciones LIKE '%" + pNumeroMediciones + "%';";
        return CMD_BUSCAR;
    }
     
     private Object[][] obtenerTablaX(List<Calibracion> pCalibraciones)
     {
        Object[][] r = new Object[pCalibraciones.size()][4];
        int lvIndice = 0;
        for (Calibracion lvCalibracion : pCalibraciones)
        {
            r[lvIndice][0] = lvCalibracion.obtenerNumeroCalibracion();
            r[lvIndice][1] = lvCalibracion.obtenerInstrumento();
            r[lvIndice][2] = lvCalibracion.obtenerFecha();
            r[lvIndice][3] = lvCalibracion.obtenerMediciones();
            lvIndice++;
        }
        return r;
     }
    
    public Object[][] obtenerTabla()
    {
        List<Calibracion> lvCalibraciones = listaCalibraciones();
        return obtenerTablaX(lvCalibraciones);
    }
     
    public Object[][] obtenerTablaBusqueda(String pFiltro, String pValor)
    {
        List<Calibracion> lvCalibraciones = buscar(pFiltro, pValor);
        return obtenerTablaX(lvCalibraciones);
    }
    
    public Object[][] obtenerTablaBusquedaAvanzada(String pInstrumento, Date pFecha, String pMediciones)
    {
        List<Calibracion> lvCalibraciones = buscarAvanzado(pInstrumento, pFecha, pMediciones);
        return obtenerTablaX(lvCalibraciones);
    }
    
    private static final String BASE_DATOS = "instrumentos";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR = "SELECT Numero, Instrumento, Fecha, Mediciones FROM calibracion ORDER BY Numero;";
    private static final String CMD_AGREGAR = "INSERT INTO calibracion VALUES (null, ?, ?, ?);";
    private static final String CMD_RECUPERAR = "SELECT Numero, Instrumento, Fecha, Mediciones FROM calibracion WHERE Numero = ?;";
    private static final String CMD_ACTUALIZAR = "UPDATE calibracion SET Instrumento = ?, Fecha = ?, Mediciones = ? WHERE Numero = ?;";
    private static final String CMD_ELIMINAR = "DELETE FROM calibracion WHERE Numero = ?;";

    private static GestorCalibracion aInstancia = null;
    private final GestorBaseDatos aBaseDatos;
}
