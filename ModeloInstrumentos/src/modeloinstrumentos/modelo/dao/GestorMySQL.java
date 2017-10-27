package modeloinstrumentos.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorMySQL extends GestorBaseDatos
{
    GestorMySQL(String pServidor) throws ClassNotFoundException, IllegalAccessException, InstantiationException 
    {
        super("Gestor MySQL", pServidor);
        Class.forName(MANEJADOR_DB).newInstance();
    }

    GestorMySQL() throws ClassNotFoundException, IllegalAccessException, InstantiationException 
    {
        this(SERVIDOR_POR_DEFECTO);
    }
    
    @Override
    public Connection obtenerConexion(String pBaseDatos, String pUsuario, String pClaveAcceso) throws SQLException
    {
        cerrarConexion();
        String URL_conexion = String.format("%s//%s/%s", PROTOCOLO, aURL_servidor, pBaseDatos);

        aConexion = DriverManager.getConnection(URL_conexion, pUsuario, pClaveAcceso);
        return aConexion;
    }

    private static final String MANEJADOR_DB = "com.mysql.jdbc.Driver";
    private static final String PROTOCOLO = "jdbc:mysql:";
}
