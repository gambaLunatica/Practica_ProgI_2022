/*
CLASE Palabra
AGLUTINA LA DESCRIPCIÓN (ESTADO) Y FUNCIONALIDADES (COMPORTAMIENTO) QUE DEFINEN
A UN OBJETO Palara
 */
package wordle;

public class Palabra {

    //DECLARACIONES ATRIBUTOS DE LA CLASE
    //declaración atributo de clase constante entera para representar
    //el número máximo de caracteres que puede tener un objeto Palabra
    private static final int MAXIMO_NUMERO_CARACTERES = 100;
    //declaración atributo de clase constante caracter para representar
    //el caracter que identifica el final de una secuencia de caracteres
    //a introducir por teclado
    private static final char FIN_SECUENCIA = '.';
    //declaración atributo de clase constante caracter para representar
    //el caracter espacio 
    private static final char ESPACIO = ' ';
    //declaración atributo de clase variable char para almacenar el último
    //caracter leído de la secuencia de caracteres introducida por teclado
    private static char caracter = ESPACIO;

    //declaración atributo de objeto array de componentes caracteres
    private char[] caracteres = new char[MAXIMO_NUMERO_CARACTERES];
    //declaración atributo de objeto variable entera para almacenar el número
    //de caracteres de un objeto Palabra
    private int numeroCaracteres;

    //MÉTODOS CONSTRUCTORES
    //declaración método constructor sin parámetros
    public Palabra() {
        //inicialización atributo numeroCaracteres a 0
        numeroCaracteres = 0;
    }

    //MÉTODOS FUNCIONALES
    //declaración método de clase hayPalabras que verifica si hay alguna palabra
    //por leer en la secuencia de caracteres introducida por teclado
    public static boolean hayPalabras() throws Exception {
        buscarPalabra();
        return (caracter != FIN_SECUENCIA);
    }
    public static Palabra toPalabra (String s){
        Palabra aux = new Palabra();
        char[] conversion = s.toCharArray();
        for (int i = 0; i < conversion.length; i++) {
            aux.adicionCaracter(conversion[i]);            
        }
        return aux;
    }

    /** 
     *
     * declaración método privado buscarPalabra que lleva cabo la búsqueda de
     * una palabra en la secuencia de caracteres introducida por teclado
     *
     * @throws Exception
     */

    private static void buscarPalabra() throws Exception {
        while ((!(((caracter >= 'a') && (caracter <= 'z')) || ((caracter >= 'A') && (caracter <= 'Z'))))
                && (caracter != FIN_SECUENCIA)) {
            caracter = LT.readChar();
        }
    }

    //declaración método de objeto lectura que lleva a cabo la lectura, caracter
    //a caracter, de un objeto Palabra desde la secuencia de caracteres introducida
    //por teclado
    public void lectura() throws Exception {
        //inicialización atributo numeroCaracteres a 0 para incializar el
        //objeto Palabra donde vamos a almacenar la palabra a leer desde
        //la secuencia de caracteres
        numeroCaracteres = 0;
        //bucle lectura de la palabra caracter a caracter      
        while ((caracter != (char) 10) && (caracter != ESPACIO)) {
            //almacenar el caracter leído en la componente correspondiente
            //del atributo caracteres
            this.caracteres[numeroCaracteres] = caracter;
            //incrementar atributo numeroCaracteres
            this.numeroCaracteres++;
            //lectura siguiente caracter de la secuencia de caracteres
            caracter = LT.readChar();
        }

    }
    /**
     * 
     * @param pDiccionario palbrabra del diccionario a comparar
     * 
     * @return 
     */

    public boolean sonIguales(Palabra pDiccionario) {

        if (this.getNumeroCaracteres() != pDiccionario.getNumeroCaracteres()) {
            return false;
        }
        char[] p1 = this.toString().toCharArray();
        char[] p2 = pDiccionario.toString().toCharArray();
        for (int i = 0; i < pDiccionario.getNumeroCaracteres(); i++) {
            if (p1[i] != p2[i]) {
                return false;
            }
        }
        return true;
    }

//Método compararPalabras al que se le pasan dos palabras por parámetro.
    //Devuelve un array de ints (inicializado con 3's) con un 2 en la posición i si la letra i de la palabra comparada
    //no se encuentra en la palabra modelo, un 1 si se encuentra en la palabra
    //modelo pero no en la posición correspondiente y un 0 si se encuentra en la misma posición.
    public int[] compararPalabras(Palabra pRandom) {
        int[] verificacion = new int[pRandom.numeroCaracteres];
        for (int i = 0; i < verificacion.length; i++) {
            verificacion[i] = 3;
        }
        char[] pUsuario = this.toString().toCharArray();
        char[] pObjetivo = pRandom.toString().toCharArray();
        boolean[] revisadas;
        revisadas = resetearArrayRevisadas(pRandom.numeroCaracteres);
        for (int i = 0; i < pRandom.numeroCaracteres; i++) {
            if (pUsuario[i] == pObjetivo[i]) {
                revisadas[i] = true;
                verificacion[i] = 0;
            } else {
                for (int j = 0; j < pRandom.numeroCaracteres; j++) {
                    if (pUsuario[i] == pObjetivo[j] && revisadas[j] == false) {
                        revisadas[j] = true;
                        verificacion[i] = 1;
                    }

                }
                if ((verificacion[i] != 1) && (verificacion[i] != 0)) {
                    verificacion[i] = 2;
                }
            }
        }
        for (int i = 0; i < verificacion.length; i++) {

        }
        return verificacion;
    }

    private boolean[] resetearArrayRevisadas(int numCaracteres) {
        boolean[] revisadas = new boolean[numCaracteres];
        for (int i = 0; i < numCaracteres; i++) {
            revisadas[i] = false;
        }
        return revisadas;

    }

    //declaración método de objeto toString que lleva a cabo la conversión
    //de un objeto Palabra a String
    @Override
    public String toString() {
        //DECLARACIONES
        //declaración variable String para almacenar a través de la operación
        //de concatenación los diferentes caracteres del objeto Palabra
        //correspondiente
        String resultado = "";

        //bucle de concatenación para almacenar en el String resultado
        //los caracteres del objeto Palabra correspondiente
        for (int indice = 0; indice < numeroCaracteres; indice++) {
            //concatenación en resultado del caracter del objeto
            //Palabra correspondiente a la iteración indice-ésima
            resultado = resultado + caracteres[indice];
        }
        //Devolución resultado
        return resultado;
    }

    //delcaración método de objeto getNumeroCaracteres que devuelve el número
    //de caracteres de un objeto Palabra
    public int getNumeroCaracteres() {
        return numeroCaracteres;
    }

    //MÉTODO obtenerCaracter LLEVA A CABO LA OBTENCIÓN DEL CARACTER DE UNA
    //PALABRA QUE ESTÁ ALMACENADO EN LA POSICIÓN DADA DENTRO DEL ATRIBUTO
    //caracteres DE DICHO OBJETO Palabra
    public char obtenerCaracter(int posicion) {
        //devolver el caracter del objeto Palabra que está en la posición
        //dada dentro del atributo array caracteres
        return (caracteres[posicion]);
    }

    //MÉTODO adicionCaracter LLEVA A CABO LA ADICIÓN DE UN CARACTER DADO POR PARÁMETRO
    //EN UN OBJETO Palabra
    public void adicionCaracter(char caracter) {
        //almacenar el caracter dado en la primera componente libre del atributo
        //caracteres del objeto Palabra. El índice de dicha componente coincide
        //con el valor del atributo numeroCaracteres del mismo objeto Palabra
        caracteres[numeroCaracteres] = caracter;
        //incrementar el atributo numeroCaracteres del objeto Palabra para que
        //tenga en cuenta el caracter que acabamos de añadir en la palabra
        numeroCaracteres++;
    }
}
