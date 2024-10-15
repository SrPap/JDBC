package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;
import entidades.Comentario;

public class ComentarioDAO extends DAO {

    public void guardarComentario(Comentario comentario) throws Exception {
        if (comentario == null) {
            throw new Exception("El comentario no puede ser nulo");
        }
        try {
            String sql = "INSERT INTO comentarios (id_comentario, comentario) VALUES (" 
                        + comentario.getID() + ", '" + comentario.getComentario() + "');";
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Comentario> listarComentarios() throws Exception {
        List<Comentario> comentarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM comentarios";
            ResultSet resultSet = consultarDataBase(sql);

            while (resultSet.next()) {
                Comentario comentario = new Comentario();
                comentario.setID(resultSet.getInt("id_comentario"));
                comentario.setComentario(resultSet.getString("comentario"));
                comentarios.add(comentario);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
        return comentarios;
    }



    public List<Casa> buscarCasasLimpiasUK() throws Exception {
        try {
            String sql = "SELECT ca.* FROM casas ca " +
                         "JOIN comentarios co ON ca.id_casa = co.id_casa " +
                         "WHERE ca.pais = 'Reino Unido' AND co.comentario LIKE '%limpia%'";
            consultarDataBase(sql);

            List<Casa> casas = new ArrayList<>();
            while (resultSet.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultSet.getInt(1));
                casa.setCalle(resultSet.getString(2));
                casa.setNumero(resultSet.getInt(3));
                casa.setCodigoPostal(resultSet.getString(4));
                casa.setCiudad(resultSet.getString(5));
                casa.setPais(resultSet.getString(6));
                casa.setFechaDesde(resultSet.getDate(7));
                casa.setFechaHasta(resultSet.getDate(8));
                casa.setPrecioHabitacion(resultSet.getDouble(9));
                casas.add(casa);
            }
            return casas;
        } finally {
            desconectarDataBase();
        }
    }
}
