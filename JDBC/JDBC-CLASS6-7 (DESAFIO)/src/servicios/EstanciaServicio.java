package servicios;

import java.util.List;

import entidades.Casa;
import entidades.Cliente;
import entidades.Estancia;
import persistencia.EstanciaDAO;

public class EstanciaServicio {

    private final EstanciaDAO estanciaDAO;

    public EstanciaServicio() {
        this.estanciaDAO = new EstanciaDAO();
    }

    public void guardarEstancia(Estancia estancia) throws Exception {
        try {
            if (estancia.getNombreHuesped() == null || estancia.getNombreHuesped().trim().isEmpty()) {
                throw new Exception("El nombre del huésped es obligatorio.");
            }
            estanciaDAO.guardarEstancia(estancia);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Estancia> listarEstancias() throws Exception {
        try {
            return estanciaDAO.listarEstancias();
        } catch (Exception e) {
            throw e;
        }
    }






    public void mostrarEstanciasReservadas() throws Exception {
    List<Object[]> estanciasReservadas = estanciaDAO.buscarEstanciasReservadas();

    for (Object[] obj : estanciasReservadas) {
        Cliente cliente = (Cliente) obj[0]; 
        Casa casa = (Casa) obj[1];           

        System.out.println("Nombre Cliente: " + cliente.getNombreCliente());
        System.out.println("País Cliente: " + cliente.getPais());
        System.out.println("Ciudad Cliente: " + cliente.getCiudad());

        System.out.println("ID Casa: " + casa.getIdCasa());
        System.out.println("Calle: " + casa.getCalle());
        System.out.println("Número: " + casa.getNumero());
        System.out.println("Ciudad: " + casa.getCiudad());
        System.out.println("País: " + casa.getPais());
        System.out.println("Precio por habitación: " + casa.getPrecioHabitacion());
        System.out.println("-------------------------");
    }
}
    public void insertarNuevaEstancia(int idCliente, int idCasa, String fechaInicio, String fechaFin) throws Exception {
        boolean disponible = estanciaDAO.verificarDisponibilidadEstancia(idCasa, fechaInicio, fechaFin);
        if (disponible) {
            estanciaDAO.insertarEstancia(idCliente, idCasa, fechaInicio, fechaFin);
            System.out.println("Estancia insertada exitosamente.");
        } else {
            System.out.println("La estancia no está disponible en las fechas indicadas.");
        }
    }
}
