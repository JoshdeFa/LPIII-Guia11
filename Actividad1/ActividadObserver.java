package Actividad1;

import java.util.ArrayList;
import java.util.List;

interface Observador {
    void actualizar(String mensaje);
}

class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("[" + nombre + "] recibió la notificación: " + mensaje);
    }
}

class SistemaNotificaciones {
    private List<Observador> usuarios = new ArrayList<>();

    public void suscribir(Observador usuario) {
        usuarios.add(usuario);
    }

    public void desuscribir(Observador usuario) {
        usuarios.remove(usuario);
    }

    public void notificar(String mensaje) {
        System.out.println("\n--- Enviando notificación masiva ---");
        for (Observador usuario : usuarios) {
            usuario.actualizar(mensaje);
        }
    }
}

public class ActividadObserver {
    public static void main(String[] args) {
        SistemaNotificaciones sistema = new SistemaNotificaciones();

        Usuario u1 = new Usuario("Carlos");
        Usuario u2 = new Usuario("Lucía");
        Usuario u3 = new Usuario("Mateo");

        sistema.suscribir(u1);
        sistema.suscribir(u2);
        sistema.suscribir(u3);

        sistema.notificar("¡Nueva actualización disponible en la plataforma!");

        sistema.desuscribir(u2);

        sistema.notificar("Oferta relámpago: 25% de descuento por 2 horas.");
    }
}