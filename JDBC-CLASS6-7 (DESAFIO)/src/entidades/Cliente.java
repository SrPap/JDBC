package entidades;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String calle;
    private int numero;
    private String codigo_postal;
    private String ciudad;
    private String pais;
    private String email;
    private String comentario;

    public Cliente(int id_cliente, String nombre, String calle,
            int numero, String codigo_postal, String ciudad, String region, String pais,
            String email) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
        this.pais = pais;
        this.email = email;
    }
    public Cliente(String nombre, String calle,
    int numero, String codigo_postal, String ciudad, String region, String pais,
    String email) {
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
        this.pais = pais;
        this.email = email;
    }
    public Cliente() {
    }
    
    public int getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public String getNombreCliente() {
        return nombre;
    }
    
    public void setNombreCliente(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCalle() {
        return calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getCodigoPostal() {
        return codigo_postal;
    }
    
    public void setCodigoPostal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    


    @Override
    public String toString() {
        return "Cliente [idCliente=" + id_cliente + ", nombre=" + nombre + ", calle=" + calle
                + ", numero=" + numero + ", codigo_postal=" + codigo_postal + ", ciudad=" + ciudad
                + ", pais=" + pais + ", email=" + email + "]";
    }

    public void imprimirInformacionBasica() {
        System.out.println("ID: " + this.id_cliente + ", Nombre: " + this.nombre + ", Email: " + this.email);
    }



    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}