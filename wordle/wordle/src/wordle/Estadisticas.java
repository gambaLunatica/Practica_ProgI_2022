/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author 34655
 */
/**
 * Método constructor de un array que almacenará las estadísticas. En la
 * posición 0 se contendrá el nombre del jugador en la posición 1 se contendrá
 * la palabra que el jugador tenía que adivinar en la posición 2-3-4-5-6-7 se
 * contendrán las palabras que el jugador haya intentado en la posición 8
 * tendremos los intentos que le quedaban al jugador cuando adivinó la palabra.
 * en la posición 9 la fecha en la que se jugó la partida.
 */
public class Estadisticas {

    private static String registro = "registro.txt";

    /**
     *
     * @param nombre nombre del jugador
     * @param objetivo palabra para adivinar
     * @param palabrasJugador palabras introducidas por el jugador
     * @throws Exception
     */
    public static void escribirEstadisticas(Palabra nombre, Palabra objetivo, Palabra[] palabrasJugador) throws Exception {
        DateTimeFormatter FechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String s = FechaHora.format(LocalDateTime.now());
        PalabraFicherosEscritura registroEEscritura = new PalabraFicherosEscritura((registro));

        registroEEscritura.escritura(Palabra.toPalabra("Fecha: " + s));
        registroEEscritura.nuevaLinea();
        registroEEscritura.escritura(Palabra.toPalabra("Nombre: " + nombre.toString()));
        registroEEscritura.nuevaLinea();
        registroEEscritura.escritura(Palabra.toPalabra("Palabra objetivo: " + objetivo.toString()));
        registroEEscritura.nuevaLinea();
        registroEEscritura.escritura(Palabra.toPalabra("Palabras Jugador: "));
        registroEEscritura.nuevaLinea();
        for (int i = 0; i < palabrasJugador.length; i++) {
            registroEEscritura.escritura(palabrasJugador[i]);
            registroEEscritura.nuevaLinea();
        }
        registroEEscritura.cerrarEnlaceFichero();

    }

    public static void leerEstadisticas() throws Exception {
        PalabraFicherosLectura registroELectura = new PalabraFicherosLectura(registro);

        while (registroELectura.hayPalabras()) {
            System.out.println(registroELectura.lectura().toString());
        }
        registroELectura.cerrarEnlaceFichero();
        System.out.println("");
        System.out.println("¿Quieres volver al menú principal? (si/no)");
        System.out.println("");
        String s = LT.readLine();
        switch (s) {
            case "si":
                Menu m = new Menu();
                break;
            case "no":
                System.exit(0);
                break;
        }

    }

}
