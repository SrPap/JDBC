
import java.util.Scanner;

import servicios.CasaServicio;
import servicios.ClienteServicio;
import servicios.ComentarioServicio;
import servicios.EstanciaServicio;
import servicios.FamiliaServicio;

/* 
Buscar y listar aquellas familias que tienen al menos 3 hijos, y con edad máxima inferior a 10 años. 

Buscar y listar las casas disponibles para el periodo comprendido entre el 1 de agosto de 2020 y el 31 de agosto de 2020 en Reino Unido. 

Buscar y listar  todas aquellas familias cuya dirección de email sea Hotmail. 

Consulta la BD para que te devuelva aquellas casas disponibles a partir de una fecha dada y un número de días específico. 

Buscar y listar los datos de todos los clientes que en algún momento realizaron una estancia y la descripción de la casa donde la realizaron. 

Buscar y listar todas las estancias que han sido reservadas por un cliente, mostrar el nombre, país y ciudad del cliente y además la información de la casa que reservó. La que reemplazaría a la anterior 

Crear un método para incrementar el precio por día. El mismo debe recibir como parámetro el % de aumento. En esta ocasión, debido a la devaluación de la libra esterlina con respecto al euro se desea incrementar el precio por día en un 5% de todas las casas del Reino Unido. Mostrar los precios actualizados. 

Obtener el número de casas que existen para cada uno de los países diferentes.

Buscar y listar aquellas casas del Reino Unido de las que se ha dicho de ellas (comentarios) que están ‘limpias’. 

Insertar nuevos datos en la tabla estancias verificando la disponibilidad de las fechas. 
*/





public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final FamiliaServicio familiaServicio = new FamiliaServicio();
    private final CasaServicio casaServicio = new CasaServicio();
    private final ClienteServicio clienteServicio = new ClienteServicio();
    private final EstanciaServicio estanciaServicio = new EstanciaServicio();
    private final ComentarioServicio comentarioServicio = new ComentarioServicio();

    public void iniciarMenu() {
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Listar familias con al menos 3 hijos y edad máxima menor a 10 años.");
            System.out.println("2. Listar casas disponibles en Reino Unido en agosto 2020.");
            System.out.println("3. Listar familias con email de Hotmail.");
            System.out.println("4. Buscar casas disponibles a partir de una fecha y número de días.");
            System.out.println("5. Listar clientes y descripción de la casa de sus estancias.");
            System.out.println("6. Listar estancias reservadas, con datos del cliente y la casa.");
            System.out.println("7. Incrementar precio por día de casas en Reino Unido (5%).");
            System.out.println("8. Mostrar número de casas por país.");
            System.out.println("9. Listar casas del Reino Unido con comentarios que digan que están 'limpias'.");
            System.out.println("10. Insertar nueva estancia verificando disponibilidad.");
            System.out.println("0. Salir.");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  

            try {
                switch (opcion) {
                    case 1 -> listarFamiliasConHijosYEdadMaxima(); //
                    case 2 -> listarCasasDisponiblesAgosto2020(); //
                    case 3 -> listarFamiliasHotmail(); //
                    case 4 -> buscarCasasPorFechaYDias(); //
                    case 5 -> listarClientesYDescripcionCasa(); //
                    case 6 -> listarEstanciasReservadas();
                    case 7 -> incrementarPrecioCasasUK(); //
                    case 8 -> contarCasasPorPais(); //
                    case 9 -> listarCasasLimpiasUK(); //
                    case 10 -> insertarNuevaEstancia();
                    case 0 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void listarFamiliasConHijosYEdadMaxima() throws Exception {
        System.out.println("\nFamilias con al menos 3 hijos y edad máxima menor a 10 años:");
        familiaServicio.listarFamiliasConHijosYEdadMaxima();
    }

    private void listarCasasDisponiblesAgosto2020() throws Exception {
        System.out.println("\nCasas disponibles en Reino Unido en agosto 2020:");
        casaServicio.listarCasasDisponiblesAgosto2020();
    }

    private void listarFamiliasHotmail() throws Exception {
        System.out.println("\nFamilias con email de Hotmail:");
        familiaServicio.listarFamiliasConHotmail();
    }

    private void buscarCasasPorFechaYDias() throws Exception {
        System.out.print("Introduce la fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Introduce el número de días: ");
        int dias = scanner.nextInt();
        scanner.nextLine();

        casaServicio.buscarCasasPorFechaYDias(fecha, dias);
    }

    private void listarClientesYDescripcionCasa() throws Exception {
        System.out.println("\nClientes y descripción de la casa de sus estancias:");
        clienteServicio.listarClientesYDescripcionCasa();
    }

    private void listarEstanciasReservadas() throws Exception {
        System.out.println("\nEstancias reservadas con información del cliente y la casa:");
        estanciaServicio.mostrarEstanciasReservadas();
    }

    private void incrementarPrecioCasasUK() throws Exception {
        System.out.println("\nIncrementando precio por día de las casas en Reino Unido en un 5%:");
        casaServicio.incrementarPrecioCasasUK(5);
    }

    private void contarCasasPorPais() throws Exception {
        System.out.println("\nNúmero de casas por país:");
        casaServicio.contarCasasPorPais();
    }

    private void listarCasasLimpiasUK() throws Exception {
        System.out.println("\nCasas del Reino Unido con comentarios de 'limpias':");
        comentarioServicio.listarCasasLimpiasUK();
    }

    private void insertarNuevaEstancia() throws Exception {
        System.out.print("Introduce la ID del cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Introduce la ID de la casa: ");
        int idCasa = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Introduce la fecha de inicio (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Introduce la fecha de fin (YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine();

        estanciaServicio.insertarNuevaEstancia(idCliente, idCasa, fechaInicio, fechaFin);
    }
}
