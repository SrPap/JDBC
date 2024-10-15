package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;
import entidades.Cliente;
import entidades.Comentario;
import entidades.Estancia;

public class EstanciaDAO extends DAO {

    public void guardarEstancia(Estancia estancia) throws Exception {
        try {
            String sql = "INSERT INTO estancias (nombre_huesped, fecha_desde, fecha_hasta) VALUES (" 
                        + estancia.getNombreHuesped() + "', '" 
                        + estancia.getFechaDesde() + "', '" 
                        + estancia.getFechaHasta() + "');";
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Estancia> listarEstancias() throws Exception {
        List<Estancia> estancias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM estancias";
            ResultSet resultSet = consultarDataBase(sql);

            while (resultSet.next()) {
                Estancia estancia = new Estancia();
                estancia.setID(resultSet.getInt("id_estancia"));
                estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
                estancia.setFechaDesde(resultSet.getDate("fecha_desde"));
                estancia.setFechaHasta(resultSet.getDate("fecha_hasta"));
                estancias.add(estancia);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
        return estancias;
    }


    public List<Object[]> buscarEstanciasReservadas() throws Exception {
        try {
            String sql = "SELECT c.nombre, c.pais, c.ciudad, ca.id_casa, ca.calle, ca.numero, ca.codigo_postal, " +
                         "ca.ciudad, ca.pais, ca.fecha_desde, ca.fecha_hasta, ca.precio_habitacion " +
                         "FROM clientes c " +
                         "JOIN estancias e ON c.id_cliente = e.id_cliente " +
                         "JOIN casas ca ON e.id_casa = ca.id_casa";
            consultarDataBase(sql);

            List<Object[]> estanciasReservadas = new ArrayList<>();
            while (resultSet.next()) {
                // Crear el objeto Cliente
                Cliente cliente = new Cliente();
                cliente.setNombreCliente(resultSet.getString("nombre"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCiudad(resultSet.getString("ciudad"));

                Casa casa = new Casa();
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getDate("fecha_desde"));
                casa.setFechaHasta(resultSet.getDate("fecha_hasta"));
                casa.setPrecioHabitacion(resultSet.getDouble("precio_habitacion"));

                estanciasReservadas.add(new Object[] { cliente, casa });
            }
            return estanciasReservadas;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Object[]> buscarEstanciasConDescripcionCasa() throws Exception {
        try {
            String sql = "SELECT e.id_estancia, e.nombre_huesped, e.fecha_desde, e.fecha_hasta, c.id_comentario, c.comentario " +
                         "FROM estancias e " +
                         "JOIN comentarios c ON e.id_casa = c.id_casa";
            consultarDataBase(sql);
    
            List<Object[]> estanciasConComentarios = new ArrayList<>();
            while (resultSet.next()) {
                Estancia estancia = new Estancia();
                estancia.setID(resultSet.getInt("id_estancia"));
                estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
                estancia.setFechaDesde(resultSet.getDate("fecha_desde"));  // Mantener tipo Date
                estancia.setFechaHasta(resultSet.getDate("fecha_hasta"));  // Mantener tipo Date
    
                Comentario comentario = new Comentario();
                comentario.setID(resultSet.getInt("id_comentario"));
                comentario.setComentario(resultSet.getString("comentario"));
    
                estanciasConComentarios.add(new Object[] { estancia, comentario });
            }
            return estanciasConComentarios;
        } finally {
            desconectarDataBase();
        }
    }

    public void insertarEstancia(int idCliente, int idCasa, Estancia estancia) throws Exception {
        try {
            String sql = "INSERT INTO estancias (id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta) "
                       + "VALUES (" + idCliente + ", " 
                       + idCasa + ", '" 
                       + estancia.getNombreHuesped() + "', '" 
                       + estancia.getFechaDesde() + "', '" 
                       + estancia.getFechaHasta() + "')";
            insertarModificarEliminarDataBase(sql);
        } catch (SQLException e) {
            throw new Exception("Error al insertar estancia: " + e.getMessage());
        }
    }

    public boolean verificarDisponibilidadEstancia(int idCasa, String fechaInicio, String fechaFin) throws Exception {
        try {
            String sql = "SELECT * FROM estancias WHERE id_casa = " + idCasa + 
                         " AND (fecha_desde <= '" + fechaFin + "' AND fecha_hasta >= '" + fechaInicio + "')";
            consultarDataBase(sql);
            return !resultSet.next(); 
        } finally {
            desconectarDataBase();
        }
    }

    public void insertarEstancia(int idCliente, int idCasa, String fechaInicio, String fechaFin) throws Exception {
        try {
            String sql = "INSERT INTO estancias (id_cliente, id_casa, fecha_desde, fecha_hasta) VALUES (" + 
                         idCliente + ", " + idCasa + ", '" + fechaInicio + "', '" + fechaFin + "')";
            insertarModificarEliminarDataBase(sql);
        } finally {
            desconectarDataBase();
        }
    }
}
