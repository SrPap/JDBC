package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Pedido;

public class PedidoDAO extends DAO {

    public void listarPedidosPorCliente(int idCliente) throws Exception {
        String sql = "SELECT * FROM pedidos WHERE id_cliente = " + idCliente;
        consultarDataBase(sql);
        List<Pedido> pedidos = new ArrayList<>();
        while (resultSet.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(resultSet.getInt("id_pedido"));
            pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
            pedidos.add(pedido);
        }
        
        for (Pedido pedido : pedidos) {
            pedido.imprimirInformacionBasica();
        }
    }

    public List<Pedido> obtenerListarPedidosPorCliente(int idCliente) throws Exception {
        String sql = "SELECT * FROM pedidos WHERE id_cliente = " + idCliente;
        consultarDataBase(sql);
        List<Pedido> pedidos = new ArrayList<>();
        while (resultSet.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(resultSet.getInt("id_pedido"));
            pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
            pedidos.add(pedido);
        }
        return pedidos;
    }

    public List<Pedido> listarPedidosPorEstado(String estado) throws Exception {
        String sql = "SELECT * FROM pedidos WHERE estado = '" + estado + "'";
        consultarDataBase(sql);
        List<Pedido> pedidos = new ArrayList<>();
        while (resultSet.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(resultSet.getInt("id_pedido"));
            pedido.setEstado(resultSet.getString("estado"));
            pedidos.add(pedido);
        }
        return pedidos;
    }

    public List<Pedido> listarPedidosPorProducto(int idProducto) throws Exception {
        String sql = "SELECT * FROM pedidos p JOIN detalle_pedido dp ON p.id_pedido = dp.id_pedido WHERE dp.id_producto = " + idProducto;
        consultarDataBase(sql);
        List<Pedido> pedidos = new ArrayList<>();
        while (resultSet.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(resultSet.getInt("id_pedido"));
            pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
            pedidos.add(pedido);
        }
        return pedidos;
    }



    public void guardarPedido(Pedido pedido) throws Exception {
        if (pedido == null) {
            throw new Exception("El pedido no puede ser nulo.");
        }

        try {
            String sql = "INSERT INTO pedidos (id_pedido, codigo_pedido, fecha_pedido, fecha_esperada, fecha_entrega, estado, comentarios, id_cliente) VALUES ('"
                    + pedido.getIdPedido() + "', '"
                    + pedido.getCodigoPedido() + "', '"
                    + pedido.getFechaPedido() + "', '"
                    + pedido.getFechaEsperada() + "', '"
                    + pedido.getFechaEntrega() + "', '"
                    + pedido.getEstado() + "', '"
                    + pedido.getComentarios() + "', '"
                    + pedido.getIdCliente() + "')";

            insertarModificarEliminarDataBase(sql);
            System.out.println("Pedido guardado correctamente.");

        } catch (SQLException e) {
            throw new Exception("Error al guardar el pedido: " + e.getMessage());
        } finally {
            desconectarDataBase();
        }
    }
}
