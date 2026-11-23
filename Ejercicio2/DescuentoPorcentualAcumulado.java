package Ejercicio2;

import java.util.List;

public class DescuentoPorcentualAcumulado implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        if (productos.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        double menorPrecio = productos.get(0).getPrecio();

        for (Producto p : productos) {
            total += p.getPrecio();
            if (p.getPrecio() < menorPrecio) {
                menorPrecio = p.getPrecio();
            }
        }

        if (productos.size() >= 3) {
            total -= menorPrecio * 0.50;
        }

        return total;
    }

    @Override
    public String getNombre() {
        return "Descuento porcentual acumulado (50% al menor precio desde 3 productos)";
    }
}