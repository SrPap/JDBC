package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;

public class CasaDAO extends DAO {

    public void guardarCasa(Casa casa) throws Exception {
        try {
            String sql = "INSERT INTO casas (calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda) VALUES ('" 
                        + casa.getCalle() + "', " + casa.getNumero() + ", '" + casa.getCodigoPostal() + "', '" 
                        + casa.getCiudad() + "', '" + casa.getPais() + "', '" + casa.getFechaDesde() + "', '" 
                        + casa.getFechaHasta() + "', " + casa.getTiempoMinimo() + ", " + casa.getTiempoMaximo() + ", " 
                        + casa.getPrecioHabitacion() + ", '" + casa.getTipoVivienda() + "');";
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Casa> listarCasas() throws Exception {
        List<Casa> casas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM casas";
            ResultSet resultSet = consultarDataBase(sql);

            while (resultSet.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getDate("fecha_desde"));
                casa.setFechaHasta(resultSet.getDate("fecha_hasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precio_habitacion"));
                casa.setTipoVivienda(resultSet.getString("tipo_vivienda"));
                casas.add(casa);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
        return casas;
    }











    public List<Casa> buscarCasasDisponiblesAgosto2020UK() throws Exception {
        try {
            String sql = "SELECT * FROM casas WHERE fecha_desde <= '2020-08-01' AND fecha_hasta >= '2020-08-31' AND pais = 'Reino Unido'";
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

    public List<Casa> buscarCasasPorFechaYDias(String fecha, int dias) throws Exception {
        try {
            String sql = "SELECT * FROM casas WHERE fecha_desde >= '" + fecha + "' AND DATEDIFF(fecha_hasta, fecha_desde) >= " + dias;
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

    public int incrementarPrecioCasasUK(int porcentaje) throws Exception {
        try {
            String sql = "UPDATE casas SET precio_dia = precio_dia * (1 + " + (porcentaje / 100.0) + ") WHERE pais = 'Reino Unido'";
            insertarModificarEliminarDataBase(sql);

            // Se retorna el número de filas afectadas
            return statement.getUpdateCount();
        } finally {
            desconectarDataBase();
        }
    }

    public List<String> contarCasasPorPais() throws Exception {
        try {
            String sql = "SELECT pais, COUNT(*) as cantidad_casas FROM casas GROUP BY pais";
            consultarDataBase(sql);

            List<String> casasPorPais = new ArrayList<>();
            while (resultSet.next()) {
                String resultado = "País: " + resultSet.getString(1) + " - Cantidad de casas: " + resultSet.getInt(2);
                casasPorPais.add(resultado);
            }
            return casasPorPais;
        } finally {
            desconectarDataBase();
        }
    }
}
