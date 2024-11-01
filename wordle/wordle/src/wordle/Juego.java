/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iker
 */
public class Juego {

    private String diccionario, soluciones;
    private int turnos = 6;
    private int turnoActual = 1;
    static int numCaracteres = 5;
    private Palabra pJugador = new Palabra();
    private static Palabra pRandom = new Palabra();
    private static int[] aciertos;
    public Palabra[] registroPalabrasJugador = new Palabra[6];
    public static Palabra nombreJugador;
    private static int[][] arraysAciertos = new int[6][];

    public Juego(String idioma) throws Exception {
        //Asignación del diccionario elegido por el jugador en el menú
        asignarDiccionarios(idioma);
        PalabraFicherosLectura fichero = new PalabraFicherosLectura(soluciones);
        PalabraFicherosLectura ficheroDic;
        //Declaración y asignación de la palabra aleatoria que tendrá que adivinar el jugador
        //Inicio bucle juego 
        pRandom = fichero.PalabraRandom(); 

        while (turnoActual <= turnos) {
            System.out.println("Quedan " + (turnos - turnoActual) + " turnos");
            System.out.println("Introduce una palabra: ");
            pJugador = Palabra.toPalabra(LT.readLine());
            ficheroDic = new PalabraFicherosLectura(diccionario);
            boolean existe = ficheroDic.palabraExiste(pJugador);
            boolean cincoLetras = (pJugador.getNumeroCaracteres() == numCaracteres);
            //En caso de que la palabra del jugador no tenga 5 letras o no exista en el diccionario
            if ((!cincoLetras) || (!existe)) {
                novalida();
                turnoActual--;  //restaura el turno quitado al ser un error de sintaxis del jugador
//                ficheroDic.cerrarEnlaceFichero();
            } else {
                //En este punto la palabra que ha introducido el jugador tiene 5 letras y existe en el diccionario
                //Aciertos contendrá los códigos correspondientes para representar los colores de las letras
                //Primero comprobamos si la palabra que ha introducido el usuario ya es correcta
//                ficheroDic.cerrarEnlaceFichero();
                registroPalabrasJugador[turnoActual - 1] = pJugador;
                aciertos = pJugador.compararPalabras(pRandom);
                arraysAciertos[turnoActual - 1] = aciertos;
                for (int i = 0; i < turnoActual; i++) {
                    imprimirPorColores(registroPalabrasJugador[i], arraysAciertos[i]);
                }
                if (esCorrecta()) {

                    finDelJuego(true);
                }

            }

            turnoActual++;
        }
        if (!esCorrecta()) {

            finDelJuego(false);
        }
    }

    private void asignarDiccionarios(String idioma) {
        diccionario = "wordle_" + idioma + "_diccionari.txt";
        soluciones = "wordle_" + idioma + "_solucions.txt";

    }

    /**
     *      *Método que convierte el array de ints aciertos a un boolean de
     * verdadero o falso si la letra es correcta y en la posicion correcta o no
     * para saber si acabar la partida.
     *
     * @return true si la palabra es correcta
     */
    private boolean esCorrecta() {
        boolean correcto[] = new boolean[numCaracteres];
        for (int i = 0; i < aciertos.length; i++) {
            if (aciertos[i] == 0) {
                correcto[i] = true;
            } else {
                correcto[i] = false;
            }
        }
        for (int j = 0; j < correcto.length; j++) {
            if (!correcto[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Asigna el nombre pasado por parametro al jugador
     *
     * @param nombreJugador objeto Palabra
     */

    public static void setNombreJugador(Palabra nombreJugador) {
        Juego.nombreJugador = nombreJugador;
    }

    /**
     *
     * @param palabraJugador
     * @param aciertos
     */

    public void imprimirPorColores(Palabra palabraJugador, int[] aciertos) {

        char[] palabra = palabraJugador.toString().toCharArray();
        for (int i = 0; i < palabra.length; i++) {
        LT.visualizacionLetras(palabra[i], aciertos[i]);
        }
        System.out.println("\n");
    }

    /**
     * Si
     */
    private void novalida() {
        System.out.println("La palabra introducida no es válida: no tiene 5 letras o no existe en el diccionario. Inténtalo de nuevo");
    }

    private void finDelJuego(boolean ganado) throws Exception {
        for (int i = turnoActual; i < 6; i++) {
            registroPalabrasJugador[i] = Palabra.toPalabra("pnull");
        }
        Estadisticas.escribirEstadisticas(nombreJugador, pRandom, registroPalabrasJugador);
        if (ganado) {
            //rellenamos el array de palabras con - para los turnos que faltan 
            Menu.victoria();
        } else {
            Menu.derrota();
        }

    }

}
