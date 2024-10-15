package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Familia;

public class FamiliaDAO extends DAO {

    public void guardarFamilia(Familia familia) throws Exception {
        try {
            String sql = "INSERT INTO familias (nombre, edad_minima, edad_maxima, num_hijos, email) VALUES ('" 
                        + familia.getNombre() + "', " + familia.getEdadMinima() + ", " + familia.getEdadMaxima() + ", " 
                        + familia.getNumHijos() + ", '" + familia.getEmail() + "');";
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFamilia(int id) throws Exception {
        try {
            String sql = "DELETE FROM familias WHERE id_familia = " + id;
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

     public List<Familia> listarFamilias() throws Exception {
        List<Familia> familias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM familias";
            ResultSet resultSet = consultarDataBase(sql);

            while (resultSet.next()) {
                Familia familia = new Familia();
                familia.setId(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdadMinima(resultSet.getInt("edad_minima"));
                familia.setEdadMaxima(resultSet.getInt("edad_maxima"));
                familia.setNumHijos(resultSet.getInt("num_hijos"));
                familia.setEmail(resultSet.getString("email"));
                familias.add(familia);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
        return familias;
    }
    
    public List<Familia> listarFamiliasConHabitaciones() throws Exception {
        List<Familia> familias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM familias WHERE num_hijos > 0"; // Ejemplo: familias con hijos podrían ofrecer habitaciones
            ResultSet resultSet = consultarDataBase(sql);

            while (resultSet.next()) {
                Familia familia = new Familia();
                familia.setId(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdadMinima(resultSet.getInt("edad_minima"));
                familia.setEdadMaxima(resultSet.getInt("edad_maxima"));
                familia.setNumHijos(resultSet.getInt("num_hijos"));
                familia.setEmail(resultSet.getString("email"));
                familias.add(familia);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
        return familias;
    }










    public List<Familia> buscarFamiliasConHijosYEdadMaxima() throws Exception {
        try {
            String sql = "SELECT * FROM familias WHERE num_hijos >= 3 AND edad_maxima < 10";
            consultarDataBase(sql);

            List<Familia> familias = new ArrayList<>();
            while (resultSet.next()) {
                Familia familia = new Familia();
                familia.setId(resultSet.getInt(1));
                familia.setNombre(resultSet.getString(2));
                familia.setEdadMinima(resultSet.getInt(3));
                familia.setEdadMaxima(resultSet.getInt(4));
                familia.setNumHijos(resultSet.getInt(5));
                familia.setEmail(resultSet.getString(6));
                familias.add(familia);
            }
            return familias;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Familia> buscarFamiliasConHotmail() throws Exception {
        try {
            String sql = "SELECT * FROM familias WHERE email LIKE '%hotmail.com'";
            consultarDataBase(sql);

            List<Familia> familias = new ArrayList<>();
            while (resultSet.next()) {
                Familia familia = new Familia();
                familia.setId(resultSet.getInt(1));
                familia.setNombre(resultSet.getString(2));
                familia.setEdadMinima(resultSet.getInt(3));
                familia.setEdadMaxima(resultSet.getInt(4));
                familia.setNumHijos(resultSet.getInt(5));
                familia.setEmail(resultSet.getString(6));
                familias.add(familia);
            }
            return familias;
        } finally {
            desconectarDataBase();
        }
    }
}
