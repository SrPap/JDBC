package entidades;

public class Comentario {
    private int id_comentario;
    private String comentario;


    public Comentario(int id_comentario, String comentario) {
        this.id_comentario = id_comentario;
        this.comentario = comentario;
    }
    public Comentario( String comentario) {
        this.comentario = comentario;
    }
    public Comentario() {}

    public int getID() {
        return id_comentario;
    }

    public void setID(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    @Override
    public String toString() {
        return "Comentarios [Id=" + id_comentario + ", comentario=" + comentario + "]";
    }
}
