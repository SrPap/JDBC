package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

public class ClienteDAO extends DAO {

    public void guardarCliente(Cliente cliente) throws Exception {
        try {
            String sql = "INSERT INTO clientes (nombre, calle, numero, codigo_postal, ciudad, pais, email) VALUES ('" 
                        + cliente.getNombreCliente() + "', '" + cliente.getCalle() + "', " + cliente.getNumero() + ", '" 
                        + cliente.getCodigoPostal() + "', '" + cliente.getCiudad() + "', '" + cliente.getPais() + "', '" 
                        + cliente.getEmail() + "');";
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Cliente> listarClientes() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clientes";
            ResultSet resultSet = consultarDataBase(sql);
            
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getInt("numero"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
        return clientes;
    }


    
    public List<Cliente> buscarClientesConDescripcionCasa() throws Exception {
        try {
            String sql = "SELECT c.nombre, c.pais, c.ciudad, co.comentario FROM clientes c " +
                         "JOIN estancias e ON c.id_cliente = e.id_cliente " +
                         "JOIN casas ca ON e.id_casa = ca.id_casa" +
                         "JOIN comentarios co ON ca.id_casa = co.id_comentario";
            consultarDataBase(sql);

            List<Cliente> clientes = new ArrayList<>();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombreCliente(resultSet.getString(1));
                cliente.setPais(resultSet.getString(2));
                cliente.setCiudad(resultSet.getString(3));
                cliente.setComentario(resultSet.getString(4)); 
                clientes.add(cliente);
            }
            return clientes;
        } finally {
            desconectarDataBase();
        }
    }
}
