
import java.util.concurrent.Executor;

/**
 * Simula una ejecución del problema. La clase lee una serie de argumentos de la
 * variable args. Ademas implementa Executor.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Simulador implements Executor {

    /**
     * Método para la ejecución de los productores y consumidores.
     *
     * @param r Productor o consumidor.
     */
    @Override
    public void execute(Runnable r) {
        new Thread(r).start();
    }

    /**
     * Método principal para probar el programa, es decir, las implementaciones
     * de cola, cola concurrente, productor y consumidor.
     *
     * @param args Recibe cadenas de texto desde la entrada estandar en el
     * siguiente orden: args[0] = num_p Este será el número de productores a
     * crear. args[1] = it_p Representa el número de iteraciones de cada
     * productor. args[2) = min_p Es el mínimo de tiempo de los productores a
     * esperar entre cada acceso a la cola. args[3] = max_p Es el máximo de
     * tiempo a esperar de los productores entre accesos a la cola. args[4] =
     * prod_mensaje Determina si los productores imprimira sus mensajes o no. Si
     * el argumento es positivo, los productores imprimen su mensaje en caso
     * contrario lo omiten. args[5] = num_c Número de consumidores a crear.
     * args[6] = it_c Número de iteraciones a realizar por los consumidores.
     * args[7] = min_c Es el minimo de tiempo a esperar de los consumidores
     * entre acceso a la cola. args[8] = max_c Máximo de tiempo a esperar de los
     * consumidores entre acceso a la cola. args[9] = cons_mensaje Determina si
     * los consumidores imprimira sus mensajes o no. Si el argumento es
     * positivo, los consumidores imprimen su mensaje en caso contrario lo
     * omiten.
     */
    public static void main(String[] args) {
        int num_p = Integer.parseInt(args[0]);
        int it_p = Integer.parseInt(args[1]);
        int min_p = Integer.parseInt(args[2]);
        int max_p = Integer.parseInt(args[3]);
        int prod_mensaje = Integer.parseInt(args[4]);
        int num_c = Integer.parseInt(args[5]);
        int it_c = Integer.parseInt(args[6]);
        int min_c = Integer.parseInt(args[7]);
        int max_c = Integer.parseInt(args[8]);
        int cons_mensaje = Integer.parseInt(args[9]);
        Executor ejecutor = new Simulador();
        Productor productor = new Productor(num_p, it_p, min_p, max_p, prod_mensaje);
        ejecutor.execute(productor);
        ejecutor.execute(new Consumidor(productor, num_c, it_c, min_c, max_c, cons_mensaje));
    }
}
