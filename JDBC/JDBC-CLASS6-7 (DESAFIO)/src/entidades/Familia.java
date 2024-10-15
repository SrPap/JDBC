package entidades;

public class Familia {
    private int id_familia;
    private String nombre; 
    private int edad_minima; 
    private int edad_maxima; 
    private int num_hijos; 
    private String email; 

    public Familia(int id, String nombre, int edad_minima, int edad_maxima,
    int num_hijos, String email) {
        this.id_familia = id;
        this.nombre = nombre;
        this.edad_minima = edad_minima;
        this.edad_maxima = edad_maxima;
        this.num_hijos = num_hijos;
        this.email = email;
    }

    public Familia(String nombre, int edad_minima, int edad_maxima,
    int num_hijos, String email) {
        this.nombre = nombre;
        this.edad_minima = edad_minima;
        this.edad_maxima = edad_maxima;
        this.num_hijos = num_hijos;
        this.email = email;
    }
    public Familia() {
    }
    
    public int getId() {
        return id_familia;
    }

    public void setId(int id) {
        this.id_familia = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdadMinima() {
        return edad_minima;
    }
    
    public void setEdadMinima(int edad_minima) {
        this.edad_minima = edad_minima;
    }
    
    public int getEdadMaxima() {
        return edad_maxima;
    }
    
    public void setEdadMaxima(int edad_maxima) {
        this.edad_maxima = edad_maxima;
    }
    
    public int getNumHijos() {
        return num_hijos;
    }
    
    public void setNumHijos(int num_hijos) {
        this.num_hijos = num_hijos;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
 
    @Override
    public String toString() {
        return "Familia [id=" + id_familia + ", nombre=" + nombre
                + ", edad_minima=" + edad_minima + ", edad_maxima=" + edad_maxima
                + ", numero_hijos=" + num_hijos + ", email=" + email + "]";
    }
    
    public void imprimirInformacionBasica() {
        System.out.println("ID: " + this.id_familia + ", Nombre: " + this.nombre + ", Email: " + this.email);
    }


}