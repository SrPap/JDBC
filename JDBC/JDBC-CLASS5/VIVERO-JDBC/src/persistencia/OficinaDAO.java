package persistencia;

import java.util.ArrayList;
import java.util.List;
import entidades.Oficina;

public class OficinaDAO extends DAO {

    public List<Oficina> listarOficinas() throws Exception {
        String sql = "SELECT * FROM oficina";
        consultarDataBase(sql);
        List<Oficina> oficinas = new ArrayList<>();
        while (resultSet.next()) {
            Oficina oficina = new Oficina();
            oficina.setIdOficina(resultSet.getInt("id_oficina"));
            oficina.setCiudad(resultSet.getString("ciudad"));
            oficina.setPais(resultSet.getString("pais"));
            oficinas.add(oficina);
        }
        return oficinas;
    }

    public void actualizarUbicacionOficina(int idOficina, String nuevaCiudad, String nuevoPais) throws Exception {
        String sql = "UPDATE oficina SET ciudad = '" + nuevaCiudad + "', pais = '" + nuevoPais + "' WHERE id_oficina = " + idOficina;
        insertarModificarEliminarDataBase(sql);
    }
}
