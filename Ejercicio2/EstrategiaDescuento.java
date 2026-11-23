package Ejercicio2;

import java.util.List;

public interface EstrategiaDescuento {
    double calcularTotal(List<Producto> productos);
    String getNombre();
}