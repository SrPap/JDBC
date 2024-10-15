package servicios;

import java.util.List;

import entidades.Familia;
import persistencia.FamiliaDAO;

public class FamiliaServicio {

    private final FamiliaDAO familiaDao;

    public FamiliaServicio() {
        this.familiaDao = new FamiliaDAO();
    }

    public void guardarFamilia(Familia familia) throws Exception {
        try {
            if (familia.getNombre() == null || familia.getNombre().trim().isEmpty()) {
                throw new Exception("El nombre de la familia es obligatorio.");
            }
            familiaDao.guardarFamilia(familia);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFamilia(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID de la familia debe ser mayor a 0.");
        }
        familiaDao.eliminarFamilia(id);
    }






     public void listarFamiliasConHijosYEdadMaxima() throws Exception {
        try {
            List<Familia> familias = familiaDao.buscarFamiliasConHijosYEdadMaxima();
            if (familias.isEmpty()) {
                System.out.println("No hay familias que cumplan con los requisitos.");
            } else {
                for (Familia familia : familias) {
                    System.out.println(familia);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarFamiliasConHotmail() throws Exception {
        try {
            List<Familia> familias = familiaDao.buscarFamiliasConHotmail();
            if (familias.isEmpty()) {
                System.out.println("No hay familias con correo de Hotmail.");
            } else {
                for (Familia familia : familias) {
                    System.out.println(familia);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
