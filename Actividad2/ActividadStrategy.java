package Actividad2;

interface EstrategiaPromocion {
 double aplicarPromocion(double precio);
}

class SinPromocion implements EstrategiaPromocion {
 @Override
 public double aplicarPromocion(double precio) {
     return precio;
 }
}

class PromocionTemporada implements EstrategiaPromocion {
 @Override
 public double aplicarPromocion(double precio) {
     return precio * 0.85; // 15% de descuento
 }
}

class PromocionCyberDay implements EstrategiaPromocion {
 @Override
 public double aplicarPromocion(double precio) {
     return precio * 0.70; // 30% de descuento
 }
}

class PromocionCuponFijo implements EstrategiaPromocion {
 private double montoDescuento;

 public PromocionCuponFijo(double montoDescuento) {
     this.montoDescuento = montoDescuento;
 }

 @Override
 public double aplicarPromocion(double precio) {
     return Math.max(0, precio - montoDescuento);
 }
}

class CalculadoraPromociones {
 private EstrategiaPromocion estrategia;

 public void setEstrategia(EstrategiaPromocion estrategia) {
     this.estrategia = estrategia;
 }

 public double calcularPrecioFinal(double precioBase) {
     if (estrategia == null) {
         return precioBase;
     }
     return estrategia.aplicarPromocion(precioBase);
 }
}

public class ActividadStrategy {
 public static void main(String[] args) {
     CalculadoraPromociones calculadora = new CalculadoraPromociones();
     double precioProducto = 200.0;

     calculadora.setEstrategia(new SinPromocion());
     System.out.printf("Precio normal: S/ %.2f%n", calculadora.calcularPrecioFinal(precioProducto));

     calculadora.setEstrategia(new PromocionTemporada());
     System.out.printf("Promoción de Temporada (15%%): S/ %.2f%n", calculadora.calcularPrecioFinal(precioProducto));

     calculadora.setEstrategia(new PromocionCyberDay());
     System.out.printf("Promoción CyberDay (30%%): S/ %.2f%n", calculadora.calcularPrecioFinal(precioProducto));

     calculadora.setEstrategia(new PromocionCuponFijo(45.0));
     System.out.printf("Promoción Cupón Fijo (-S/45): S/ %.2f%n", calculadora.calcularPrecioFinal(precioProducto));
 }
}