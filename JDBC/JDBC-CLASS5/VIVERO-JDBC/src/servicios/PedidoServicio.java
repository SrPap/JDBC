package servicios;
import java.sql.Date;
import java.util.List;

import entidades.DetallePedido;
import entidades.Pedido;
import persistencia.DetallePedidoDAO;
import persistencia.PedidoDAO;

public class PedidoServicio {

    private final PedidoDAO pd;
    private final DetallePedidoDAO dpd;
    public  PedidoServicio(){
        this.pd =  new PedidoDAO();
        this.dpd = new DetallePedidoDAO();
    }

    public Pedido crearNuevoPedido( 
     int idPedido, 
     int codigoPedido,
     Date fechaPedido,
     Date fechaEsperada,
     Date fechaEntrega,
     String estado,
     String comentarios,
     int idCliente) throws Exception {
  
       validaciones(fechaEsperada, fechaEntrega, estado, comentarios, idCliente);
       Pedido pedido = new Pedido(idPedido, codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado, comentarios, idCliente);
       pd.guardarPedido(pedido);
       return pedido;
    }

    public void validaciones(Date fechaEsperada, Date fechaEntrega, String estado, String comentarios, int idCliente) throws Exception{
        if (fechaEsperada == null) {
          throw new Exception("La fecha de esperada no puede ser nula.");
        }
        if (fechaEntrega == null) {
          throw new Exception("La fecha de entrega no puede ser nula.");
        }  
        if (estado == null) {
            throw new Exception("La fecha de entrega no puede ser nula.");
        }
        if (comentarios == null) {
            throw new Exception("La fecha de entrega no puede ser nula.");  
        }
        if (idCliente <= 0) {
            throw new Exception("El id del cliente no es valido.");
        }
    }


    public void guardarPedido(Pedido pedido, List<DetallePedido> detalles) throws Exception {
        if (pedido == null || detalles == null || detalles.isEmpty()) {
            throw new Exception("El pedido y los detalles no pueden ser nulos o vacíos.");
        }

        pd.guardarPedido(pedido);

        int idPedido = pedido.getIdPedido();

        for (DetallePedido detalle : detalles) {
            detalle.setIdPedido(idPedido);
            dpd.guardarDetallePedido(detalle);
        }

        System.out.println("Pedido y detalles guardados correctamente.");
    }
}