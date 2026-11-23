package Actividad3;

interface Comando {
 void ejecutar();
}

class Televisor {
 private int volumen = 10;
 private int canal = 1;

 public void encender() {
     System.out.println("TV: Encendido.");
 }

 public void apagar() {
     System.out.println("TV: Apagado.");
 }

 public void subirVolumen() {
     volumen++;
     System.out.println("TV: Volumen subido a " + volumen);
 }

 public void bajarVolumen() {
     if (volumen > 0) volumen--;
     System.out.println("TV: Volumen bajado a " + volumen);
 }

 public void cambiarCanal(int nuevoCanal) {
     this.canal = nuevoCanal;
     System.out.println("TV: Cambiado al canal " + canal);
 }
}

//Comandos Concretos
class ComandoEncender implements Comando {
 private Televisor tv;
 public ComandoEncender(Televisor tv) { this.tv = tv; }
 @Override
 public void ejecutar() { tv.encender(); }
}

class ComandoApagar implements Comando {
 private Televisor tv;
 public ComandoApagar(Televisor tv) { this.tv = tv; }
 @Override
 public void ejecutar() { tv.apagar(); }
}

class ComandoSubirVolumen implements Comando {
 private Televisor tv;
 public ComandoSubirVolumen(Televisor tv) { this.tv = tv; }
 @Override
 public void ejecutar() { tv.subirVolumen(); }
}

class ComandoBajarVolumen implements Comando {
 private Televisor tv;
 public ComandoBajarVolumen(Televisor tv) { this.tv = tv; }
 @Override
 public void ejecutar() { tv.bajarVolumen(); }
}

class ComandoCambiarCanal implements Comando {
 private Televisor tv;
 private int canal;
 public ComandoCambiarCanal(Televisor tv, int canal) {
     this.tv = tv;
     this.canal = canal;
 }
 @Override
 public void ejecutar() { tv.cambiarCanal(canal); }
}

//Invocador
class ControlRemoto {
 private Comando comando;

 public void setComando(Comando comando) {
     this.comando = comando;
 }

 public void presionarBoton() {
     if (comando != null) {
         comando.ejecutar();
     }
 }
}

public class ActividadCommand {
 public static void main(String[] args) {
     Televisor miTV = new Televisor();
     ControlRemoto control = new ControlRemoto();

     // 1. Encender
     control.setComando(new ComandoEncender(miTV));
     control.presionarBoton();

     // 2. Cambiar canal
     control.setComando(new ComandoCambiarCanal(miTV, 7));
     control.presionarBoton();

     // 3. Subir volumen
     control.setComando(new ComandoSubirVolumen(miTV));
     control.presionarBoton();

     // 4. Bajar volumen
     control.setComando(new ComandoBajarVolumen(miTV));
     control.presionarBoton();

     // 5. Apagar
     control.setComando(new ComandoApagar(miTV));
     control.presionarBoton();
 }
}