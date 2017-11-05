package modeloinstrumentos.modeloa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modeloinstrumentos.modelo.dao.GestorBaseDatos;

public class GestorMedidas 
{
    public GestorMedidas() throws InstantiationException, ClassNotFoundException, IllegalAccessException
    {
        aBaseDatos = GestorBaseDatos.obtenerGestorBD( GestorBaseDatos.GBD.MYSQL_SERVER, GestorBaseDatos.SERVIDOR_POR_DEFECTO);
    }
    
    public static GestorMedidas obtenerInstancia() throws InstantiationException, ClassNotFoundException, IllegalAccessException 
    {
        if (aInstancia == null) 
            aInstancia = new GestorMedidas();
        return aInstancia;
    }

    public boolean agregar(Medida pNuevaMedida)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_AGREGAR)) {
                lvPaso.clearParameters();
                lvPaso.setInt(1, pNuevaMedida.obtenerNumero());
                lvPaso.setInt(2, pNuevaMedida.obtenerReferencia());
                lvPaso.setInt(3, pNuevaMedida.obtenerLectura());

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

    public Medida recuperar(String pNumero)
    {
        Medida lvMedida = null;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_RECUPERAR)) 
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pNumero);

                try (ResultSet rs = lvPaso.executeQuery()) 
                {
                    if (rs.next()) 
                        lvMedida = new Medida(rs.getInt("Numero"), rs.getInt("Referencia"), rs.getInt("Lectura"));
                }
            }
        } 
        catch (SQLException ex)
        {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return lvMedida;
    }

    public boolean actualizar(Medida pMedida)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ACTUALIZAR))
            {
                lvPaso.clearParameters();
                lvPaso.setInt(1, pMedida.obtenerReferencia());
                lvPaso.setInt(2, pMedida.obtenerLectura());
                lvPaso.setInt(3, pMedida.obtenerNumero());

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
    
    public List<Medida> listaMedidas()
    {
        List<Medida> lvLista = new ArrayList<>();

        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_LISTAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new Medida(rs.getInt("Numero"), rs.getInt("Referencia"), rs.getInt("Lectura")));
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
        List<Medida> lvMedidas = listaMedidas();
        Object[][] r = new Object[lvMedidas.size()][6];
        int lvIndice = 0;
        for (Medida lvMedida : lvMedidas)
        {
            r[lvIndice][0] = lvMedida.obtenerNumero();
            r[lvIndice][1] = lvMedida.obtenerReferencia();
            r[lvIndice][2] = lvMedida.obtenerLectura();
            lvIndice++;
        }
        return r;
    }

        public List<Medida> buscar(String pFiltro, String pValor)
    {
        String CMD_BUSCAR;
        List<Medida> lvLista = new ArrayList<>();
        String lvCriterioBusqueda = "";
        switch (pFiltro)
        {
            case "Numero de medida":
                lvCriterioBusqueda = "Numero";
                break;
            case "Numero de referencia":
                lvCriterioBusqueda = "Referencia";
                break;
            case "Numero de lectura":
                lvCriterioBusqueda = "Lectura";
                break;
            default:
                break;
        }
        if(!pValor.equals(""))
            CMD_BUSCAR = "SELECT * FROM medida WHERE "+ lvCriterioBusqueda + " LIKE '%" + Integer.parseInt(pValor) + "%';";
        else
            CMD_BUSCAR = "SELECT * FROM medida WHERE "+ lvCriterioBusqueda + " LIKE '%" + pValor + "%';";
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_BUSCAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new Medida(rs.getInt("Numero"), rs.getInt("Referencia"), rs.getInt("Lectura")));
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
        List<Medida> lvMedidas = buscar(pFiltro, pValor);
        Object[][] r = new Object[lvMedidas.size()][3];
        int lvIndice = 0;
        for (Medida lvMedida : lvMedidas)
        {
            r[lvIndice][0] = lvMedida.obtenerNumero();
            r[lvIndice][1] = lvMedida.obtenerReferencia();
            r[lvIndice][2] = lvMedida.obtenerLectura();
            lvIndice++;
        }
        return r;
    }
    
    private static final String BASE_DATOS = "instrumentos";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR = "SELECT Numero, Referencia, Lectura FROM medida ORDER BY Numero;";
    private static final String CMD_AGREGAR = "INSERT INTO medida VALUES (?, ?, ?);";
    private static final String CMD_RECUPERAR = "SELECT Numero, Referencia, Lectura FROM medida WHERE Numero = ?;";
    private static final String CMD_ACTUALIZAR = "UPDATE medida SET Referencia = ?, Lectura = ? WHERE Numero = ?;";
    private static final String CMD_ELIMINAR = "DELETE FROM medida WHERE Numero = ?;";

    private static GestorMedidas aInstancia = null;
    private final GestorBaseDatos aBaseDatos;
}
