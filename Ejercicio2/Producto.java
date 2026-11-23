package Ejercicio2;

import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto p)) return false;
        return Double.compare(p.precio, precio) == 0 &&
               Objects.equals(nombre.toLowerCase(), p.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), precio);
    }

    @Override
    public String toString() {
        return String.format("%s - S/ %.2f", nombre, precio);
    }
}