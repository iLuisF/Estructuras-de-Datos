
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;

/**
 * Contiene la implementación del consumidor implementando Runnable.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Consumidor implements Runnable {

    private final Productor consumidor;
    int num_c, it_c, retardo_min, retardo_max, imprime;

    /**
     * Construye un consumidor.
     *
     * @param consumidor Crea un consumidor por medio del productor.
     * @param num_c Número de consumidores.
     * @param it_c Número de iteraciones en el consumidor.
     * @param retardo_min Limite minimo del retardo para calcular numero
     * aleatorio.
     * @param retardo_max Limite maximo del retardo para calcular numero
     * aleatorio.
     * @param imprime Si en un valor positivo se imprimiran los mensajes del
     * consumidor.
     */
    public Consumidor(Productor consumidor, int num_c, int it_c, int retardo_min,
            int retardo_max, int imprime) {
        this.consumidor = consumidor;
        this.num_c = num_c;
        this.it_c = it_c;
        this.retardo_min = retardo_min;
        this.retardo_max = retardo_max;
        this.imprime = imprime;
    }

    /**
     * Se define la función run de la interface Runnable utilizando un ciclo for
     * el cual se ejecutara mientras la longitud de la cola no sea 0. Ademas se
     * introduce una "pausa" durante la ejecución del subproceso esto se hace
     * con la función sleep como paramentro en milisegundos.
     */
    @Override    
    public void run() {
        boolean pararCondicion = (consumidor.getColaLongitud() == 0);
        int iteraciones = 0;
        int j = 0; //Id del consumidor.                
        int getAleatorio = consumidor.getAleatorio(retardo_min, retardo_max);
        while (!pararCondicion && iteraciones < it_c) {
            for (int i = 0; i < num_c && iteraciones < it_c; i++) {
                try {
                    if (consumidor.esImprimir(imprime)) {
                        System.out.println("Elemento decolado al consumidor " + j + " : "
                                + consumidor.decolaElem());
                    }
                    Thread.sleep(getAleatorio);
                } catch (InterruptedException | ExcepcionAccesoIncorrecto ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                j++;
                iteraciones++;
            }
            pararCondicion = (consumidor.getColaLongitud() == 0);
        }
        if (consumidor.esImprimir(imprime)) {
            System.out.println("Número de iteraciones del consumidor: " + iteraciones);
            System.out.println("Tiempo aleatorio del consumidor: " + getAleatorio);
        }
    }
}
