
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.DetallePedido;
import entidades.Pedido;
import servicios.ClienteServicio;
import servicios.PedidoServicio;
import persistencia.PedidoDAO;
import persistencia.ProductoDAO;

public class Menu {

    private final Scanner scanner;
    private final ClienteServicio clienteServicio;
    private final PedidoServicio pedidoServicio;
    private final PedidoDAO pedidoDAO;
    private final ProductoDAO productoDAO;

    public Menu() {
        scanner = new Scanner(System.in);
        clienteServicio = new ClienteServicio();
        pedidoServicio = new PedidoServicio();
        pedidoDAO = new PedidoDAO();
        productoDAO = new ProductoDAO();
    }

    public void mostrarMenuPrincipal() {
        int opcion = 0;

        do {
            System.out.println("\n----- MENÚ PRINCIPAL -----");
            System.out.println("1. Servicios de Cliente");
            System.out.println("2. Servicios de Pedido");
            System.out.println("3. Servicios de Producto");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1 -> mostrarMenuCliente();
                case 2 -> mostrarMenuPedido();
                case 3 -> mostrarMenuProducto();
                case 4 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private void mostrarMenuCliente() {
    int opcion = 0;
        do {
            System.out.println("\n----- SERVICIOS DE CLIENTE -----");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Buscar Cliente por Código");
            System.out.println("3. Listar Todos los Clientes");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> buscarClientePorCodigo();
                case 3 -> listarTodosLosClientes();
                case 4 -> System.out.println("Volviendo al Menú Principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private void mostrarMenuPedido() {
        int opcion = 0;

        do {
            System.out.println("\n----- SERVICIOS DE PEDIDO -----");
            System.out.println("1. Crear Pedido");
            System.out.println("2. Listar Pedidos por Cliente");
            System.out.println("3. Listar Pedidos por Estado");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> listarPedidosPorCliente();
                case 3 -> listarPedidosPorEstado();
                case 4 -> System.out.println("Volviendo al Menú Principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private void mostrarMenuProducto() {
        int opcion = 0;

        do {
            System.out.println("\n----- SERVICIOS DE PRODUCTO -----");
            System.out.println("1. Eliminar Producto por Código");
            System.out.println("2. Listar Productos con Menor Cantidad en Stock");
            System.out.println("3. Listar Productos con Menor Precio de Venta");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> eliminarProducto();
                case 2 -> listarProductosConMenorCantidad();
                case 3 -> listarProductosConMenorPrecio();
                case 4 -> System.out.println("Volviendo al Menú Principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private void crearCliente() {
        try {
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el nombre del contacto: ");
            String nombreContacto = scanner.nextLine();
            System.out.print("Ingrese el apellido del contacto: ");
            String apellidoContacto = scanner.nextLine();
            System.out.print("Ingrese el teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Ingrese el fax: ");
            String fax = scanner.nextLine();
            System.out.print("Ingrese la ciudad: ");
            String ciudad = scanner.nextLine();
            System.out.print("Ingrese el país: ");
            String pais = scanner.nextLine();
            System.out.print("Ingrese el código postal: ");
            String codigoPostal = scanner.nextLine();
            System.out.print("Ingrese el límite de crédito: ");
            double limiteCredito = scanner.nextDouble();

            clienteServicio.crearNuevoCliente(0, nombre, nombreContacto, apellidoContacto, telefono, fax, ciudad, null, pais, codigoPostal, 1, limiteCredito);
            System.out.println("Cliente creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    private void buscarClientePorCodigo() {
        try {
            System.out.print("Ingrese el código del cliente: ");
            int codigo = scanner.nextInt();
            clienteServicio.buscarClientePorCodigo(codigo);
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    private void listarTodosLosClientes() {
        try {
            clienteServicio.listarTodosLosClientes();
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
    }

    // Metodos para Pedidos (todavia trabajando en ello)
    private void crearPedido() {
        try {
            System.out.print("Ingrese el ID del pedido: ");
            int idPedido = scanner.nextInt();
    
            System.out.print("Ingrese el código del pedido: ");
            int codigoPedido = scanner.nextInt();
    
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            System.out.print("Ingrese la fecha del pedido (yyyy-mm-dd): ");
        String fechaPedidoStr = scanner.next();
        LocalDate fechaPedidoLocal = LocalDate.parse(fechaPedidoStr, formatter);
        Date fechaPedido = Date.valueOf(fechaPedidoLocal); // Conversión correcta

        System.out.print("Ingrese la fecha esperada (yyyy-mm-dd): ");
        String fechaEsperadaStr = scanner.next();
        LocalDate fechaEsperadaLocal = LocalDate.parse(fechaEsperadaStr, formatter);
        Date fechaEsperada = Date.valueOf(fechaEsperadaLocal); // Conversión correcta

        System.out.print("Ingrese la fecha de entrega (yyyy-mm-dd): ");
        String fechaEntregaStr = scanner.next();
        LocalDate fechaEntregaLocal = LocalDate.parse(fechaEntregaStr, formatter);
        Date fechaEntrega = Date.valueOf(fechaEntregaLocal);
    
            scanner.nextLine(); 
    
            System.out.print("Ingrese el estado del pedido: ");
            String estado = scanner.nextLine();
    
            System.out.print("Ingrese los comentarios del pedido: ");
            String comentarios = scanner.nextLine();
    
            System.out.print("Ingrese el código del cliente: ");
            int idCliente = scanner.nextInt();
    
            Pedido pedido = pedidoServicio.crearNuevoPedido(
                idPedido, codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado, comentarios, idCliente
            );
    
            List<DetallePedido> detalles = new ArrayList<>();
            char agregarOtro;
            do {
                System.out.print("Ingrese el ID del producto: ");
                int idProducto = scanner.nextInt();
                System.out.print("Ingrese la cantidad del producto: ");
                int cantidad = scanner.nextInt();
                System.out.print("Ingrese el precio del producto: ");
                double precioUnidad = scanner.nextDouble();
                System.out.print("Ingrese el numero de linea: ");
                short numeroLinea = scanner.nextShort();
                scanner.nextLine(); 
                
                detalles.add(new DetallePedido(0,0, idProducto, cantidad, precioUnidad, numeroLinea));
    
                System.out.print("¿Desea agregar otro producto al pedido? (s/n): ");
                agregarOtro = scanner.nextLine().toLowerCase().charAt(0);
            } while (agregarOtro == 's');
    
            pedidoServicio.guardarPedido(pedido, detalles);
    
            System.out.println("Pedido creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el pedido: " + e.getMessage());
        }
    }

    private void listarPedidosPorCliente() {
        try {
            System.out.print("Ingrese el código del cliente: ");
            int idCliente = scanner.nextInt();
            pedidoDAO.listarPedidosPorCliente(idCliente);
        } catch (Exception e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
    }

    private void listarPedidosPorEstado() {
        try {
            System.out.print("Ingrese el estado de los pedidos: ");
            String estado = scanner.nextLine();
            pedidoDAO.listarPedidosPorEstado(estado);
        } catch (Exception e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
    }

    // Métodos para Producto
    private void eliminarProducto() {
        try {
            System.out.print("Ingrese el código del producto a eliminar: ");
            int codigoProducto = scanner.nextInt();
            productoDAO.eliminarProductoPorCodigo(codigoProducto);
            System.out.println("Producto eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    private void listarProductosConMenorCantidad() {
        try {
            productoDAO.listarProductosConMenorStock();
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    private void listarProductosConMenorPrecio() {
        try {
            productoDAO.listarProductosConMenorPrecio();
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }
}
