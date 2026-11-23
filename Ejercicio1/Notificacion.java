package Ejercicio1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notificacion {
    private String tipo;
    private String mensaje;
    private String fechaHora;

    public Notificacion(String tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public String getTipo() { return tipo; }
    public String getMensaje() { return mensaje; }
    public String getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return String.format("[%s] (%s): %s", fechaHora, tipo.toUpperCase(), mensaje);
    }
}