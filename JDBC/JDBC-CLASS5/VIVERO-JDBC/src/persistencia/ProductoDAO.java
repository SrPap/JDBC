package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Producto;

public class ProductoDAO extends DAO {

    public void eliminarProductoPorCodigo(int idProducto) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE id_producto = " + idProducto;

            insertarModificarEliminarDataBase(sql);

            System.out.println("Producto con id " + idProducto + " eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
            throw e;
        }
    }


     // ejercicios complementarios

    public void modificarProductoPorId(Producto producto) throws Exception {
        String sql = "UPDATE producto SET nombre_producto = '" + producto.getNombreProducto() + 
                     "', precio_venta = " + producto.getPrecioVenta() +
                     ", cantidad_en_stock = " + producto.getStock() +
                     " WHERE id_producto = " + producto.getIdProducto();
        insertarModificarEliminarDataBase(sql);
    }

    public List<Producto> listarProductosConMenorStock() throws Exception {
        String sql = "SELECT * FROM producto ORDER BY cantidad_en_stock ASC LIMIT 1";
        consultarDataBase(sql);
        List<Producto> productos = new ArrayList<>();
        while (resultSet.next()) {
            Producto producto = new Producto();
            producto.setIdProducto(resultSet.getInt("id_producto"));
            producto.setNombreProducto(resultSet.getString("nombre_producto"));
            producto.setStock(resultSet.getInt("cantidad_en_stock"));
            productos.add(producto);
        }
        return productos;
    }

    public List<Producto> listarProductosConMenorPrecio() throws Exception {
        String sql = "SELECT * FROM producto ORDER BY precio_venta ASC LIMIT 1";
        consultarDataBase(sql);
        List<Producto> productos = new ArrayList<>();
        while (resultSet.next()) {
            Producto producto = new Producto();
            producto.setIdProducto(resultSet.getInt("id_producto"));
            producto.setNombreProducto(resultSet.getString("nombre_producto"));
            producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
            productos.add(producto);
        }
        return productos;
    }
}
