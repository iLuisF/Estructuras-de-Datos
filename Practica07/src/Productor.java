
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;

/**
 * Contiene la implementación del productor ademas se escogio implementar
 * Runnable esto con el motivo de usar Executor.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Productor implements Runnable {

    ColaConcurrente<String> productor = new ColaConcurrente<>();
    int num_p, it_p, retardo_min, retardo_max, imprime;

    /**
     * Construye un productor.
     *
     * @param num_p Número de productores.
     * @param it_p Número de interaciones en el productor.
     * @param retardo_min Limite minimo del retardo para calcular numero
     * aleatorio.
     * @param retardo_max Limite maximo del retardo para calcular numero
     * aleatorio.
     * @param imprime Si es un valor positivo se imprimiran los mensajes del
     * productor.
     */
    public Productor(int num_p, int it_p, int retardo_min, int retardo_max,
            int imprime) {
        this.num_p = num_p;
        this.it_p = it_p;
        this.retardo_min = retardo_min;
        this.retardo_max = retardo_max;
        this.imprime = imprime;
    }

    /**
     * Quita el elemento del buffer es decir desencola. Esto sucede en caso de
     * que la cola no este vacía.
     *
     * @return Si la cola no esta vacia regresa el valor que desencola, en otro
     * caso, regresa null.
     * @throws ExcepcionAccesoIncorrecto En caso de querer sacar un elemento de
     * una cola vacia.
     */
    public String decolaElem() throws ExcepcionAccesoIncorrecto {
        if (!productor.esVacia()) {
            System.out.println("Tamaño de la cola: " + productor.longitud());
            return productor.saca();
        } else {
            return null;
        }
    }

    /**
     * Agrega el elemento al buffer es decir encola, ademas imprime el mensaje
     * del productor en caso de que el usuario lo indique en otro caso lo omite.
     *
     * @param thread_id Valor unico para el productor.
     * @param item Cadena que indica el token y su número.
     */
    private void encolaElem(int thread_id, String item) {
        if (esImprimir(imprime)) {
            System.out.println("Elemento encolado por productor " + thread_id + " : " + item);
        }
        productor.mete(item);
    }

    /**
     * Si la cola no esta vacía regresa el tamaño de la cola, en otro caso
     * regresa 0.
     *
     * @return En caso de que la cola no sea vacia regresa el tamaño de la cola,
     * de otra manera regresa 0.
     */
    public int getColaLongitud() {
        if (!productor.esVacia()) {
            return productor.longitud();
        } else {
            return 0;
        }
    }

    /**
     * Calcula un número aleatorio entre dos valores. Para que se pueda calcular
     * un número aleatorio se debe cumplir la condición de que el limite
     * superior sea mayor o igual que el limite inferior y que el limite
     * inferior sea mayor o igual que cero.
     *
     * @param retardo_min Es el limite inferior para calcular el número.
     * @param retardo_max Es el limite superior para calcular el número.
     * @return Número aleatorio entre el limite inferior y el limite superior,
     * si no se cumple la condición entonces regresara un número por default.
     */
    public int getAleatorio(int retardo_min, int retardo_max) {
        if (retardo_max >= retardo_min && retardo_min >= 0) {//La condición esta bien.       
            return (int) Math.floor(Math.random() * (retardo_min - retardo_max + 1) + retardo_max);
        } else {
            return 100;//En caso de que no se cumpla se asigna este valor por default.
        }
    }

    /**
     * Determina si imprime sus mensajes del productor o consumidor. Si el
     * argumenteo es positivo se imprimira su mensaje en otro caso lo omite.
     *
     * @param imprime Número entero positivo o negativo.
     * @return En caso de que el parametro sea positivo se regresa true en otro
     * caso se regresa false.
     */
    public boolean esImprimir(int imprime) {
        return imprime > 0;
    }

    /**
     * Se define la función run de la interface Runnable utilizando un ciclo
     * for. Ademas se introduce una "pausa" durante la ejecución del subproceso
     * esto se hace con la función sleep como paramentro en milisegundos.
     */
    @Override
    public void run() {
        int iteraciones;
        int getAleatorio = getAleatorio(retardo_min, retardo_max);
        for (iteraciones = 0; iteraciones < it_p && iteraciones < num_p; iteraciones++) {
            encolaElem(iteraciones, "Token" + " " + iteraciones);
            try {
                Thread.sleep(getAleatorio);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e);
            }
        }
        if (esImprimir(imprime)) {
            System.out.println("Número de iteraciones del productor: " + iteraciones);
            System.out.println("Tiempo aleatorio del productor: " + getAleatorio);
        }
    }
}
