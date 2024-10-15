package entidades;

import java.util.Date;

public class Estancia {
    private int id_estancia;
    private String nombre_huesped;
    private Date fecha_desde;
    private Date fecha_hasta;
    

    public Estancia(int id_estancia, String nombre_huesped, Date fecha_desde, Date fecha_hasta) {
        this.id_estancia = id_estancia;
        this.nombre_huesped = nombre_huesped;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;  
    }
    public Estancia( String nombre_huesped, Date fecha_desde, Date fecha_hasta) {
        this.nombre_huesped = nombre_huesped;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
    }
    public Estancia() {}

    public int getID() {
        return id_estancia;
    }

    public void setID(int id_estancia) {
        this.id_estancia = id_estancia;
    }

    public String getNombreHuesped() {
        return nombre_huesped;
    }

    public void setNombreHuesped(String nombre_huesped) {
        this.nombre_huesped = nombre_huesped;
    }

    public Date getFechaDesde() {
        return fecha_desde;
    }

    public void setFechaDesde(Date fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public Date getFechaHasta() {
        return fecha_hasta;
    }

    public void setFechaHasta(Date fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }
    
    @Override
    public String toString() {
        return "Estancias [id_estancia=" + id_estancia + ", nombre_huesped=" + nombre_huesped 
                + ", fecha_desde=" + fecha_desde + ", fecha_hasta=" + fecha_hasta + "]";
    }
}
