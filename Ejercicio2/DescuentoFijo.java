package Ejercicio2;

import java.util.List;

public class DescuentoFijo implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0.0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total * 0.90;
    }

    @Override
    public String getNombre() {
        return "Descuento fijo (10%)";
    }
}