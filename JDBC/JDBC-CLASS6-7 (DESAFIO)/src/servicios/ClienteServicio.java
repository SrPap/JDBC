package servicios;

import java.util.List;

import entidades.Cliente;
import persistencia.ClienteDAO;

public class ClienteServicio {

    private final ClienteDAO clienteDAO;

    public ClienteServicio() {
        this.clienteDAO = new ClienteDAO();
    }

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }
        try {
            if (cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()) {
                throw new Exception("El nombre del cliente es obligatorio.");
            }
            clienteDAO.guardarCliente(cliente);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Cliente> listarClientes() throws Exception {
        try {
            return clienteDAO.listarClientes();
        } catch (Exception e) {
            throw e;
        }
    }




    public void listarClientesYDescripcionCasa() throws Exception {
        List<Cliente> clientes = clienteDAO.buscarClientesConDescripcionCasa();
        if (clientes.isEmpty()) {
            System.out.println("No se encontraron clientes con estancias registradas.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }
}
