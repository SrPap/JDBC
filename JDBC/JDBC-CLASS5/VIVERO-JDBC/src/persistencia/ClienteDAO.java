package persistencia;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

public class ClienteDAO extends DAO {

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }
        
        String sql = "INSERT INTO cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, ciudad, region, pais, codigo_postal, id_empleado, limite_credito) VALUES ('"
                + cliente.getCodigoCliente() + "', '"
                + cliente.getNombreCliente() + "', '"
                + cliente.getNombreContacto() + "', '"
                + cliente.getApellidoContacto() + "', '"
                + cliente.getTelefono() + "', '"
                + cliente.getFax() + "', '"
                + cliente.getCiudad() + "', '"
                + cliente.getRegion() + "', '"
                + cliente.getPais() + "', '"
                + cliente.getCodigoPostal() + "', "
                + cliente.getIdEmpleado() + ", "
                + cliente.getLimiteCredito() + ")";
        
        insertarModificarEliminarDataBase(sql); 
    }

    public void listarTodosLosClientes() throws Exception {
        String sql = "SELECT id_cliente, nombre_cliente, apellido_contacto FROM cliente";
        consultarDataBase(sql);
        List<Cliente> clientes = new ArrayList<>();
        
        if (clientes.isEmpty()) {
            throw new Exception("No hay clientes registrados en la base de datos.");
        }

        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("id_cliente"));
            cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
            cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
            clientes.add(cliente);
        }
    
        for (Cliente cliente : clientes) {
            cliente.imprimirInformacionBasica();
        }

        
    }

    public void eliminarClientePorId(int id) throws Exception {
        String sql = "DELETE FROM cliente WHERE id_cliente = " + id;
        insertarModificarEliminarDataBase(sql); 
    }


    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM cliente WHERE id_cliente = " + codigo;
            consultarDataBase(sql);
            
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getDouble("limite_credito"));
                
                System.out.println(cliente.toString());
            } else {
                System.out.println("No se encontró un cliente con el código: " + codigo);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el cliente: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
        return cliente;
    }

    public List<Cliente> listarClientesPorEmpleado(int idEmpleado) throws Exception {
        String sql = "SELECT * FROM clientes WHERE id_empleado = " + idEmpleado;
        consultarDataBase(sql);
        List<Cliente> clientes = new ArrayList<>();
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("id_cliente"));
            cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
            cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
            cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
            cliente.setLimiteCredito(resultSet.getDouble("limite_credito"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public void incrementarLimiteCredito() throws Exception {
        String sql = "UPDATE clientes SET limite_credito = limite_credito * 1.15";
        insertarModificarEliminarDataBase(sql);
    }


    
    
}
