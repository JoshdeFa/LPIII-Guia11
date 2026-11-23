package Ejercicio3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

interface Command {
    void execute();
    void undo();
    String getDescripcion();
}

class EditorTexto {
    private StringBuilder contenido = new StringBuilder();
    private String ultimoRespaldo = "";
    private boolean guardado = true;

    public void escribir(String texto) {
        ultimoRespaldo = contenido.toString();
        contenido.append(texto);
        guardado = false;
        System.out.println("Texto actualizado: \"" + contenido + "\"");
    }

    public void borrarUltimaLetra() {
        if (contenido.length() == 0) {
            System.out.println("El editor está vacío; no hay caracteres para borrar.");
            return;
        }
        ultimoRespaldo = contenido.toString();
        contenido.deleteCharAt(contenido.length() - 1);
        guardado = false;
        System.out.println("Texto actualizado: \"" + contenido + "\"");
    }

    public void limpiar() {
        if (contenido.length() == 0) {
            System.out.println("El editor ya está vacío.");
            return;
        }
        ultimoRespaldo = contenido.toString();
        contenido.setLength(0);
        guardado = false;
        System.out.println("Contenido limpiado.");
    }

    public void guardar() {
        guardado = true;
        System.out.println("Documento guardado con éxito -> \"" + contenido + "\"");
    }

    public void restaurarEstado(String estadoAnterior) {
        contenido = new StringBuilder(estadoAnterior);
        System.out.println("Estado restaurado -> \"" + contenido + "\"");
    }

    public String getUltimoRespaldo() {
        return ultimoRespaldo;
    }

    public String getContenido() {
        return contenido.toString();
    }

    public boolean isGuardado() {
        return guardado;
    }
}

class EscribirTextoCommand implements Command {
    private EditorTexto editor;
    private String texto;
    private String estadoPrevio;

    public EscribirTextoCommand(EditorTexto editor, String texto) {
        this.editor = editor;
        this.texto = texto;
    }

    @Override
    public void execute() {
        estadoPrevio = editor.getContenido();
        editor.escribir(texto);
    }

    @Override
    public void undo() {
        if (estadoPrevio != null) {
            editor.restaurarEstado(estadoPrevio);
        }
    }

    @Override
    public String getDescripcion() {
        return "Escribir \"" + texto + "\"";
    }
}

class BorrarUltimaLetraCommand implements Command {
    private EditorTexto editor;
    private String estadoPrevio;

    public BorrarUltimaLetraCommand(EditorTexto editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        estadoPrevio = editor.getContenido();
        editor.borrarUltimaLetra();
    }

    @Override
    public void undo() {
        if (estadoPrevio != null) {
            editor.restaurarEstado(estadoPrevio);
        }
    }

    @Override
    public String getDescripcion() {
        return "Borrar última letra";
    }
}

class LimpiarTextoCommand implements Command {
    private EditorTexto editor;
    private String estadoPrevio;

    public LimpiarTextoCommand(EditorTexto editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        estadoPrevio = editor.getContenido();
        editor.limpiar();
    }

    @Override
    public void undo() {
        if (estadoPrevio != null) {
            editor.restaurarEstado(estadoPrevio);
        }
    }

    @Override
    public String getDescripcion() {
        return "Limpiar todo el texto";
    }
}

class GuardarDocumentoCommand implements Command {
    private EditorTexto editor;

    public GuardarDocumentoCommand(EditorTexto editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.guardar();
    }

    @Override
    public void undo() {
        System.out.println("La acción de guardar no se puede deshacer.");
    }

    @Override
    public String getDescripcion() {
        return "Guardar documento";
    }
}

class GestorComandos {
    private Queue<Command> colaPendientes = new LinkedList<>();
    private Stack<Command> historialEjecutados = new Stack<>();

    public void encolarComando(Command comando) {
        colaPendientes.offer(comando);
        System.out.println("Comando encolado: " + comando.getDescripcion());
    }

    public void ejecutarSiguiente() {
        Command comando = colaPendientes.poll();
        if (comando == null) {
            System.out.println("No hay comandos pendientes en la cola.");
            return;
        }
        comando.execute();
        historialEjecutados.push(comando);
    }

    public void ejecutarTodos() {
        if (colaPendientes.isEmpty()) {
            System.out.println("La cola de comandos está vacía.");
            return;
        }
        System.out.println("\n--- Ejecutando comandos en cola ---");
        while (!colaPendientes.isEmpty()) {
            Command comando = colaPendientes.poll();
            comando.execute();
            historialEjecutados.push(comando);
        }
    }

    public void deshacerUltimo() {
        if (historialEjecutados.isEmpty()) {
            System.out.println("No hay comandos ejecutados para deshacer.");
            return;
        }
        Command ultimo = historialEjecutados.pop();
        System.out.println("Deshaciendo: " + ultimo.getDescripcion());
        ultimo.undo();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EditorTexto editor = new EditorTexto();
        GestorComandos gestor = new GestorComandos();

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=========================================");
            System.out.println("       EDITOR DE TEXTO (COMMAND)         ");
            System.out.println("=========================================");
            System.out.println("Estado actual: \"" + editor.getContenido() + "\" | Guardado: " + (editor.isGuardado() ? "Sí" : "No"));
            System.out.println("1. Encolar escribir texto");
            System.out.println("2. Encolar borrar última letra");
            System.out.println("3. Encolar limpiar texto");
            System.out.println("4. Encolar guardar documento");
            System.out.println("5. Ejecutar siguiente comando en cola");
            System.out.println("6. Ejecutar todos los comandos en cola");
            System.out.println("7. Deshacer último comando ejecutado");
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
                    System.out.print("Texto a agregar: ");
                    String texto = sc.nextLine();
                    if (!texto.isEmpty()) {
                        gestor.encolarComando(new EscribirTextoCommand(editor, texto));
                    } else {
                        System.out.println("No se ingresó texto.");
                    }
                }
                case 2 -> gestor.encolarComando(new BorrarUltimaLetraCommand(editor));
                case 3 -> gestor.encolarComando(new LimpiarTextoCommand(editor));
                case 4 -> gestor.encolarComando(new GuardarDocumentoCommand(editor));
                case 5 -> gestor.ejecutarSiguiente();
                case 6 -> gestor.ejecutarTodos();
                case 7 -> gestor.deshacerUltimo();
                case 0 -> System.out.println("Cerrando editor...");
                default -> System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}