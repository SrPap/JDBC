package persistencia;

import java.sql.SQLException;

import entidades.DetallePedido;

public class DetallePedidoDAO extends DAO {
    public void guardarDetallePedido(DetallePedido detalle) throws Exception {
        if (detalle == null) {
            throw new Exception("El detalle del pedido no puede ser nulo.");
        }


        try {
            String sql = "INSERT INTO detalle_pedido (id_detalle_pedido, id_pedido, id_producto, cantidad, precio_unidad, numero_linea) VALUES ('"
                    + detalle.getIdDetallePedido() + "', '"
                    + detalle.getIdPedido() + "', '"
                    + detalle.getIdProducto() + "', '"
                    + detalle.getCantidad() + "', '"
                    + detalle.getPrecioUnidad() + "', '"
                    + detalle.getNumeroLinea() + "')";

            insertarModificarEliminarDataBase(sql);
            System.out.println("Detalle de pedido guardado correctamente.");

        } catch (SQLException e) {
            throw new Exception("Error al guardar el detalle del pedido: " + e.getMessage());
        } finally {
            desconectarDataBase();
        }
    }
}
