package servicios;
import entidades.Cliente;
import persistencia.ClienteDAO;
public class ClienteServicio {
    private final ClienteDAO cd;
    
    public  ClienteServicio(){
        this.cd =  new ClienteDAO();
    }

    public Cliente crearNuevoCliente(int codigoC, String nombre, String nombreContacto, String apellidoContacto,
        String telefono, String fax, String ciudad, String region, String pais, String codigoPostal,
        int idEmpleado, double limiteCredito) throws Exception {
  
       validacionesNyA(nombreContacto, apellidoContacto, (int) limiteCredito);
       Cliente cliente = new Cliente(codigoC, nombre, nombreContacto, apellidoContacto, telefono, fax, ciudad, region,
       pais, codigoPostal, idEmpleado, limiteCredito);
       cd.guardarCliente(cliente);
       return cliente;
    }

    public void validacionesNyA(String nombreContacto, String apellidoContacto, int limiteCredito) throws Exception{
      if (nombreContacto == null) {
          throw new Exception("El nombre del contacto no puede ser nulo.");
      }
      if (apellidoContacto == null) {
          throw new Exception("El apellido del contacto no puede ser nulo.");
      }
      if (limiteCredito <= 0) {
        throw new Exception("El límite de crédito debe ser mayor a 0.");
      }
    }


     public void listarTodosLosClientes() throws Exception {
        cd.listarTodosLosClientes();
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        if (codigo <= 0) {
            throw new Exception("El código del cliente debe ser mayor a 0.");
        }
        Cliente cliente = cd.buscarClientePorCodigo(codigo);
        if (cliente == null) {
            throw new Exception("No se encontró ningún cliente con el código proporcionado.");
        }
        return cliente;
    }
}