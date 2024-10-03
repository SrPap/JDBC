
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import persistencia.DAO;

public class App extends DAO {

    public static void main(String[] args) {
        App mainApp = new App();
        try {
            mainApp.connectarDataBase();

            //buscarClientes(mainApp.conexion);

            //buscarClientePorCodigo( mainApp.conexion,  1); // codigo 1 para probar
            //buscarClientesPorEmpleado( mainApp.conexion,  8); // codigo 8 para probar

            //getProductosParaReponer(mainApp.conexion,  350);
            //getProductosGama(mainApp.conexion,  3);

            //getPedidosPorCliente(mainApp.conexion, 1); // Ejemplo: pedidos del cliente con idCliente 1
            //getPedidosPorEstado(mainApp.conexion, "Entregado");

            //                       COMPLEMENTARIOS

            //getProductosPorGamaList(mainApp.conexion);
            //getProductosNoComprados(mainApp.conexion);
            //getPedidosPorProducto(mainApp.conexion, 1);             
            //getPedidosPorFechas(mainApp.conexion, "2007-01-01", "2008-12-31");
            //getProductosPorProveedor(mainApp.conexion, "HiperGarden Tools"); 
            getPedidosPorClienteCompleto(mainApp.conexion, 3); 

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                mainApp.desconectarDataBase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void buscarClientes(Connection conexion) {
                String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente ";
                try {
                    Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    int count = 0;
                    while (rs.next()) {
                        String nombre = rs.getString("nombre_contacto");
                        String apellido = rs.getString("apellido_contacto");
                        String telefono = rs.getString("telefono");
                        count++;
                        System.out.println(count + " - " + nombre + " " + apellido + " -  " + telefono);
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error en la consulta: " + e.getMessage());
                }
             }
        



    public static void buscarClientePorCodigo(Connection conexion, int codigo) {
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = " + codigo;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Código: " + rs.getInt("codigo_cliente"));
                System.out.println("Nombre: " + rs.getString("nombre_contacto"));
                System.out.println("Apellido: " + rs.getString("apellido_contacto"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
            } else {
                System.out.println("No se encontró ningún cliente con el código: " + codigo);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void buscarClientesPorEmpleado(Connection conexion, int codigoEmpleado) {
        String sql = "SELECT c.nombre_contacto, c.apellido_contacto, c.telefono "
                   + "FROM cliente c "
                   + "JOIN empleado e ON c.id_empleado = e.id_empleado "
                   + "WHERE e.id_empleado = " + codigoEmpleado;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + " - " + nombre + " " + apellido + " - " + telefono);
            }

            if (count == 0) {
                System.out.println("No se encontraron clientes asociados al empleado con código: " + codigoEmpleado);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }


    public static void getProductosParaReponer(Connection conexion, int ProductosReponer) {
        String sql = "SELECT id_producto, nombre, cantidad_en_stock FROM producto WHERE cantidad_en_stock > " + ProductosReponer;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                String id = rs.getString("id_producto");
                String nombre = rs.getString("nombre");
                String stock = rs.getString("cantidad_en_stock");
                count++;
                System.out.println(count + " - ID: " + id + "- Producto: " + nombre + " -  Stock: " + stock);
            }

            if (count == 0) {
                System.out.println("No se encontraron productos con un mayor stock que: " + ProductosReponer);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }


    public static void getProductosGama(Connection conexion, int codigoGama) {
        String sql = "SELECT p.codigo_producto, p.nombre, g.id_gama, g.gama " 
        + "FROM producto p " 
        + "JOIN gama_producto g ON g.id_gama = p.id_gama "
        + "WHERE g.id_gama = " + codigoGama;
        

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                
                String CódigoP = rs.getString("codigo_producto");
                String NombreP = rs.getString("nombre");
                int CódigoG = rs.getInt("id_gama");
                String NombreG = rs.getString("gama");
                count++;
                System.out.println(count + " - Código: " + CódigoP + "- Producto: " + NombreP + " -  Código Gama: " + CódigoG
                                   + " -  Gama: " + NombreG);
            }

            if (count == 0) {
                System.out.println("No se encontró ningún producto con el gama: " + codigoGama);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // Método para obtener pedidos de un cliente por su ID
    public static void getPedidosPorCliente(Connection conexion, int idCliente) {
        String sql = "SELECT id_pedido, fecha_pedido, estado FROM pedido WHERE id_cliente = " 
               + idCliente;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                int numeroPedido = rs.getInt("id_pedido");
                String fechaPedido = rs.getString("fecha_pedido");
                String Estado = rs.getString("estado");
                count++;
                System.out.println(count + " - Pedido Nº: " + numeroPedido + ", Fecha: " + fechaPedido + ", Total: " + Estado);
            }

            if (count == 0) {
                System.out.println("No se encontraron pedidos para el cliente con ID: " + idCliente);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // Método para obtener pedidos según su estado
    public static void getPedidosPorEstado(Connection conexion, String estado) {
        String sql = "SELECT id_pedido, id_cliente, fecha_pedido, fecha_esperada FROM pedido WHERE estado = '" 
               + estado + "'";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                int numeroPedido = rs.getInt("id_pedido");
                int codigoCliente = rs.getInt("id_cliente");
                String fechaPedido = rs.getString("fecha_pedido");
                String fechaEsperada = rs.getString("fecha_esperada");
                count++;
                System.out.println(count + " - Pedido Nº: " + numeroPedido + ", Cliente ID: " + codigoCliente + ", Fecha del pedido: " + fechaPedido + ", Fecha espedara: " + fechaEsperada);
            }

            if (count == 0) {
                System.out.println("No se encontraron pedidos con el estado: " + estado);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }












    //                 COMPLEMENTARIOS


    // Método para obtener la cantidad de productos por gama
    public static void getProductosPorGamaList(Connection conexion) {
        String sql = "SELECT COUNT(*) AS cantidad, gama_producto.gama FROM producto " +
                     "JOIN gama_producto ON producto.id_gama = gama_producto.id_gama " +
                     "GROUP BY gama_producto.gama";
    
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                String gama = rs.getString("gama");
                System.out.println("Cantidad " + cantidad + " - Gama " + gama);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // Método para obtener productos que nunca han sido comprados
    public static void getProductosNoComprados(Connection conexion) {
        String sql = "SELECT nombre FROM producto " +
                     "WHERE id_producto NOT IN (SELECT id_producto FROM detalle_pedido)";
    
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                System.out.println("Producto no comprado: " + nombreProducto);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    

    // Método para obtener los pedidos que contienen un producto específico
    public static void getPedidosPorProducto(Connection conexion, int idProducto) {
        String sql = "SELECT pedido.id_pedido, pedido.fecha_pedido FROM pedido " +
                     "JOIN detalle_pedido ON pedido.id_pedido = detalle_pedido.id_pedido " +
                     "WHERE detalle_pedido.id_producto = ?";
    
        try { 
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                Date fechaPedido = rs.getDate("fecha_pedido");
                System.out.println("Pedido ID: " + idPedido + " - Fecha: " + fechaPedido);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    

    // Método para obtener los pedidos entre dos fechas
    public static void getPedidosPorFechas(Connection conexion, String desde, String hasta) {
        String sql = "SELECT id_pedido, fecha_pedido FROM pedido " +
                     "WHERE fecha_pedido BETWEEN ? AND ?";
    
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Convertir las cadenas de texto a objetos java.sql.Date
            java.util.Date parsedDesde = dateFormat.parse(desde);
            java.util.Date parsedHasta = dateFormat.parse(hasta);
            // Convertir java.util.Date a java.sql.Date
            Date DateDesde = new Date(parsedDesde.getTime());
            Date DateHasta = new Date(parsedHasta.getTime());

            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setDate(1, DateDesde);
            stmt.setDate(2, DateHasta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                Date fechaPedido = rs.getDate("fecha_pedido");
                System.out.println("Pedido ID: " + idPedido + " - Fecha: " + fechaPedido);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        } catch (ParseException ex) {
        }
    }
    
    // Método para obtener productos de un proveedor específico
    public static void getProductosPorProveedor(Connection conexion, String proveedor) {
        String sql = "SELECT codigo_producto, nombre FROM producto WHERE proveedor = ?";
    
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, proveedor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String codigoProducto = rs.getString("codigo_producto");
                String nombreProducto = rs.getString("nombre");
                System.out.println("Producto: " + codigoProducto + " - " + nombreProducto);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    

    // Método para obtener los pedidos con su detalle de un cliente específico
    public static void getPedidosPorClienteCompleto(Connection conexion, int idCliente) {
        String sql = "SELECT p.id_pedido, p.fecha_pedido, dp.id_producto, dp.cantidad " +
                     "FROM pedido p " +
                     "JOIN detalle_pedido dp ON p.id_pedido = dp.id_pedido " +
                     "WHERE p.id_cliente = ?";
    
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                Date fechaPedido = rs.getDate("fecha_pedido");
                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");
                System.out.println("Pedido ID: " + idPedido + " - Fecha: " + fechaPedido + 
                                   " - Producto ID: " + idProducto + " - Cantidad: " + cantidad);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    
}
