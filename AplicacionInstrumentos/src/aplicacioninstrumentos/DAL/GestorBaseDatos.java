package aplicacioninstrumentos.DAL;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class GestorBaseDatos 
{
  protected GestorBaseDatos(String pDescripcion, String pURL_servidor)
  {
        this.aDescripcion = pDescripcion;
        this.aURL_servidor = (pURL_servidor != null) ? pURL_servidor : SERVIDOR_POR_DEFECTO;
    }

    public abstract Connection obtenerConexion(String baseDatos, String usuario, String claveAcceso) throws SQLException;

    public void cerrarConexion()
    {
        if (aConexion != null) {
            try {
                aConexion.close();
            } 
            catch (SQLException ex)
            {
                ex.printStackTrace(System.err);
            }
        }
    }

    public static GestorBaseDatos obtenerGestorBD(GBD pTipoServidor, String pURL_servidor) throws InstantiationException, ClassNotFoundException, IllegalAccessException 
    {
        if (aInstancia == null) 
        {
            switch (pTipoServidor)
            {
                case MYSQL_SERVER:
                    aInstancia = new GestorMySQL(pURL_servidor);
                    break;
                default:
                    throw new InstantiationException("El tipo de servidor no se encuentra implementado.");
            }
        }
        return aInstancia;
    }

    public static GestorBaseDatos obtenerGestorBD(GBD tipoServidor)
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        return obtenerGestorBD(tipoServidor, SERVIDOR_POR_DEFECTO);
    }

    public static GestorBaseDatos obtenerGestorBD() throws InstantiationException 
    {
        if (aInstancia == null) 
            throw new InstantiationException("Instancia inválida.");
        return aInstancia;
    }

    public String obtenerURL() 
    {
        return aURL_servidor;
    }

    public String obtenerDescripcion() 
    {
        return aDescripcion;
    }

    @Override
    public String toString() 
    {
        StringBuilder r = new StringBuilder();
        r.append(String.format("%s%nServidor de base de datos en: %s", aDescripcion, aURL_servidor));
        return r.toString();
    }

    public enum GBD 
    {
        MYSQL_SERVER,
        POSTGRESQL,
        MSSQL_SERVER,
        ORACLE_SERVER
    };

    public static final String SERVIDOR_POR_DEFECTO = "localhost";
    
    protected static GestorBaseDatos aInstancia = null;

    protected Connection aConexion = null;
    protected String aURL_servidor = null;
    private String aDescripcion = null;
}
