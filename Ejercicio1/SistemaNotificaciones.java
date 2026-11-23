package Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class SistemaNotificaciones {
    private List<Usuario> usuariosSuscritos = new ArrayList<>();

    public void suscribir(Usuario usuario) {
        if (!usuariosSuscritos.contains(usuario)) {
            usuariosSuscritos.add(usuario);
            System.out.println("Usuario suscrito correctamente: " + usuario);
        }
    }

    public boolean desuscribir(String nombre) {
        return usuariosSuscritos.removeIf(u -> u.getNombre().equalsIgnoreCase(nombre));
    }

    public List<Usuario> getUsuariosSuscritos() {
        return usuariosSuscritos;
    }

    public void notificarUsuarios(Notificacion notificacion) {
        System.out.println("\n--- Emitiendo Notificación ---");
        if (usuariosSuscritos.isEmpty()) {
            System.out.println("No hay usuarios suscritos actualmente.");
            return;
        }
        for (Usuario usuario : usuariosSuscritos) {
            usuario.update(notificacion);
        }
    }
}