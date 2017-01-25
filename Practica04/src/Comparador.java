
import java.util.Comparator;

/**
 * Clase para comparar dos elementos.
 *
 * @author Flores Gonzalez Luis Brandon.
 */
public class Comparador implements Comparator<Elemento> {

    /**
     * Metodo que compara dos elementos.
     *
     * @param elem1 Es el primer elemento a comparar.
     * @param elem2 Es el segundo elemento a comparar.
     * @return Regresa un numero negativo, positivo o cero si el primer elemento
     * es menor, igual que, o mayor que el segundo
     */
    @Override
    public int compare(Elemento elem1, Elemento elem2) {
        return Float.compare(elem2.getValorPeso(), elem1.getValorPeso());
    }

}
