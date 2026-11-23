package Ejercicio1;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaNotificaciones sistema = new SistemaNotificaciones();

        // Usuarios de ejemplo
        sistema.suscribir(new Usuario("Ana", "ana@correo.com"));
        sistema.suscribir(new Usuario("Luis", "luis@correo.com"));

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=========================================");
            System.out.println("   SISTEMA DE NOTIFICACIONES   ");
            System.out.println("=========================================");
            System.out.println("1. Suscribir nuevo usuario");
            System.out.println("2. Desuscribir usuario");
            System.out.println("3. Ver usuarios suscritos");
            System.out.println("4. Enviar notificación de Promoción");
            System.out.println("5. Enviar notificación de Actualización de Producto");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del usuario: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Correo del usuario: ");
                    String correo = sc.nextLine().trim();
                    if (!nombre.isEmpty() && !correo.isEmpty()) {
                        sistema.suscribir(new Usuario(nombre, correo));
                    } else {
                        System.out.println("El nombre y el correo no pueden estar vacíos.");
                    }
                }
                case 2 -> {
                    System.out.print("Nombre del usuario a desuscribir: ");
                    String nombre = sc.nextLine().trim();
                    if (sistema.desuscribir(nombre)) {
                        System.out.println("Usuario '" + nombre + "' desuscrito correctamente.");
                    } else {
                        System.out.println("No se encontró un usuario suscrito con ese nombre.");
                    }
                }
                case 3 -> {
                    System.out.println("\n--- USUARIOS SUSCRITOS ---");
                    List<Usuario> lista = sistema.getUsuariosSuscritos();
                    if (lista.isEmpty()) {
                        System.out.println("(Lista vacía)");
                    } else {
                        for (int i = 0; i < lista.size(); i++) {
                            System.out.println((i + 1) + ". " + lista.get(i));
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Escribe el mensaje de la promoción: ");
                    String msg = sc.nextLine().trim();
                    if (!msg.isEmpty()) {
                        sistema.notificarUsuarios(new Notificacion("Promoción", msg));
                    }
                }
                case 5 -> {
                    System.out.print("Escribe el detalle de la actualización del producto: ");
                    String msg = sc.nextLine().trim();
                    if (!msg.isEmpty()) {
                        sistema.notificarUsuarios(new Notificacion("Actualización de Producto", msg));
                    }
                }
                case 0 -> System.out.println("Cerrando sistema de notificaciones...");
                default -> System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}