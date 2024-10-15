package servicios;

import java.util.List;

import entidades.Casa;
import persistencia.CasaDAO;

public class CasaServicio {

    private final CasaDAO casaDao;

    public CasaServicio() {
        this.casaDao = new CasaDAO();
    }

    public void guardarCasa(Casa casa) throws Exception {
        try {
            if (casa.getCiudad() == null || casa.getCiudad().trim().isEmpty()) {
                throw new Exception("La ciudad es obligatoria.");
            }
            casaDao.guardarCasa(casa);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Casa> listarCasas() throws Exception {
        try {
            return casaDao.listarCasas();
        } catch (Exception e) {
            throw e;
        }
    }




    public void listarCasasDisponiblesAgosto2020() throws Exception {
        List<Casa> casas = casaDao.buscarCasasDisponiblesAgosto2020UK();
        if (casas.isEmpty()) {
            System.out.println("No se encontraron casas disponibles en Reino Unido para agosto 2020.");
        } else {
            for (Casa casa : casas) {
                System.out.println(casa);
            }
        }
    }

    public void buscarCasasPorFechaYDias(String fecha, int dias) throws Exception {
        List<Casa> casas = casaDao.buscarCasasPorFechaYDias(fecha, dias);
        if (casas.isEmpty()) {
            System.out.println("No se encontraron casas disponibles.");
        } else {
            for (Casa casa : casas) {
                System.out.println(casa);
            }
        }
    }

    public void incrementarPrecioCasasUK(int porcentaje) throws Exception {
        int filasActualizadas = casaDao.incrementarPrecioCasasUK(porcentaje);
        System.out.println("Precios actualizados de " + filasActualizadas + " casas en Reino Unido.");
    }

    public void contarCasasPorPais() throws Exception {
        List<String> casasPorPais = casaDao.contarCasasPorPais();
        for (String resultado : casasPorPais) {
            System.out.println(resultado);
        }
    }

}
