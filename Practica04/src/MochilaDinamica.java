
/**
 * Clase que resuelve el problema de la mochila mediante programación dinamica.
 *
 * @version 1.0
 * @author Flores Gonzalez Luis Brandon.
 */
public class MochilaDinamica {

    /**
     * Metodo para calcular la ganancia maxima de de los elementos posibles que
     * se pueden meter a la mochila, para lograr el mayor beneficio posible.
     *
     * @param P Es un arreglo de pesos.
     * @param G Es un arreglo de ganancias.
     * @param W Es la capacidad de la mochila.
     * @return Regresa la maxima ganancia.
     */
    public float maxGan(int P[], float G[], int W) {
        float MG[] = new float[W + 1]; //Arreglo para memorizar la solución.
        MG[0] = 0; //Caso base.
        for (int w2 = 0; w2 < MG.length; w2++) {
            float max = 0;
            for (int i = 0; i < P.length; i++) { //Calcula la maxima ganancia.
                if (w2 >= P[i]) {
                    float g = MG[w2 - P[i]] + G[i];
                    if (g > max) {
                        max = g;
                    }
                }
            }
            MG[w2] = max;
        }
        return MG[W];
    }

    /**
     * Metodo para calcular la ganancia maxima de de los elementos posibles que
     * se pueden meter a la mochila, para lograr el mayor beneficio posible.
     *
     * @param P Es un arreglo de pesos.
     * @param G Es un arreglo de ganancias.
     * @param W Es la capacidad de la mochila.
     * @return Regresa los elementos que se usaron en la mochila.
     */
    public int[] numElem(int P[], float G[], int W) {
        float MG[] = new float[W + 1];
        int dec[] = new int[W + 1]; //Memoriza las decisiones tomadas, arreglo de soluciones.
        int X[] = new int[P.length];
        MG[0] = 0;
        for (int w2 = 0; w2 < MG.length; w2++) {
            float max = 0;
            int imax = -1; //Recuerda cual elemento tiene el maximo.
            for (int i = 0; i < P.length; i++) {
                if (w2 >= P[i]) {
                    float g = MG[w2 - P[i]] + G[i];
                    if (g > max) {
                        max = g;
                        imax = i;
                    }
                }
            }
            MG[w2] = max;// Guarda el maximo.
            dec[w2] = imax;//Guarda la posicion del maximo.
        }
        int i = 0;
        for (int w2 = W; w2 > 0 && i != -1;) {
            i = dec[w2];
            X[i]++;
            w2 = w2 - P[i];
        }
        return X;
    }
}
