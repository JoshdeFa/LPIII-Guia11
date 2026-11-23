package Ejercicio1;

public class Usuario implements Observer {
    private String nombre;
    private String correo;

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    @Override
    public void update(Notificacion notificacion) {
        System.out.println("  -> Usuario " + nombre + " (" + correo + ") recibió -> " + notificacion);
    }

    @Override
    public String toString() {
        return nombre + " <" + correo + ">";
    }
}