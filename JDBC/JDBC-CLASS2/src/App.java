
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

            getPedidosPorCliente(mainApp.conexion, 1); // Ejemplo: pedidos del cliente con idCliente 1
            getPedidosPorEstado(mainApp.conexion, "Entregado");


        } catch (Exception e) {
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
}

//Actividad Pedidos
//Realizar el método getPedidosPorCliente(idCliente);  en el cual se listen todos los pedidos 
//de un cliente específico pasado por parámetro. No es necesario mostrar todos los campos de cada pedido. 

//Realizar un método getPedidosPorEstado(estado); que liste todos los pedidos que han quedado 
//en un estado que se recibe por parámetro.
