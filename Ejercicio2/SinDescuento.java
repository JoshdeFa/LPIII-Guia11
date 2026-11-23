package Ejercicio2;

import java.util.List;

public class SinDescuento implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0.0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    @Override
    public String getNombre() {
        return "Sin descuento";
    }
}