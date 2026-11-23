package Actividad4;

import java.util.ArrayList;
import java.util.List;

// ================= PATRÓN STRATEGY =================
interface EstrategiaDescuento {
    double calcular(double monto);
}

class DescuentoNormal implements EstrategiaDescuento {
    @Override
    public double calcular(double monto) { return monto; }
}

class DescuentoVIP implements EstrategiaDescuento {
    @Override
    public double calcular(double monto) { return monto * 0.80; } // 20% desc.
}

// ================= PATRÓN OBSERVER =================
interface Suscriptor {
    void notificar(String evento);
}

class ClienteSuscriptor implements Suscriptor {
    private String nombre;
    public ClienteSuscriptor(String nombre) { this.nombre = nombre; }

    @Override
    public void notificar(String evento) {
        System.out.println("  -> [Notificación a " + nombre + "]: " + evento);
    }
}

// Receptor + Subject (Tienda)
class TiendaOnline {
    private List<Suscriptor> suscriptores = new ArrayList<>();
    private EstrategiaDescuento estrategia = new DescuentoNormal();

    public void agregarSuscriptor(Suscriptor s) { suscriptores.add(s); }

    public void setEstrategiaDescuento(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public void procesarVenta(String producto, double precioBase) {
        double total = estrategia.calcular(precioBase);
        String mensaje = String.format("Pedido confirmado: %s | Precio final: S/ %.2f", producto, total);
        System.out.println("\n[Tienda] " + mensaje);
        for (Suscriptor s : suscriptores) {
            s.notificar(mensaje);
        }
    }
}

// ================= PATRÓN COMMAND =================
interface ComandoPedido {
    void ejecutar();
}

class ComandoProcesarPedido implements ComandoPedido {
    private TiendaOnline tienda;
    private String producto;
    private double precioBase;

    public ComandoProcesarPedido(TiendaOnline tienda, String producto, double precioBase) {
        this.tienda = tienda;
        this.producto = producto;
        this.precioBase = precioBase;
    }

    @Override
    public void ejecutar() {
        tienda.procesarVenta(producto, precioBase);
    }
}

// Invocador
class GestorPedidos {
    private List<ComandoPedido> colaPedidos = new ArrayList<>();

    public void agregarPedido(ComandoPedido comando) {
        colaPedidos.add(comando);
    }

    public void ejecutarTodos() {
        for (ComandoPedido comando : colaPedidos) {
            comando.ejecutar();
        }
        colaPedidos.clear();
    }
}

public class ActividadCombinada {
    public static void main(String[] args) {
        TiendaOnline tienda = new TiendaOnline();
        GestorPedidos gestor = new GestorPedidos();

        // Registrar observadores
        tienda.agregarSuscriptor(new ClienteSuscriptor("Ana (Cliente)"));
        tienda.agregarSuscriptor(new ClienteSuscriptor("Almacén Central"));

        // Pedido 1 con descuento normal
        tienda.setEstrategiaDescuento(new DescuentoNormal());
        gestor.agregarPedido(new ComandoProcesarPedido(tienda, "Teclado Mecánico", 250.0));
        gestor.ejecutarTodos();

        // Pedido 2 cambiando dinámicamente a descuento VIP
        tienda.setEstrategiaDescuento(new DescuentoVIP());
        gestor.agregarPedido(new ComandoProcesarPedido(tienda, "Monitor 24 pulgadas", 800.0));
        gestor.ejecutarTodos();
    }
}