/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle;

import static wordle.LT.visualizacionLetras;

/**
 *
 * @author 34655
 */
public class Menu {

    //declaración de la variable selección que contendrá la opción que el 
    //jugador haya elegido 
    private int seleccion;
    private String idioma;

    public Menu() throws Exception {
        menuPrincipal();
        switch (seleccion) {
            //el jugador ha elegido jugar.
            case 1:
                seleccionarIdioma();
                limpiarPantalla();
                introducirNombre();
                limpiarPantalla();
                Juego j = new Juego(idioma);
                break;
            //el jugador ha elegido visualizar las estadísticas.
            case 2:
                Estadisticas.leerEstadisticas();

                break;
            //el jugador ha elegido salir del juego. 
            case 3:
                System.exit(0);
                break;
            default:
                errorMenu();
                break;
        }
    }

    /**
     * Método que imprimirá por pantalla el primer menú que visualizará el
     * jugador y leerá la opción que seleccione
     */
    public void menuPrincipal() {
        System.out.println("///////////////////////////////////////////");
        System.out.println("////////////////////Menú///////////////////");
        System.out.println("Opciones: ");
        System.out.println("1) Jugar una partida");
        System.out.println("2) Visualizar estadísticas de las partidas");
        System.out.println("3) Salir del juego");
        System.out.println("///////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////");
        seleccion = LT.readInt();
//        limpiarPantalla();
    }

    //Método al que se llama cuando numero < 1 || numero > 3. 
    private void errorMenu() throws Exception {

        System.out.println("ERROR");
        System.out.println("Ha seleccionado un valor que no se encuentra en el rango\n "
                + "de selección, por favor escoja una de las 3 opciones ofrecidas.");
        Menu m = new Menu();
    }

    private void finDelJuego() {
        System.out.println("Fin del juego");
        System.exit(0);
        //Aquí podríamos poner las estadísticas tipo
        //"En esta partida (Nombre jugador) ha acertado X palabras, Fallado Y 
        //y obtenido una puntuacion de Z. 

    }
    //Método que limpia la pantalla de la ventana de comandos.

    public static void limpiarPantalla() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void finLimpiarPantalla() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    

    private void seleccionarIdioma() throws Exception {

        System.out.println("///////////////////////////////////////////");
        System.out.println("////////////Selecciona una opción//////////");
        System.out.println("1) Jugar en castellano");
        System.out.println("2) Jugar en catalán");
        System.out.println("3) Jugar en inglés");

        seleccion = LT.readInt();
        finLimpiarPantalla();

        switch (seleccion) {
            case 1 ->
                idioma = "castellano";
            case 2 ->
                idioma = "catala";
            case 3 ->
                idioma = "english";
            default ->
                errorMenu();
        }
    }

    private void introducirNombre() throws Exception {
        System.out.println("Introduce tu nombre: ");

        Palabra nombre = Palabra.toPalabra(LT.readLine());
        Juego.setNombreJugador(nombre);

    }

    public static void victoria() throws Exception {
        System.out.println("///////////////////////////////////////////");
        System.out.println("////////////Enhorabuena! Has ganado////////");
        System.out.println("///////////////////////////////////////////");
        System.out.println("");
        System.out.println("¿Quieres volver al menú principal? (si/no)");
        System.out.println("");
        String s = LT.readLine();
        switch(s){
            case "si": Menu m = new Menu(); break;
            case "no": System.exit(0); break;
        }

    }

    public static void derrota() throws Exception {
        System.out.println("///////////////////////////////////////////");
        System.out.println("Oops... Has perdido, ¿quieres volver a jugar?");
        System.out.println("///////////////////////////////////////////");
        System.out.println("");
        System.out.println("¿Quieres volver al menú principal? (si/no)");
        System.out.println("");
        String s = LT.readLine();
        switch(s){
            case "si": Menu m = new Menu(); break;
            case "no": System.exit(0); break;
        
        }
    }
}
