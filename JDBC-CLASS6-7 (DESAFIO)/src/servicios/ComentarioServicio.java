package servicios;

import java.util.List;

import entidades.Casa;
import entidades.Comentario;
import persistencia.ComentarioDAO;

public class ComentarioServicio {

    private final ComentarioDAO comentarioDAO;

    public ComentarioServicio() {
        this.comentarioDAO = new ComentarioDAO();
    }

    public void guardarComentario(Comentario comentario) throws Exception {
        try {
            if (comentario.getComentario() == null || comentario.getComentario().trim().isEmpty()) {
                throw new Exception("El comentario es obligatorio.");
            }
            comentarioDAO.guardarComentario(comentario);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Comentario> listarComentarios() throws Exception {
        try {
            return comentarioDAO.listarComentarios();
        } catch (Exception e) {
            throw e;
        }
    }





    public void listarCasasLimpiasUK() throws Exception {
        List<Casa> casas = comentarioDAO.buscarCasasLimpiasUK();
        if (casas.isEmpty()) {
            System.out.println("No se encontraron casas en Reino Unido con comentarios de que están 'limpias'.");
        } else {
            for (Casa casa : casas) {
                System.out.println(casa);
            }
        }
    }
}
