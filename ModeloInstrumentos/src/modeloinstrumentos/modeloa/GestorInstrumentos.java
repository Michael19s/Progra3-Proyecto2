package modeloinstrumentos.modeloa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modeloinstrumentos.modelo.dao.GestorBaseDatos;

public class GestorInstrumentos 
{
    public GestorInstrumentos() throws InstantiationException, ClassNotFoundException, IllegalAccessException
    {
        aBaseDatos = GestorBaseDatos.obtenerGestorBD( GestorBaseDatos.GBD.MYSQL_SERVER, GestorBaseDatos.SERVIDOR_POR_DEFECTO);
    }
    
    public static GestorInstrumentos obtenerInstancia() throws InstantiationException, ClassNotFoundException, IllegalAccessException 
    {
        if (aInstancia == null) 
            aInstancia = new GestorInstrumentos();
        return aInstancia;
    }

    public boolean agregar(Instrumento nuevoInstrumento)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_AGREGAR)) {
                lvPaso.clearParameters();
                lvPaso.setString(1, nuevoInstrumento.obtenerNumeroSerie());
                lvPaso.setString(2, nuevoInstrumento.obtenerTipo());
                lvPaso.setString(3, nuevoInstrumento.obtenerDescripcion());
                lvPaso.setInt(4, nuevoInstrumento.obtenerMinimo());
                lvPaso.setInt(5, nuevoInstrumento.obtenerMaximo());
                lvPaso.setInt(6, nuevoInstrumento.obtenerTolerancia());

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

    public Instrumento recuperar(String pNumeroSerie)
    {
        Instrumento lvInstrumento = null;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_RECUPERAR)) 
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pNumeroSerie);

                try (ResultSet rs = lvPaso.executeQuery()) 
                {
                    if (rs.next()) 
                        lvInstrumento = new Instrumento(rs.getString("NumeroSerie"), rs.getString("Tipo"), rs.getString("Descripcion"), rs.getInt("Minimo"), rs.getInt("Maximo"), rs.getInt("Tolerancia"));
                }
            }
        } 
        catch (SQLException ex)
        {
            System.err.printf("Excepción: '%s'%n",
                    ex.getMessage());
        }
        return lvInstrumento;
    }

    public boolean actualizar(Instrumento pInstrumento)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ACTUALIZAR))
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pInstrumento.obtenerTipo());
                lvPaso.setString(2, pInstrumento.obtenerDescripcion());
                lvPaso.setInt(3, pInstrumento.obtenerMinimo());
                lvPaso.setInt(4, pInstrumento.obtenerMaximo());
                lvPaso.setInt(5, pInstrumento.obtenerTolerancia());
                lvPaso.setString(6, pInstrumento.obtenerNumeroSerie());

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

    public boolean eliminar(String pNumeroSerie)
    {
        boolean lvValorRetorno = false;
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); PreparedStatement lvPaso = lvConexion.prepareStatement(CMD_ELIMINAR))
            {
                lvPaso.clearParameters();
                lvPaso.setString(1, pNumeroSerie);

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
    
    public List<Instrumento> listaInstrumentos()
    {
        List<Instrumento> lvLista = new ArrayList<>();

        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_LISTAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new Instrumento(rs.getString("NumeroSerie"), rs.getString("Tipo"), rs.getString("Descripcion"), Integer.parseInt(rs.getString("Minimo")), Integer.parseInt(rs.getString("Maximo")), Integer.parseInt(rs.getString("Tolerancia"))));
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
        List<Instrumento> lvInstrumentos = listaInstrumentos();
        Object[][] r = new Object[lvInstrumentos.size()][6];
        int lvIndice = 0;
        for (Instrumento lvInstrumento : lvInstrumentos)
        {
            r[lvIndice][0] = lvInstrumento.obtenerNumeroSerie();
            r[lvIndice][1] = lvInstrumento.obtenerTipo();
            r[lvIndice][2] = lvInstrumento.obtenerDescripcion();
            r[lvIndice][3] = lvInstrumento.obtenerMinimo();
            r[lvIndice][4] = lvInstrumento.obtenerMaximo();
            r[lvIndice][5] = lvInstrumento.obtenerTolerancia();
            lvIndice++;
        }
        return r;
    }

    public List<Instrumento> buscar(String pFiltro, String pValor)
    {
        String CMD_BUSCAR;
        List<Instrumento> lvLista = new ArrayList<>();
        String lvCriterioBusqueda = "";
        boolean esString = true;
        switch (pFiltro)
        {
            case "Numero de serie":
                lvCriterioBusqueda = "NumeroSerie";
                break;
            case "Indice de tolerancia":
                lvCriterioBusqueda = "Tolerancia";
                esString = false;
                break;
            case "Tipo":
            case "Descripcion":
                lvCriterioBusqueda = pFiltro;
            case "Minimo":
            case "Maximo":
                esString = false;
                lvCriterioBusqueda = pFiltro;
                break;
            default:
                break;
        }
        if((esString)||(pValor.equals("")))
            CMD_BUSCAR = "SELECT * FROM instrumento WHERE "+ lvCriterioBusqueda + " LIKE '%" + pValor + "%';";
        else
            CMD_BUSCAR = "SELECT * FROM instrumento WHERE "+ lvCriterioBusqueda + " LIKE '%" + pValor + "%';";
        try 
        {
            try (Connection lvConexion = aBaseDatos.obtenerConexion(BASE_DATOS, USUARIO, CLAVE); Statement lvPaso = lvConexion.createStatement(); ResultSet rs = lvPaso.executeQuery(CMD_BUSCAR)) 
            {
                while (rs.next()) 
                {
                    lvLista.add(new Instrumento(rs.getString("NumeroSerie"), rs.getString("Tipo"), rs.getString("Descripcion"), Integer.parseInt(rs.getString("Minimo")), Integer.parseInt(rs.getString("Maximo")), Integer.parseInt(rs.getString("Tolerancia"))));
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
        List<Instrumento> lvInstrumentos = buscar(pFiltro, pValor);
        Object[][] r = new Object[lvInstrumentos.size()][6];
        int lvIndice = 0;
        for (Instrumento lvInstrumento : lvInstrumentos)
        {
            r[lvIndice][0] = lvInstrumento.obtenerNumeroSerie();
            r[lvIndice][1] = lvInstrumento.obtenerTipo();
            r[lvIndice][2] = lvInstrumento.obtenerDescripcion();
            r[lvIndice][3] = lvInstrumento.obtenerMinimo();
            r[lvIndice][4] = lvInstrumento.obtenerMaximo();
            r[lvIndice][5] = lvInstrumento.obtenerTolerancia();
            lvIndice++;
        }
        return r;
    }
      //metodo que retorna la verdadero si la serie ya se ingreso 
    
    public boolean ingresaSerie(String  pSerie){
        boolean lvRespuesta=false;
        List<Instrumento> list= listaInstrumentos();
        for(Instrumento lvIntrumeto : list){
           if(lvIntrumeto.obtenerNumeroSerie() == null ? pSerie == null : lvIntrumeto.obtenerNumeroSerie().equals(pSerie))
               lvRespuesta=true;
        }
        return lvRespuesta;
    }
    private static final String BASE_DATOS = "instrumentos";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static final String CMD_LISTAR = "SELECT NumeroSerie, Tipo, Descripcion, Minimo, Maximo, Tolerancia FROM instrumento ORDER BY NumeroSerie;";
    private static final String CMD_AGREGAR = "INSERT INTO instrumento VALUES (?, ?, ?, ?, ?, ?);";
    private static final String CMD_RECUPERAR = "SELECT NumeroSerie, Tipo, Descripcion, Minimo, Maximo, Tolerancia FROM instrumento WHERE NumeroSerie = ?;";
    private static final String CMD_ACTUALIZAR = "UPDATE instrumento SET Tipo = ?, Descripcion = ?, Minimo = ?, Maximo = ?, Tolerancia = ? WHERE NumeroSerie = ?;";
    private static final String CMD_ELIMINAR = "DELETE FROM instrumento WHERE NumeroSerie = ?;";

    private static GestorInstrumentos aInstancia = null;
    private final GestorBaseDatos aBaseDatos;
}
