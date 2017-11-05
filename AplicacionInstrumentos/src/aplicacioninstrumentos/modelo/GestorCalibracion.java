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
                lvPaso.setInt(1, pNuevaCalibracion.obtenerNumeroCalibracion());
                lvPaso.setString(2, pNuevaCalibracion.obtenerInstrumento());
                lvPaso.setDate(3, pNuevaCalibracion.obtenerFecha());
                lvPaso.setString(4, pNuevaCalibracion.obtenerMediciones());

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

    public Calibracion recuperar(String pNumero)
    {
        Calibracion lvCalibracion = null;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_RECUPERAR)) 
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pNumero);

                try (ResultSet rs = lvPaso.executeQuery()) 
                {
                    if (rs.next()) 
                        lvCalibracion = new Calibracion(rs.getInt("Numero"), rs.getString("Instrumento"), rs.getDate("Fecha"), rs.getString("Mediciones"));
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
                lvPaso.setString(3, pCalibracion.obtenerMediciones());
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

    public boolean eliminar(String pNumero)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ELIMINAR))
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pNumero);

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
                    lvLista.add(new Calibracion(rs.getInt("Numero"), rs.getString("Instrumento"), rs.getDate("Fecha"), rs.getString("Mediciones")));
                }
            }
        }
        catch (SQLException ex) 
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvLista;
    }
    
    public Object[][] obtenerTabla()
    {
        List<Calibracion> lvCalibraciones = listaCalibraciones();
        Object[][] r = new Object[lvCalibraciones.size()][6];
        int lvIndice = 0;
        for (Calibracion lvCalibracion : lvCalibraciones)
        {
            r[lvIndice][0] = lvCalibracion.obtenerNumeroCalibracion();
            r[lvIndice][1] = lvCalibracion.obtenerInstrumento();
            r[lvIndice][2] = lvCalibracion.obtenerFecha();
            r[lvIndice][3] = lvCalibracion.obtenerMediciones();
            lvIndice++;
        }
        return r;
    }
    
        public List<Calibracion> buscar(String pFiltro, String pValor)
    {
        String CMD_BUSCAR = "";
        List<Calibracion> lvLista = new ArrayList<>();
        String lvCriterioBusqueda = "";
        boolean lvEsString = true;
        switch (pFiltro)
        {
            case "Numero de calibracion":
                lvCriterioBusqueda = "Numero";
                lvEsString = false;
                break;
            case "Instrumento":
                lvCriterioBusqueda = pFiltro;
                break;
            case "Fecha":
                lvCriterioBusqueda = pFiltro;
                lvEsString = false;
                break;
            case "Mediciones":
                lvCriterioBusqueda = pFiltro;
                break;
            default:
                break;
        }
        if(lvEsString || pValor.equals(""))
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
                    lvLista.add(new Calibracion(rs.getInt("Numero"), rs.getString("Instrumento"), rs.getDate("Fecha"), rs.getString("Mediciones")));
                }
            }
        }
        catch (SQLException ex) 
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvLista;
    }
    
    public Object[][] obtenerTablaBusqueda(String pFiltro, String pValor)
    {
        List<Calibracion> lvCalibraciones = buscar(pFiltro, pValor);
        Object[][] r = new Object[lvCalibraciones.size()][4];
        int lvIndice = 0;
        for (Calibracion lvCalibracion : lvCalibraciones)
        {
            r[lvIndice][0] = lvCalibracion.obtenerNumeroCalibracion();
            r[lvIndice][1] = lvCalibracion.obtenerInstrumento();
            r[lvIndice][2] = lvCalibracion.obtenerFecha();
            r[lvIndice][3] = lvCalibracion.obtenerMediciones();
            lvIndice++;
        }
        return r;
    }
    
    private static final String BASE_DATOS = "instrumentos";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR = "SELECT Numero, Instrumento, Fecha, Mediciones FROM calibracion ORDER BY Numero;";
    private static final String CMD_AGREGAR = "INSERT INTO calibracion VALUES (?, ?, ?, ?);";
    private static final String CMD_RECUPERAR = "SELECT Numero, Instrumento, Fecha, Mediciones FROM calibracion WHERE Numero = ?;";
    private static final String CMD_ACTUALIZAR = "UPDATE calibracion SET Instrumento = ?, Fecha = ?, Medicion = ? WHERE Numero = ?;";
    private static final String CMD_ELIMINAR = "DELETE FROM calibracion WHERE Numero = ?;";

    private static GestorCalibracion aInstancia = null;
    private final GestorBaseDatos aBaseDatos;
}
