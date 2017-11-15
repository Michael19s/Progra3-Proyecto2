package aplicacioninstrumentos.modelo;

import aplicacioninstrumentos.DAL.GestorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestorTipoInstrumentos 
{
    public GestorTipoInstrumentos() throws InstantiationException, ClassNotFoundException, IllegalAccessException
    {
        aBaseDatos = GestorBaseDatos.obtenerGestorBD( GestorBaseDatos.GBD.MYSQL_SERVER, GestorBaseDatos.SERVIDOR_POR_DEFECTO);
    }
    
    public static GestorTipoInstrumentos obtenerInstancia() throws InstantiationException, ClassNotFoundException, IllegalAccessException 
    {
        if (aInstancia == null) 
            aInstancia = new GestorTipoInstrumentos();
        return aInstancia;
    }

    public boolean agregar(TipoInstrumento nuevoTipoInstrumento)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_AGREGAR)) {
                lvPaso.clearParameters();
                lvPaso.setString(1, nuevoTipoInstrumento.obtenerCodigo());
                lvPaso.setString(2, nuevoTipoInstrumento.obtenerNombre());
                lvPaso.setString(3, nuevoTipoInstrumento.obtenerUnidadMedicion());

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

    public TipoInstrumento recuperar(String pCodigo)
    {
        TipoInstrumento lvTipoInstrumento = null;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_RECUPERAR)) 
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pCodigo);

                try (ResultSet rs = lvPaso.executeQuery()) 
                {
                    if (rs.next()) 
                        lvTipoInstrumento = new TipoInstrumento(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("UnidadMedicion"));
                }
            }
        } 
        catch (SQLException ex)
        {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return lvTipoInstrumento;
    }

    public boolean actualizar(TipoInstrumento pTipoInstrumento)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ACTUALIZAR))
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pTipoInstrumento.obtenerNombre());
                lvPaso.setString(2, pTipoInstrumento.obtenerUnidadMedicion());
                lvPaso.setString(3, pTipoInstrumento.obtenerCodigo());

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

    public boolean eliminar(String pCodigo)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ELIMINAR))
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pCodigo);

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

    public List<TipoInstrumento> listaTipoInstrumentos()
    {
        List<TipoInstrumento> lvLista = new ArrayList<>();

        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_LISTAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new TipoInstrumento(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("UnidadMedicion")));
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
            case "Unidad de medicion":
                lvCriterioBusqueda = "UnidadMedicion";
                break;
            case "Codigo":
            case "Nombre":
                lvCriterioBusqueda = pFiltro;
                break;
            default:
                break;
        }
        return lvCriterioBusqueda;
    }
    
    public List<TipoInstrumento> buscar(String pFiltro, String pValor)
    {
        String CMD_BUSCAR;
        List<TipoInstrumento> lvLista = new ArrayList<>();
        String lvCriterioBusqueda = obtenerCriterioBusqueda(pFiltro);
        
        CMD_BUSCAR = "SELECT * FROM tipoinstrumento WHERE "+ lvCriterioBusqueda + " LIKE '%" + pValor + "%';";
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_BUSCAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new TipoInstrumento(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("UnidadMedicion")));
                }
            }
        }
        catch (SQLException ex) 
        {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return lvLista;
    }
    
    public List<TipoInstrumento> buscarAvanzado(String pNombre, String pUnidadMedicion)
    {
        String CMD_BUSCAR = obtenerComandoBusquedaAvanzada(pNombre, pUnidadMedicion);
        List<TipoInstrumento> lvLista = new ArrayList<>();
        if(!"".equals(CMD_BUSCAR))
        {
            try 
            {
                try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_BUSCAR)) 
                {
                    while (rs.next()) 
                    {
                        lvLista.add(new TipoInstrumento(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("UnidadMedicion")));
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
    
     private String obtenerComandoBusquedaAvanzada(String pNombre, String pUnidadMedicion)
    {
        String CMD_BUSCAR = "";
            if(!"".equals(pNombre) && !"".equals(pUnidadMedicion))
                CMD_BUSCAR = "SELECT * FROM tipoinstrumento WHERE Nombre LIKE '%" + pNombre + "%' AND UnidadMedicion LIKE '%" + pUnidadMedicion + "%';";
            else
                if(!"".equals(pNombre) && "".equals(pUnidadMedicion))
                    CMD_BUSCAR = "SELECT * FROM tipoinstrumento WHERE Nombre LIKE '%" + pNombre + "%';";
                else
                    if("".equals(pNombre) && !"".equals(pUnidadMedicion))
                        CMD_BUSCAR = "SELECT * FROM tipoinstrumento WHERE UnidadMedicion LIKE '%" + pUnidadMedicion + "%';";
        return CMD_BUSCAR;
    }
    
    private Object[][] obtenerTablaX(List<TipoInstrumento> pTipoInstrumentos)
    {
        int lvIndice = 0;
        Object[][] r = new Object[pTipoInstrumentos.size()][3];
        for (TipoInstrumento lvTipoInstrumento : pTipoInstrumentos)
        {
            r[lvIndice][0] = lvTipoInstrumento.obtenerCodigo();
            r[lvIndice][1] = lvTipoInstrumento.obtenerNombre();
            r[lvIndice][2] = lvTipoInstrumento.obtenerUnidadMedicion();
            lvIndice++;
        }
        return r;
    }
    
    public Object[][] obtenerTabla()
    {
        List<TipoInstrumento> lvTipoInstrumentos = listaTipoInstrumentos();
        return obtenerTablaX(lvTipoInstrumentos);
    }
    
    public Object[][] obtenerTablaBusqueda(String pFiltro, String pValor)
    {
        List<TipoInstrumento> lvTipoInstrumentos = buscar(pFiltro, pValor);
        return obtenerTablaX(lvTipoInstrumentos);
    }
    
    public Object[][] obtenerTablaBusquedaAvanzada(String pNombre, String pUnidadMedicion)
    {
        List<TipoInstrumento> lvTipoInstrumentos = buscarAvanzado(pNombre, pUnidadMedicion);
        return obtenerTablaX(lvTipoInstrumentos);
    }
    
    private static final String BASE_DATOS = "instrumentos";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR = "SELECT Codigo, Nombre, UnidadMedicion FROM tipoinstrumento ORDER BY Codigo;";
    private static final String CMD_AGREGAR = "INSERT INTO tipoinstrumento VALUES (?, ?, ?);";
    private static final String CMD_RECUPERAR = "SELECT Codigo, Nombre, UnidadMedicion FROM tipoinstrumento WHERE Codigo= ?;";
    private static final String CMD_ACTUALIZAR = "UPDATE tipoinstrumento SET Nombre = ?, UnidadMedicion= ? WHERE Codigo= ?;";
    private static final String CMD_ELIMINAR = "DELETE FROM tipoinstrumento WHERE Codigo = ?;";

    private static GestorTipoInstrumentos aInstancia = null;
    private final GestorBaseDatos aBaseDatos;
}