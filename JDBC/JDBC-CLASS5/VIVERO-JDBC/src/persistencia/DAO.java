package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String USER = "root";
    private final String PASSWORD = "rootexample";
    private final String DATABASE = "vivero_jdbc";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void conectarDataBase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected void desconectarDataBase() throws SQLException, ClassNotFoundException {
try {
    if (resultSet != null) {
     resultSet.close();
    }
    if (statement != null) {
     statement.close();
    }
    if (conexion != null) {
     conexion.close();
    }
} catch (SQLException e) {
        System.out.println(e.getMessage());
        throw e;
    }
 }



 protected void insertarModificarEliminarDataBase(String sql) throws Exception {
    try {
        conectarDataBase();
        statement = conexion.createStatement();
        statement.executeUpdate(sql); 
    } catch (SQLException e) {
        System.out.println("Error al ejecutar la operación: " + e.getMessage());
        throw e;
    } finally {
        desconectarDataBase();
    }
}

protected ResultSet consultarDataBase(String sql) throws Exception {
    try {
        conectarDataBase(); 
        statement = conexion.createStatement();
        resultSet = statement.executeQuery(sql); 
    } catch (SQLException e) {
        System.out.println("Error al realizar la consulta: " + e.getMessage());
        throw e;
    }
    return resultSet; 
}
}