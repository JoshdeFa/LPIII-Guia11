package Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Producto> productos = new ArrayList<>();
        CalculadoraDePrecios calculadora = new CalculadoraDePrecios(new SinDescuento());

        // Productos iniciales de ejemplo
        productos.add(new Producto("Teclado", 120.0));
        productos.add(new Producto("Teclado", 120.0));

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=========================================");
            System.out.println("     CALCULADORA DE PRECIOS (STRATEGY)   ");
            System.out.println("=========================================");
            System.out.println("Estrategia actual: " + calculadora.getNombreEstrategia());
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Limpiar lista de productos");
            System.out.println("4. Seleccionar estrategia de descuento");
            System.out.println("5. Calcular precio final");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del producto: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Precio del producto: ");
                    try {
                        double precio = Double.parseDouble(sc.nextLine().trim());
                        if (!nombre.isEmpty() && precio > 0) {
                            productos.add(new Producto(nombre, precio));
                            System.out.println("Producto agregado correctamente.");
                        } else {
                            System.out.println("Nombre inválido o precio menor/igual a 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Precio inválido.");
                    }
                }
                case 2 -> {
                    System.out.println("\n--- PRODUCTOS REGISTRADOS ---");
                    if (productos.isEmpty()) {
                        System.out.println("(Sin productos)");
                    } else {
                        for (int i = 0; i < productos.size(); i++) {
                            System.out.println((i + 1) + ". " + productos.get(i));
                        }
                    }
                }
                case 3 -> {
                    productos.clear();
                    System.out.println("Lista de productos vaciada.");
                }
                case 4 -> {
                    System.out.println("\n--- ELIGE UNA ESTRATEGIA ---");
                    System.out.println("1. SinDescuento");
                    System.out.println("2. DescuentoFijo (10%)");
                    System.out.println("3. DescuentoPorcentual (30% en 2 productos iguales)");
                    System.out.println("4. DescuentoPorcentualAcumulado (50% al menor precio desde 3 productos)");
                    System.out.print("Opción: ");
                    try {
                        int est = Integer.parseInt(sc.nextLine().trim());
                        switch (est) {
                            case 1 -> calculadora.setEstrategia(new SinDescuento());
                            case 2 -> calculadora.setEstrategia(new DescuentoFijo());
                            case 3 -> calculadora.setEstrategia(new DescuentoPorcentual());
                            case 4 -> calculadora.setEstrategia(new DescuentoPorcentualAcumulado());
                            default -> System.out.println("Estrategia no válida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ingresa un número válido.");
                    }
                }
                case 5 -> {
                    if (productos.isEmpty()) {
                        System.out.println("Agrega al menos un producto antes de calcular.");
                    } else {
                        double subtotal = new SinDescuento().calcularTotal(productos);
                        double totalFinal = calculadora.calcular(productos);
                        System.out.printf("\nSubtotal: S/ %.2f%n", subtotal);
                        System.out.println("Estrategia aplicada: " + calculadora.getNombreEstrategia());
                        System.out.printf("Total final: S/ %.2f%n", totalFinal);
                    }
                }
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}