package model;

public class Ubicacion {
    private final int id;
    private final boolean esOnline;
    private final String direccion;    
    private final String enlaceOnline; 

    public Ubicacion(int id, boolean esOnline, String direccion, String enlaceOnline) {
        this.id = id;
        this.esOnline = esOnline;
        this.direccion = direccion;
        this.enlaceOnline = enlaceOnline;
    }

    public int getId() { return id; }
    public boolean esOnline() { return esOnline; }
    public String getDireccion() { return direccion; }
    public String getEnlaceOnline() { return enlaceOnline; }

    @Override
    public String toString() {
        return esOnline 
            ? "Online → " + enlaceOnline 
            : "Presencial → " + direccion;
    }
}
