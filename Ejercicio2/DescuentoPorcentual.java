package Ejercicio2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescuentoPorcentual implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        if (productos.isEmpty()) {
            return 0.0;
        }

        Map<Producto, Integer> conteo = new HashMap<>();
        for (Producto p : productos) {
            conteo.put(p, conteo.getOrDefault(p, 0) + 1);
        }

        double total = 0.0;
        for (Map.Entry<Producto, Integer> entry : conteo.entrySet()) {
            Producto prod = entry.getKey();
            int cantidad = entry.getValue();

            if (cantidad == 2) {
                total += (prod.getPrecio() * 2) * 0.70;
            } else {
                total += prod.getPrecio() * cantidad;
            }
        }
        return total;
    }

    @Override
    public String getNombre() {
        return "Descuento porcentual (30% en 2 productos iguales)";
    }
}